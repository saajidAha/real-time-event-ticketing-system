package com.saajid.realtimeticketingapp.server;

import com.saajid.realtimeticketingapp.mainLogic.Configuration;
import com.saajid.realtimeticketingapp.mainLogic.LoggerHandler;
import com.saajid.realtimeticketingapp.mainLogic.Ticket;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Logger;

import static com.saajid.realtimeticketingapp.mainLogic.LoggerHandler.logInfo;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React dev server
@RequestMapping("/")
public class TicketController {
    private TicketService ticketService;
    private static Logger logger = Logger.getLogger(TicketController.class.getName());

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    static{
        logger.addHandler(LoggerHandler.getFileHandler());
    }

    @GetMapping("/logs")
    public ArrayList<String> getLogs(){
        return this.ticketService.getLogs();
    }

    @PostMapping("/tickets/add")
    public void addTicket(@RequestBody Ticket ticket){
        this.ticketService.addTicket(ticket);
    }

    @PostMapping("/tickets/remove")
    public void removeTicket(){
        this.ticketService.removeTicket();
    }

    @PostMapping("/tickets/createpool")
    public void createTicketPool(@RequestBody Configuration config){
        this.ticketService.createTicketPool(config);
    }

    @GetMapping("/tickets")
    public Vector<Ticket> getTicketsList(){
        return this.ticketService.getTicketsList();
    }

    @PostMapping("/simulate")
    public void simulate(@RequestBody Configuration config){
        logInfo(logger, "Simulation request recieved from the front-end successfully.","INFO");
        this.ticketService.simulate(config);
    }
}
