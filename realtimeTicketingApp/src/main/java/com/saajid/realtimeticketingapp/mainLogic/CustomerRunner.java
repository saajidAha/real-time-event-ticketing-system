package com.saajid.realtimeticketingapp.mainLogic;

/**
 * This class is responsible for the creation of multiple customer threads
 */
public class CustomerRunner implements Runnable {
    private TicketPool ticketPool;

    public CustomerRunner(TicketPool ticketPool){
        this.ticketPool = ticketPool;
    }

    /**
     * Creates and runs specified number of customer threads at specified intervals
     */
    @Override
    public void run(){
        for (int i=1; i<=this.ticketPool.getConfig().getNumOfCustomers(); i++){
            Simulator.startThread( this.ticketPool,"Customer", this.ticketPool.getConfig().getCustomerRetrievalRate(), i );
        }
    }
}