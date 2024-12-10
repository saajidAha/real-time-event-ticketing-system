package com.saajid.realtimeticketingapp.mainLogic;

import lombok.AllArgsConstructor;
/**
 * This class is responsible for the creation of multiple customer threads
 */
@AllArgsConstructor // creates constructor and takes ticketpool as an argument
public class CustomerRunner implements Runnable {
    private TicketPool ticketPool;

    /**
     * Creates and runs specified number of customer threads at specified intervals
     */
    @Override
    public void run(){
        for (int i=1; i<=this.ticketPool.getConfig().getNumOfCustomers(); i++){
            if (!ticketPool.customerThreadCanRun){
                break;
            }
            Thread thread = new Thread( new Customer(ticketPool) , "Customer_#" + i); // create the thread
            thread.start();
            try {
                Thread.sleep(this.ticketPool.getConfig().getCustomerRetrievalRate()); // sleep for the specified timeout
                thread.join(); // wait till thread completes its task to proceed
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}