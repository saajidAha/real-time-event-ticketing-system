package com.saajid.realtimeticketingapp.mainLogic;

/**
 * This class represents the Vendor threads
 */
public class Vendor implements Runnable{
    public TicketPool ticketPool;

    public Vendor(TicketPool ticketPool){
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        for (int i=0; i<10; i++){
            ticketPool.addTicket( new Ticket() );
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
