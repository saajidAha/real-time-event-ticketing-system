package com.saajid.realtimeticketingapp.mainLogic;

/**
 * This class represents the Customer threads
 */
public class Customer implements Runnable{
    public TicketPool ticketPool;

    public Customer(TicketPool ticketPool){
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        for (int i=0; i<10; i++){
            ticketPool.removeTicket();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
