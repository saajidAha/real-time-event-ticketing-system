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
        config.serialize();
        ticketPool = new TicketPool(config);
    }

    /**
     * Adds a ticket to the ticketpool array
     * @param ticket Ticket object
     */
    public void addTicket(Ticket ticket){
        if (ticketPool != null){
            ticketPool.addTicket(ticket);
            transactionRepository.save( new Transaction( ticket.getTicketID(), "Ticket Release", "Front-end User" ) );
        }else{
            logInfo(logger, "Cannot release ticket due to no existing ticket pool", "Warning");
        }
    }

    /**
     * Removes the first ticket from the ticketpool array
     */
    public void removeTicket(){
        Ticket removedTicket = null;
        if (ticketPool != null){
            removedTicket = ticketPool.removeTicket();
        }else{
            logInfo(logger, "Cannot purchase ticket due to no existing ticket pool", "Warning");
        }
        if(removedTicket != null){
            transactionRepository.save( new Transaction(removedTicket.getTicketID(), "Ticket Purchase", "Front-end User") );
        }
    }

    /**
     * Gets the list of tickets in current ticketpool
     * @return An array of tickets
     */
    public List<Ticket> getTicketsList(){
        return ticketPool.getTickets();
    }

    /**
     * Runs simulation based on the configuration
     * @param config Configuration object
     */
    public void simulate(Configuration config){
        LoggerHandler.clearLogs();
        config.serialize();
        // start simulation
        Simulator simulator = new Simulator(config);
        simulator.start();
        ticketPool = simulator.getTicketPool();

        // save all of the tickets that was created during the simulation to the database
        transactionRepository.saveAll(ticketPool.getTransactions());
    }
}
