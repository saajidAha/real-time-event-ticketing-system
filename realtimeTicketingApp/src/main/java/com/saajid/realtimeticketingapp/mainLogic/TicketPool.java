package com.saajid.realtimeticketingapp.mainLogic;

import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * This class is the shared resource that going to be accessed by the Vendors and Customers
 */
public class TicketPool {
    private Configuration config;
    private Vector<Ticket> tickets;
    private static Logger logger = Logger.getLogger(TicketPool.class.getName());
    private Lock lock;

//    Custom conditions for locks
    private Condition canConsume;
    private Condition canProduce;

//    boolean to control indefinite wait situations (e.g when customers are waiting and no tickets get released)
    private volatile boolean isProgramComplete;

//   timeout to exit indefinite wait
    private int timeout;

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
        this.timeout = 3000;
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
        String ticketLog = "System initialized with " + this.config.getTotalTickets() + " tickets: Ticket ID's: " + this.tickets;
        logger.log(Level.INFO, ticketLog);
    }

    /**
     * Adds tickets as long as the issued tickets does not exceed the maximum ticket capacity specified
     * Logs info to the console
     * @param ticket The ticket that will be added to the vector
     */
    public void addTicket(Ticket ticket){
        if (this.isProgramComplete){return;}
        this.lock.lock();
        // wait if the ticket array is full
        try {
            while (this.tickets.size() == this.config.getMaxTicketCapacity()) {
//                check if a signal is recieved from a producer else terminate;
                boolean signalReceived = this.canProduce.await(this.timeout, TimeUnit.MILLISECONDS);
                if (!signalReceived) {
                    logger.log(Level.WARNING, Thread.currentThread().getName() + " Thread terminated due to no customer attempting to buy after a timeout of " + this.timeout + " milliseconds");
                    this.isProgramComplete = true;
                    return;
                }

            }
//            if (this.isProgramComplete){return;}

            // add ticket if the ticket array is not full
            if (this.tickets.size() < this.config.getMaxTicketCapacity()) {
                this.tickets.add(ticket);
                String ticketLog = Thread.currentThread().getName() + " issued ticket: [Ticket ID:  " + ticket.getTicketID() + "]";
                logger.log(Level.INFO, ticketLog);

                // sleep after adding ticket
                Thread.sleep(config.getTicketReleaseRate());
            }
        } catch (InterruptedException e){
            System.out.println("Thread has been interrupted");

        } finally {
            // release lock and notify all waiting customers after task is complete
            this.canConsume.signalAll();
            this.lock.unlock();
        }
    }




    /**
     * Removes tickets in FIFO order from the vector as long as it is not empty
     * Logs info to the console
     */
    public void removeTicket() {
        if (this.isProgramComplete) return;
        this.lock.lock();
        try {
            // wait if the ticket array is empty
            while (this.tickets.isEmpty()) {
                boolean signalReceived = this.canConsume.await(timeout, TimeUnit.MILLISECONDS);
                if (!signalReceived) {
                    logger.log(Level.WARNING, Thread.currentThread().getName() + " Thread terminated due to no Vendor releasing tickets after a timeout of " + this.timeout + " milliseconds");
                    this.isProgramComplete = true; // mark program complete if wait times out
                    return;
                }
            }

//            if (isProgramComplete) return;

            // Remove the ticket if ticket array is not empty
            Ticket removedTicket = this.tickets.removeFirst();
            String ticketLog = Thread.currentThread().getName() + " purchased ticket: [Ticket ID: " + removedTicket.getTicketID() + "]";
            logger.log(Level.INFO, ticketLog);

            // Sleep after removing the ticket
            Thread.sleep(config.getCustomerRetrievalRate());
            this.canProduce.signalAll(); // Notify waiting producers

        } catch (InterruptedException e) {
            logger.log(Level.WARNING, Thread.currentThread().getName() + " Thread was interrupted");

        } finally {
            this.canProduce.signalAll();
            this.lock.unlock();
        }
    }
}
