package com.saajid.realtimeticketingapp.server;

import com.saajid.realtimeticketingapp.mainLogic.Configuration;
import com.saajid.realtimeticketingapp.mainLogic.LoggerHandler;
import com.saajid.realtimeticketingapp.mainLogic.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.saajid.realtimeticketingapp.mainLogic.LoggerHandler.logInfo;

/**
 * Class (API) responsible for handling HTTP requests from the front-end
 */
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React dev server
@RequestMapping("/")
public class TicketController {

    private final TicketService ticketService;// automatic dependency injection by @RequiredArgsConstructor

    private static Logger logger = Logger.getLogger(TicketController.class.getName());

    static{
        logger.addHandler(LoggerHandler.getFileHandler());
    }

    /**
     * Gets the logs and returns it
     * @return Logs related to the system activity
     */
    @GetMapping("/logs")
    public ArrayList<String> getLogs(){
        return this.ticketService.getLogs();
    }

    /**
     * Creates a TicketPool object
     * @param config Configuration object
     */
    @PostMapping("/tickets/createpool")
    public void createTicketPool(@RequestBody Configuration config){
        this.ticketService.createTicketPool(config);
    }

    /**
     * Adds a ticket to the ticketpool array
     * @param ticket Ticket object
     */
    @PostMapping("/tickets/add")
    public void addTicket(@RequestBody Ticket ticket){
        this.ticketService.addTicket(ticket);
    }

    /**
     * Removes the first ticket from the ticketpool array
     */
    @PostMapping("/tickets/remove")
    public void removeTicket(){
        this.ticketService.removeTicket();
    }

    /**
     * Gets the list of tickets in current ticketpool
     * @return An array of tickets
     */
    @GetMapping("/tickets")
    public List<Ticket> getTicketsList(){
        return this.ticketService.getTicketsList();
    }

    /**
     * Runs simulation based on the configuration
     * @param config Configuration object
     */
    @PostMapping("/simulate")
    public void simulate(@RequestBody Configuration config){
        logInfo(logger, "Simulation request recieved from the front-end successfully.","INFO");
        this.ticketService.simulate(config);
    }
}
