package com.saajid.realtimeticketingapp.mainLogic;

import lombok.AllArgsConstructor;

/**
 * This class is responsible for the creation of multiple Vendor threads
 */
@AllArgsConstructor // creates constructor and takes ticketpool as an argument
public class VendorRunner implements Runnable {
    private TicketPool ticketPool;

    /**
     * Creates and runs specified number of Vendor threads at specified intervals
     */
    @Override
    public void run(){
        for (int i=1; i<=ticketPool.getConfig().getNumOfVendors(); i++){
            if (!ticketPool.vendorThreadCanRun){
                break;
            }
            Thread thread = new Thread( new Vendor(ticketPool) , "Vendor_#" + i); // create the thread
            thread.start();
            try {
                Thread.sleep(this.ticketPool.getConfig().getTicketReleaseRate()); // sleep for the specified timeout
                thread.join(); // wait till thread completes its task to proceed
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
