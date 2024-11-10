package com.saajid.realtimeticketingapp.mainLogic;

/**
 * This class represents the Vendor threads
 */
public class Vendor implements Runnable{
    private TicketPool ticketPool;

    public Vendor(TicketPool ticketPool){
        this.ticketPool = ticketPool;
    }

//    each vendor releases a single ticket
    @Override
    public void run(){
        this.ticketPool.addTicket( new Ticket() );
    }
}
