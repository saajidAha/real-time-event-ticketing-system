package com.saajid.realtimeticketingapp.server;

import com.saajid.realtimeticketingapp.mainLogic.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Class responsible for proccessing requests received by the TicketController API
 */
@Service
public class TicketService {
    /**
     * Static TicketPool object for the purpose of being used as reference by specific methods
     */
    private static TicketPool ticketPool;

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
        ticketPool = new TicketPool(config);
    }

    /**
     * Adds a ticket to the ticketpool array
     * @param ticket Ticket object
     */
    public void addTicket(Ticket ticket){
        ticketPool.addTicket(ticket);
    }

    /**
     * Removes the first ticket from the ticketpool array
     */
    public void removeTicket(){
        ticketPool.removeTicket();
    }

    /**
     * Gets the list of tickets in current ticketpool
     * @return An array of tickets
     */
    public Vector<Ticket> getTicketsList(){
        return ticketPool.getTickets();
    }

    /**
     * Runs simulation based on the configuration
     * @param config Configuration object
     */
    public void simulate(Configuration config){
        LoggerHandler.clearLogs();
        config.serialize();
        Simulator simulator = new Simulator(config);
        simulator.simulate();
        ticketPool = simulator.getTicketPool();
    }
}
