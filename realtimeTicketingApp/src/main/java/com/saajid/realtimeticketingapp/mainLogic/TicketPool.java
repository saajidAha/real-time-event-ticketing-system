package com.saajid.realtimeticketingapp.mainLogic;

import java.util.Vector;

/**
 * This class is the shared resource that going to be accessed by the Vendors and Customers
 */
public class TicketPool {
    private Vector<Ticket> tickets;
    private Configuration config;

    /**
     * Initializes the vector with specified initial number of tickets
     * @param config Configuration object used to extract configuration parameters by the user
     */
    public TicketPool(Configuration config){
        this.tickets = new Vector<>();
        this.config = config;
        for (int i=0; i < this.config.getTotalTickets(); i++){
            this.tickets.add(new Ticket());
        }
        System.out.println("System initialized with " + this.config.getTotalTickets() + " tickets: Ticket ID's: " + this.tickets.toString() );
    }

    /**
     * Adds tickets as long as the issued tickets does not exceed the maximum ticket capacity specified
     * @param ticket The ticket that will be added to the vector
     */
    public synchronized void addTicket(Ticket ticket){
        while ( this.tickets.size()
                == this.config.getMaxTicketCapacity() ){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
            this.tickets.add(ticket);
            System.out.println(Thread.currentThread().getName() + " issued ticket successfully: [Ticket ID:  " + ticket.getTicketID() + "]");
            notifyAll();
    }

    /**
     * Removes tickets in FIFO order from the vector as long as it is not empty
     */
    public synchronized void removeTicket(){
        while (this.tickets.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
            Ticket removedTicket = this.tickets.removeFirst();
            System.out.println(Thread.currentThread().getName() + " purchased ticket successfully: [Ticket ID: " + removedTicket.getTicketID() + "]");
            notifyAll();
    }

}
