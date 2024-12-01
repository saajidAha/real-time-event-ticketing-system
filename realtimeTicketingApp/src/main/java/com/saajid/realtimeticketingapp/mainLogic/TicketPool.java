package com.saajid.realtimeticketingapp.mainLogic;

import lombok.Data;

import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import static com.saajid.realtimeticketingapp.mainLogic.LoggerHandler.logInfo;

/**
 * This class is the shared resource that going to be accessed by the Vendors and Customers
 */
@Data // auto generate getters & setters
public class TicketPool {
    private Configuration config;
    private Vector<Ticket> tickets;
    private Transaction transaction;
    private Lock lock;
//    Custom conditions for locks
    private Condition canConsume;
    private Condition canProduce;

    public static volatile boolean customerThreadCanRun;
    public static volatile boolean vendorThreadCanRun;

    private static Logger logger = Logger.getLogger(TicketPool.class.getName());

    // static initializer
    static{
    //add a single handler for logger when the class loads for all instances
        logger.addHandler( LoggerHandler.getFileHandler() );
    }

    /**
     * Initializes the TicketPool based on the configuration
     * @param config Configuration object used to extract configuration parameters by the user
     */
    public TicketPool(Configuration config){
        this.config = config;
        this.tickets = new Vector<>();
        this.lock = new ReentrantLock();
        this.canConsume = this.lock.newCondition();
        this.canProduce = this.lock.newCondition();
        fillTicketPool();
//        whenever a ticket pool is created. the default is customer and vendor threads can run
        customerThreadCanRun = true;
        vendorThreadCanRun = true;
    }

    /**
     * Fill the ticket array with the specified number of totaltickets;
     */
    public void fillTicketPool(){
            for (int i=0; i < this.config.getTotalTickets(); i++) {
                this.tickets.add(new Ticket());
            }
            logInfo(logger,"System initialized with " + this.config.getTotalTickets() + " tickets: Ticket ID's: " + this.tickets, "INFO");
    }

    /**
     * Adds tickets as long as the issued tickets does not exceed the maximum ticket capacity specified
     * Logs info to the console
     * @param ticket The ticket that will be added to the vector
     */
    public void addTicket(Ticket ticket){
        this.lock.lock();
        try {
            // wait if the ticket array is full and wait until customer purchases
            while (this.tickets.size() >= this.config.getMaxTicketCapacity()) {
                // check if signal is received by the customer with a timeout
                // set the boolean flag to false if no response is received, to avoid an indefinite wait with deadlock.
                // timeout logic : if the time exceeds the interval in which customers purchase, that means no more customer threads will run and purchase tickets.
                vendorThreadCanRun = this.canProduce.await(config.getCustomerRetrievalRate() + 200, TimeUnit.MILLISECONDS);
                // If no signal was received, terminate the thread.
                if (!vendorThreadCanRun){
                    Thread.currentThread().interrupt();
                }
            }
            this.tickets.add(ticket); // add ticket if the ticket array is not full

            logInfo(logger,Thread.currentThread().getName() + " issued ticket: [Ticket ID:  " + ticket.getTicketID() + "]", "INFO");
            this.canConsume.signalAll(); // notify all waiting customers
        } catch (InterruptedException e){
            logInfo(logger, Thread.currentThread().getName() + " thread has been terminated due to no customers attempting to buy from the pool, & the ticketpool is filled to the max capacity.", "INFO");
        } finally {
            this.lock.unlock(); // release lock and notify all waiting customers after task is complete
        }
    }

    /**
     * Removes tickets in FIFO order from the vector as long as it is not empty
     * Logs info to the console
     */
    public void removeTicket() {
        this.lock.lock();
        try {
            // wait while the ticket array is empty
            while (this.tickets.isEmpty()) {
                // check if signal is received by the vendor with a timeout
                // set the boolean flag to false if no response is received, to avoid an indefinite wait with deadlock.
                // timeout logic : if the time exceeds the interval in which vendors release, that means no more vendors threads will run & release tickets.
                customerThreadCanRun = this.canConsume.await(config.getTicketReleaseRate() + 200, TimeUnit.MILLISECONDS);
                // if no signal was recieved terminate the thread
                if (!customerThreadCanRun){
                    Thread.currentThread().interrupt();
                }
            }
            Ticket removedTicket = this.tickets.removeFirst(); // Remove the ticket if ticket array is not empty
            logInfo(logger,Thread.currentThread().getName() + " purchased ticket: [Ticket ID: " + removedTicket.getTicketID() + "]", "INFO");
            this.canProduce.signalAll(); // Notify all waiting producers
        } catch (InterruptedException e) {
            logInfo(logger, Thread.currentThread().getName() + " thread has been terminated due to no vendors releasing tickets & ticket pool being empty. ", "INFO");
        } catch (NoSuchElementException n){
            System.out.println("No tickets in the ticket pool");
        }
        finally {
            this.lock.unlock();
        }
    }
}
