package com.saajid.realtimeticketingapp.mainLogic;

public class Simulator {
    public static void main(String[] args) {

        Configuration config = Configuration.deSerialize();

        TicketPool ticketPool = new TicketPool(config);

        Runnable vendor = new Vendor(ticketPool);
        Thread vendorThread = new Thread(vendor, "Vendor 1");

        Runnable customer = new Customer(ticketPool);
        Thread customerThread = new Thread(customer, "Customer 1");

        Runnable customer2 = new Customer(ticketPool);
        Thread customerThread2 = new Thread(customer2, "Customer 2");

        Runnable customer3 = new Customer(ticketPool);
        Thread customerThread3 = new Thread(customer3, "Customer 3");

        vendorThread.start();
        customerThread.start();
        customerThread2.start();
        customerThread3.start();

    }
}
