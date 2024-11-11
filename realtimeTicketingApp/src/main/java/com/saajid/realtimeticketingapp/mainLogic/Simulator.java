package com.saajid.realtimeticketingapp.mainLogic;

import java.util.ArrayList;
import java.util.logging.Logger;

import static com.saajid.realtimeticketingapp.mainLogic.LoggerHandler.logInfo;


/**
 * This class is responsible for initializing and simulating multiple vendor and customer threads
 */
public class Simulator {
    private Configuration config;
    private TicketPool ticketPool;
    private ArrayList<Thread> vendorThreads;
    private ArrayList<Thread> customerThreads;
    private ArrayList<Thread> allThreads;
    private static Logger logger = Logger.getLogger(Simulator.class.getName());

    static{
        logger.addHandler(LoggerHandler.getFileHandler());
    }

    public Simulator(Configuration config){
        this.config = config;
        this.ticketPool = new TicketPool(config);
        this.vendorThreads = new ArrayList<>();
        this.customerThreads = new ArrayList<>();
        this.allThreads = new ArrayList<>();
    }

    /**
     * Simulates multithreaded environment where multiple customers and vendors interact with the ticket pool
     */
    public void simulate(){
        for (int i=1; i<=config.getNumOfVendors(); i++){
            String name = "Vendor_#" + i;
            Runnable vendor = new Vendor(this.ticketPool);
            Thread vendorThread = new Thread(vendor, name );
            vendorThread.start();

            vendorThreads.add(vendorThread);
            allThreads.add(vendorThread);
        }

        for (int i=1; i<=config.getNumOfCustomers(); i++){
            String name = "Customer_#" + i;
            Runnable customer = new Customer(this.ticketPool);
            Thread customerThread = new Thread(customer, name );
            customerThread.start();

            customerThreads.add(customerThread);
            allThreads.add(customerThread);
        }

        // makes all the threads to complete first before continuing to execute the rest of the code in the main method;
        for (Thread thread : allThreads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logInfo(logger, "issue while joining", "SEVERE");
            }
        }

        logInfo(logger,"Simulation execution completed successfully", "INFO");
        System.out.println(LoggerHandler.getLogs());

    }

    public TicketPool getTicketPool() {
        return ticketPool;
    }
}
