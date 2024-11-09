package com.saajid.realtimeticketingapp.mainLogic;

import jakarta.annotation.PostConstruct;

import java.util.Vector;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * This class is the shared resource that going to be accessed by the Vendors and Customers
 */
public class TicketPool {
    private Vector<Ticket> tickets;
    private Configuration config;
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
    }

    // runs this code immediately after initialzation of the constructor
    @PostConstruct
    public void init(){
        // fill the ticket array with the specified number of tickets.
        if (this.config.getTotalTickets() != 0){
            for (int i=0; i < this.config.getTotalTickets(); i++){
                this.tickets.add(new Ticket());
            }
        }
        String ticketLog = "System initialized with " + this.config.getTotalTickets() + " tickets: Ticket ID's: " + this.tickets.toString();
        logger.log(Level.INFO, ticketLog);
    }

    /**
     * Adds tickets as long as the issued tickets does not exceed the maximum ticket capacity specified
     * Logs info to the console
     * @param ticket The ticket that will be added to the vector
     */
    public synchronized void addTicket(Ticket ticket){
        // wait if the ticket array is full
        while ( this.tickets.size()
                == this.config.getMaxTicketCapacity() ){
            try {
                wait();
            } catch (InterruptedException e) {
                logger.log(Level.WARNING,Thread.currentThread().getName() +  " Thread has been interuppted while waiting");
            }
        }
        // add ticket if the ticket array is not full
        if( this.tickets.size() < this.config.getMaxTicketCapacity()){
            this.tickets.add(ticket);
            String ticketLog = Thread.currentThread().getName() + " issued ticket: [Ticket ID:  " + ticket.getTicketID() + "]";
            logger.log(Level.INFO, ticketLog);

            // sleep after adding ticket
            try {
                Thread.sleep(config.getTicketReleaseRate());
            } catch (InterruptedException e) {
                logger.log(Level.WARNING,Thread.currentThread().getName() +  " Thread has been interuppted while waiting");
            }

            // notify all waiting customers after task is complete
            notifyAll();
        }
    }

    /**
     * Removes tickets in FIFO order from the vector as long as it is not empty
     * Logs info to the console
     */
    public synchronized void removeTicket(){
        // wait if the ticket array is empty
        while (this.tickets.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                logger.log(Level.WARNING,Thread.currentThread().getName() +  " Thread has been interuppted while waiting");
            }
        }
        // remove the ticket if ticket array is not empty
        Ticket removedTicket = this.tickets.removeFirst();
        String ticketLog = Thread.currentThread().getName() + " purchased ticket: [Ticket ID: " + removedTicket.getTicketID() + "]";
        logger.log(Level.INFO, ticketLog);

        // sleep after removing the ticket
        try {
            Thread.sleep(config.getCustomerRetreivalRate());
        } catch (InterruptedException e) {
            logger.log(Level.WARNING,Thread.currentThread().getName() +  " Thread has been interuppted while waiting");
        }

        // notify all waiting producers after task is complete
        notifyAll();
    }
}
