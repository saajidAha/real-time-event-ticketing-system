package com.saajid.realtimeticketingapp.mainLogic;

/**
 * This class represents the Customer threads
 */
public class Customer implements Runnable{
    private TicketPool ticketPool;

    public Customer(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }
    // each customer purchases a single ticket
    @Override
    public void run(){
        if (TicketPool.customerThreadCanRun){
            this.ticketPool.removeTicket();
        }
    }
}
