package com.saajid.realtimeticketingapp.server;

import com.saajid.realtimeticketingapp.mainLogic.Configuration;
import com.saajid.realtimeticketingapp.mainLogic.LoggerHandler;
import com.saajid.realtimeticketingapp.mainLogic.Simulator;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.logging.Logger;

import static com.saajid.realtimeticketingapp.mainLogic.LoggerHandler.logInfo;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React dev server
@RequestMapping("/")
public class TicketController {
    private static Logger logger = Logger.getLogger(TicketController.class.getName());

    static{
        logger.addHandler(LoggerHandler.getFileHandler());
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello from springboot backend!";
    }

    @GetMapping("/simulate")
    public ArrayList<String> getName(){
        return LoggerHandler.getLogs();
    }

    @PostMapping("/simulate")
    public void simulate(@RequestBody Configuration config){
        logInfo(logger, "Simulation request recieved from the front-end successfully.","INFO");
        LoggerHandler.clearLogs();
        config.serialize();
        Simulator simulator = new Simulator(config);
        simulator.simulate();
    }
}
