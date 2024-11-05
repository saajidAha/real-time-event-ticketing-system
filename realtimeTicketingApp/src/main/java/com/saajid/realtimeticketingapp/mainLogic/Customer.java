package com.saajid.realtimeticketingapp.mainLogic;

/**
 * This class represents the Customer threads
 */
public class Customer implements Runnable{
    private TicketPool ticketPool;
    private int customerRetreivalRate;

    public Customer(TicketPool ticketPool, int customerRetreivalRate){
        this.ticketPool = ticketPool;
        this.customerRetreivalRate = customerRetreivalRate;
    }

    @Override
    public void run() {
        for (int i=0; i<10; i++){
            ticketPool.removeTicket();
            try {
                Thread.sleep( customerRetreivalRate );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
