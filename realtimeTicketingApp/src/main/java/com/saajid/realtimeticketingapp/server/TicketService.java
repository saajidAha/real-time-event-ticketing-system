package com.saajid.realtimeticketingapp.server;

import com.saajid.realtimeticketingapp.mainLogic.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.saajid.realtimeticketingapp.mainLogic.LoggerHandler.logInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class responsible for processing requests received by the TicketController API
 */
@Service
@RequiredArgsConstructor // creates constructor with arguments for final fields
public class TicketService {
     //Static TicketPool object for the purpose of being used as reference by specific methods
    private static TicketPool ticketPool;

    private final TransactionRepository transactionRepository; // automatic dependency injection by @RequiredArgsConstructor

    private static Logger logger = Logger.getLogger(TicketService.class.getName());

    static{
        logger.addHandler(LoggerHandler.getFileHandler());
    }

    /**
     * Gets the logs and returns it
     * @return Logs related to the system activity
     */
    public ArrayList<String> getLogs(){
        return LoggerHandler.getLogs();
    }

    /**
     * Creates a TicketPool object
     * @param config Configuration object
     */
    public void createTicketPool(Configuration config){
        LoggerHandler.clearLogs();
        config.serialize();
        ticketPool = new TicketPool(config);
    }

    /**
     * Adds a ticket to the ticketpool array
     * @param ticket Ticket object
     */
    public void addTicket(Ticket ticket){
        if ( checkTicketPoolExists() ){
            ticketPool.addTicket(ticket);
            transactionRepository.save( new Transaction( ticket.getTicketID(), "Ticket Release", "Front-end User" ) );
        }else{
            logInfo(logger, "Cannot release ticket due to no existing ticket pool", "Warning");
        }
    }

    /**
     * Removes a ticket based on the recieved ticket id
     * @param ticketID The ticket id of the ticket object
     */
    public void removeTicket(String ticketID){
        if ( checkTicketPoolExists() ){
            if( ticketPool.removeTicket(ticketID) ){
                transactionRepository.save(new Transaction(ticketID, "Ticket Purchase", "Front-end User"));
            }
        } else{
            logInfo(logger, "Cannot purchase ticket due to no existing ticket pool", "Warning");
        }
    }

    /**
     * Gets the list of tickets in current ticketpool
     * @return An array of tickets
     */
    public List<Ticket> getTicketsList(){
        List<Ticket> tickets = new ArrayList<>();
       if( ticketPool != null ){
           tickets = ticketPool.getTickets();
       }
       return tickets;
    }

    /**
     * Runs simulation based on the configuration
     * @param config Configuration object
     */
    public synchronized void simulate(Configuration config){
        LoggerHandler.clearLogs();
        config.serialize();
        // start simulation
        Simulator simulator = new Simulator(config);
        ticketPool = simulator.getTicketPool();
        simulator.start();

        // save all of the tickets that was created during the simulation to the database
        transactionRepository.saveAll(ticketPool.getTransactions());
    }

    /**
     * Check if the ticketpool is not null and is existing and logs the info.
     * @return A boolean value of true if the ticketpool is in existence. else false.
     */
    private static boolean checkTicketPoolExists(){
        return ticketPool != null;
    }

    /**
     * Stop the running threads
     */
    public void stopThreads(){
        ticketPool.setCustomerThreadCanRun(false);
        ticketPool.setVendorThreadCanRun(false);
        logInfo(logger, "Vendor & Customer threads have been STOPPED As per request from the front-end.", "WARNING");
    }

    /**
     * Resets the ticket pool
     */
    public void resetTicketPool(){
        if (checkTicketPoolExists()){
            ticketPool = null;
            LoggerHandler.clearLogs();
            logInfo(logger, "TicketPool RESET successful.", "INFO");
        }
    }
}
