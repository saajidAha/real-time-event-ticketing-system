package com.saajid.realtimeticketingapp.mainLogic;

import lombok.Data;

import java.util.logging.Logger;

import static com.saajid.realtimeticketingapp.mainLogic.LoggerHandler.logInfo;

/**
 * This class is responsible for initializing and simulating multiple vendor and customer threads
 */
@Data // auto generate getters & setters
public class Simulator {
    private TicketPool ticketPool;
    private static Logger logger = Logger.getLogger(Simulator.class.getName());

    static{
        logger.addHandler(LoggerHandler.getFileHandler());
    }

    /**
     * Initalizes ticketpool with configuration
     * @param config Configuration object
     */
    public Simulator(Configuration config){
        this.ticketPool = new TicketPool(config);
    }

    /**
     * Simulates multithreaded environment where multiple customers and vendors interact with the ticket pool
     */
    public void start() {
        // create threads with runnable
        Thread customerRunner = new Thread( new CustomerRunner(this.ticketPool) , "Customer runner");
        Thread vendorRunner = new Thread( new VendorRunner(this.ticketPool) , "Vendor runner");

        // start threads
        customerRunner.start();
        vendorRunner.start();

        // makes sure that all customer & vendor threads are executed before moving to the next lines of code
        try {
            customerRunner.join();
            vendorRunner.join();
        } catch (InterruptedException e) {
            logInfo(logger,"Error when joining.", "SEVERE");
        }
        logInfo(logger,"Simulation execution completed successfully", "INFO");
    }
}
