package com.saajid.realtimeticketingapp.mainLogic;

/**
 * This class is responsible for initializing and simulating multiple vendor and customer threads
 */
public class Simulator {
    private Configuration config;
    private TicketPool ticketPool;

    public Simulator(Configuration config){
        this.config = config;
        this.ticketPool = new TicketPool(config);
        config.serialize();
    }

    /**
     * Simulates multithreaded environment where multiple customers and vendors interact with the ticket pool
     * @param numOfVendors The number of vendors that are going to be releasing tickets
     * @param numOfCustomers The number of customers that are going to purchase tickets
     */
    public void simulate(int numOfVendors, int numOfCustomers){

        for (int i=1; i<=numOfVendors; i++){
            String name = "Vendor_#" + i;
            Runnable vendor = new Vendor(this.ticketPool, config.getTicketReleaseRate());
            Thread vendorThread = new Thread(vendor, name);
            vendorThread.start();
        }

        for (int i=1; i<=numOfCustomers; i++){
            String name = "Customer_#" + i;
            Runnable customer = new Customer(this.ticketPool, config.getCustomerRetreivalRate());
            Thread customerThread = new Thread(customer, name );
            customerThread.start();
        }
    }

}
