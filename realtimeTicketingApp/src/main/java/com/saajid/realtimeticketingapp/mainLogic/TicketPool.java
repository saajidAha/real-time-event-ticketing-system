package com.saajid.realtimeticketingapp.mainLogic;

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
public class TicketPool {
    private Configuration config;
    private Vector<Ticket> tickets;
    private Lock lock;
//    Custom conditions for locks
    private Condition canConsume;
    private Condition canProduce;

    private static Logger logger = Logger.getLogger(TicketPool.class.getName());

    // static initializer
    static{
    //add a single handler for logger when the class loads for all instances
        logger.addHandler( LoggerHandler.getFileHandler() );
    }

    /**
     * Initializes the vector with specified initial number of tickets
     * Logs info to the console
     * @param config Configuration object used to extract configuration parameters by the user
     */
    public TicketPool(Configuration config){
        this.tickets = new Vector<>();
        this.config = config;
        this.lock = new ReentrantLock();
        this.canConsume = this.lock.newCondition();
        this.canProduce = this.lock.newCondition();
        init();
    }

    /**
     * Runs this code immediately after initialzation of the constructor
     */
    public void init(){
        // fill the ticket array with the specified number of tickets.
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
            while (this.tickets.size() >= this.config.getMaxTicketCapacity()) {
                this.canProduce.await(); // wait if the ticket array is full
            }
            this.tickets.add(ticket); // add ticket if the ticket array is not full
            logInfo(logger,Thread.currentThread().getName() + " issued ticket: [Ticket ID:  " + ticket.getTicketID() + "]", "INFO");
            this.canConsume.signalAll(); // notify all waiting customers
        } catch (InterruptedException e){
            logInfo(logger,"Thread has been interrupted", "SEVERE");
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
            while (this.tickets.isEmpty()) {
                this.canConsume.await(); // wait if the ticket array is empty
            }
            Ticket removedTicket = this.tickets.removeFirst(); // Remove the ticket if ticket array is not empty
            logInfo(logger,Thread.currentThread().getName() + " purchased ticket: [Ticket ID: " + removedTicket.getTicketID() + "]", "INFO");
            this.canProduce.signalAll(); // Notify all waiting producers
        } catch (InterruptedException e) {
            logInfo(logger,"Thread has been interrupted", "SEVERE");
        } finally {
            this.lock.unlock();
        }
    }

    public Configuration getConfig() {
        return config;
    }
}
