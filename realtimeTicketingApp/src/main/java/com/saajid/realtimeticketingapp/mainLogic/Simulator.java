package com.saajid.realtimeticketingapp.mainLogic;

import com.saajid.realtimeticketingapp.server.TicketController;

import java.util.ArrayList;
import java.util.logging.Logger;

import static com.saajid.realtimeticketingapp.mainLogic.LoggerHandler.logInfo;


/**
 * This class is responsible for initializing and simulating multiple vendor and customer threads
 *
 */
public class Simulator {
    private Configuration config;
    private TicketPool ticketPool;
    private ArrayList<Thread> allThreads;
    private static Logger logger = Logger.getLogger(Simulator.class.getName());

    static{
        logger.addHandler(LoggerHandler.getFileHandler());
    }

    public Simulator(Configuration config){
        this.config = config;
        this.ticketPool = new TicketPool(config);
    }

    /**
     * Simulates multithreaded environment where multiple customers and vendors interact with the ticket pool
     */
    public void simulate() {
        // create runnables to simulate multiple vendors and customers
        Runnable runVendor = new VendorRunner(this.ticketPool);
        Runnable runCustomer = new CustomerRunner(this.ticketPool);
        // create threads
        Thread runCustomerThread = new Thread(runCustomer, "Run customer thread");
        Thread runVendorThread = new Thread(runVendor, "Run customer thread");
        // start threads
        runCustomerThread.start();
        runVendorThread.start();

        try {
        // make sure that all customer & vendor threads are executed before moving to the next lines of code
            runCustomerThread.join();
            runVendorThread.join();
        } catch (InterruptedException e) {
            logInfo(logger,"Error when joining.", "SEVERE");
        }
        logInfo(logger,"Simulation execution completed successfully", "INFO");
        System.out.println(LoggerHandler.getLogs());

    }

    public static void startThread(TicketPool ticketPool, String threadType, int timeout, int threadNum) {
        Runnable runnable = null;
        //create a runnable instance
        if (threadType.equalsIgnoreCase("Customer")) {
            runnable = new Customer(ticketPool);
        } else if (threadType.equalsIgnoreCase("Vendor")) {
            runnable = new Vendor(ticketPool);
        }
        Thread thread = new Thread(runnable, threadType + "_#" + threadNum); // create the thread
        thread.start();
        try {
            Thread.sleep(timeout); // sleep for the specified timeout
        } catch (InterruptedException e) {
            logInfo(logger,"Error occured while sleeping for specified timeout", "SEVERE");
        }

    }

    public TicketPool getTicketPool() {
        return ticketPool;
    }
}

//          VERY IMPORTANT USEFUL CODE
//        // Runnable interface implementation to insantiate multiple vendor threads
//        Runnable runVendor = new Runnable() {
//            @Override
//            public void run() {
//                for (int i=1; i<=config.getNumOfVendors(); i++){
//                    startThread("Vendor", config.getTicketReleaseRate(), i);
//                }
//            }
//        };
//
//        // Runnable interface implementation to insantiate multiple customer threads
//        Runnable runCustomer = new Runnable() {
//            @Override
//            public void run() {
//                for (int i=1; i<=config.getNumOfCustomers(); i++){
//                    startThread("Customer", config.getCustomerRetrievalRate(), i);
//                }
//            }
//        };
