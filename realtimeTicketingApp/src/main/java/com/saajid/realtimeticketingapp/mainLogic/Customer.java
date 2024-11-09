package com.saajid.realtimeticketingapp.mainLogic;

/**
 * This class represents the Customer threads
 */
public class Customer implements Runnable{
    private TicketPool ticketPool;

    public Customer(TicketPool ticketPool){
        this.ticketPool = ticketPool;
    }

    @Override
    public void run(){
        ticketPool.removeTicket();
    }

}
