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
    }

    public void addTicket(Ticket ticket){
        while ( this.config.getTotalTickets() == this.config.getMaxTicketCapacity() ){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if ( this.config.getTotalTickets() < this.config.getMaxTicketCapacity() ){
            this.tickets.add(ticket);
            System.out.println(Thread.currentThread().getName() + "added ticket successfully");
            notifyAll();
        }
    }

    public void removeTicket(){
        while (this.config.getTotalTickets() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (this.config.getTotalTickets() > 0){
            this.tickets.removeLast();
            System.out.println(Thread.currentThread().getName() + "removed ticket successfully");
            notifyAll();
        }
    }

}
