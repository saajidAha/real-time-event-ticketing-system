package com.saajid.realtimeticketingapp.mainLogic;

/**
 * This class represents the Vendor threads
 */
public class Vendor implements Runnable{
    private TicketPool ticketPool;
    private int ticketReleaseRate;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate){
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        for (int i=0; i<10; i++){
            ticketPool.addTicket( new Ticket() );
            try {
                Thread.sleep( ticketReleaseRate );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
