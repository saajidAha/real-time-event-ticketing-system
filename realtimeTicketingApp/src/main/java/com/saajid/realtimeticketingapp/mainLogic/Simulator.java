package com.saajid.realtimeticketingapp.mainLogic;

public class Simulator {
    private Configuration config;
    private TicketPool ticketPool;

    public Simulator(Configuration config){
        this.config = config;
        this.ticketPool = new TicketPool(config);
        config.serialize();
    }

    public void simulate(){
        for (int i=1; i<=5; i++){
            String name = "Vendor_#" + i;
            Runnable vendor = new Vendor(this.ticketPool, config.getTicketReleaseRate());
            Thread vendorThread = new Thread(vendor, name);
            vendorThread.start();
        }

        for (int i=1; i<=5; i++){
            String name = "Customer_#" + i;
            Runnable customer = new Customer(this.ticketPool, config.getCustomerRetreivalRate());
            Thread customerThread = new Thread(customer, name );
            customerThread.start();
        }
    }

}
