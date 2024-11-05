package com.saajid.realtimeticketingapp.mainLogic;

import java.util.Vector;

/**
 * This class is the shared resource that going to be accessed by the Vendors and Customers
 */
public class TicketPool {
    private Vector<Ticket> tickets;
    private Configuration config;


    public TicketPool(Configuration config){
        this.tickets = new Vector<>();
        this.config = config;
        for (int i=0; i < config.getTotalTickets(); i++){
            this.tickets.add(new Ticket());
        }
    }

    public synchronized void addTicket(Ticket ticket){
        while ( this.tickets.size()
                == this.config.getMaxTicketCapacity() ){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if ( this.tickets.size() < this.config.getMaxTicketCapacity() ){
            this.tickets.add(ticket);
            System.out.println(Thread.currentThread().getName() + " added ticket successfully. ticket id: " + ticket.getTicketID() );
            notifyAll();
        }
    }

    public synchronized void removeTicket(){
        while (this.tickets.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
            Ticket removedTicket = this.tickets.removeFirst();
            System.out.println(Thread.currentThread().getName() + " removed ticket successfully ticket id: " + removedTicket.getTicketID());
            notifyAll();
    }

}
