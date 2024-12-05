package com.saajid.realtimeticketingapp.mainLogic;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
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
    private final Configuration config;
    private List<Ticket> tickets = new Vector<>();
    private List<Transaction> transactions = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    //Custom conditions for locks
    private Condition canConsume = lock.newCondition();
    private Condition canProduce = lock.newCondition();
    // booleans to control thread behaviour
    public volatile boolean customerThreadCanRun = true;
    public volatile boolean vendorThreadCanRun = true;

    private static Logger logger = Logger.getLogger(TicketPool.class.getName());

    // static initializer
    static{
    //add a single handler for logger when the class loads for all instances
        logger.addHandler( LoggerHandler.getFileHandler() );
    }

    /**
     * Constructor to initialize the TicketPool
     * @param config Configuration object
     */
    public TicketPool(Configuration config){
        this.config = config;
        fillTicketPool();
    }

    /**
     * Fill the ticket array with the specified number of totaltickets immediately after constructor is initialized;
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
            transactions.add( new Transaction(ticket.getTicketID(), "Ticket Release", Thread.currentThread().getName()) );
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
    public Ticket removeTicket() {
        this.lock.lock();
        Ticket removedTicket = null;
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
            removedTicket = this.tickets.removeFirst(); // Remove the ticket if ticket array is not empty
            transactions.add( new Transaction(removedTicket.getTicketID(), "Ticket Purchase", Thread.currentThread().getName()) );
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
            return removedTicket;
    }

    /**
     * Overloaded method to remove specific ticket
     * @param ticketID ID of the specific ticket
     */
    public synchronized boolean removeTicket(String ticketID){
        Ticket ticketToRemove = findTicketById(ticketID);
        if (ticketToRemove != null){
            tickets.remove(ticketToRemove);
            logInfo(logger,Thread.currentThread().getName() + " purchased ticket: [Ticket ID: " + ticketToRemove.getTicketID() + "]", "INFO");
            return true;
        }else{
            logInfo(logger, "Could not find ticket with ticket ID : " + ticketID + " in the ticket pool. please try purchasing an available ticket.", "WARNING");
            return false;
        }
    }

    /**
     * Finds a specific ticket based on the id of it
     * @param ticketID Specific ID of the ticket
     * @return Ticket object
     */
    public Ticket findTicketById(String ticketID){
        Ticket ticketToRemove = null;
        for (Ticket ticket: tickets){
            if ( ticketID.equalsIgnoreCase(ticket.getTicketID()) ){
                ticketToRemove = ticket;
            }
        }
        return ticketToRemove;
    }
}
