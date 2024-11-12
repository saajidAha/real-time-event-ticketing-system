package com.saajid.realtimeticketingapp.mainLogic;

/**
 * This class is responsible for the creation of multiple Vendor threads
 */
public class VendorRunner implements Runnable {
    TicketPool ticketPool;
    public VendorRunner(TicketPool ticketPool){
        this.ticketPool = ticketPool;
    }

    /**
     * Creates and runs specified number of Vendor threads at specified intervals
     */
    @Override
    public void run(){
        for (int i=1; i<=ticketPool.getConfig().getNumOfVendors(); i++){
            Simulator.startThread( this.ticketPool, "Vendor", this.ticketPool.getConfig().getTicketReleaseRate(), i );
        }
    }
}
