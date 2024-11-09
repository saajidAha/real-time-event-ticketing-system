package com.saajid.realtimeticketingapp.mainLogic;

import java.util.ArrayList;

/**
 * This class is responsible for initializing and simulating multiple vendor and customer threads
 */
public class Simulator {
    private TicketPool ticketPool;
    private ArrayList<Thread> vendorThreads;
    private ArrayList<Thread> customerThreads;
    private ArrayList<Thread> allThreads;

    public Simulator(Configuration config){
        this.ticketPool = new TicketPool(config);
        this.vendorThreads = new ArrayList<>();
        this.customerThreads = new ArrayList<>();
        this.allThreads = new ArrayList<>();
    }

    /**
     * Simulates multithreaded environment where multiple customers and vendors interact with the ticket pool
     * @param numOfVendors The number of vendors that are going to be releasing tickets
     * @param numOfCustomers The number of customers that are going to purchase tickets
     */
    public void simulate(int numOfVendors, int numOfCustomers){
        for (int i=1; i<=numOfVendors; i++){
            String name = "Vendor_#" + i;
            Runnable vendor = new Vendor(this.ticketPool);
            Thread vendorThread = new Thread(vendor, name );
            vendorThreads.add(vendorThread);
            allThreads.add(vendorThread);
            vendorThread.start();
        }

        for (int i=1; i<=numOfCustomers; i++){
            String name = "Customer_#" + i;
            Runnable customer = new Customer(this.ticketPool);
            Thread customerThread = new Thread(customer, name );

            customerThreads.add(customerThread);
            allThreads.add(customerThread);
            customerThread.start();
        }

        // makes all the threads to complete first before continuing to execute the rest of the code in the main method;
        for (Thread eachThread: allThreads){
            try {
                eachThread.join();
            } catch (InterruptedException e) {
                System.out.println("issue while joining");
            }
        }

        System.out.println("Simulation execution completed successfully.");


    }

}
