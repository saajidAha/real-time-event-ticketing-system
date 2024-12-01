package com.saajid.realtimeticketingapp.mainLogic;

import lombok.Data;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * This class is solely for the purpose of creating a single FileHandler and using it across the program.
 */
@Data // auto generate getters & setters
public class LoggerHandler {
    private static Logger logger = Logger.getLogger(LoggerHandler.class.getName());
    @Getter
    private static FileHandler fileHandler;
    @Getter
    private static ArrayList<String> logs = new ArrayList<>(); // this will store the logs to display to the frontend;

//    Runs this code only ONCE when the program is run. because we need only one filehandler for all the classes;
    static{
        try {
            fileHandler = new FileHandler(Configuration.PATH + "SessionLog.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occured while creation of logger");
        }
    }

    /**
     * Handles logging info and storing the logs for future use
     * @param message The Log message
     * @param level The severity level (INFO, WARNING, SEVERE)
     */
    public static void logInfo(Logger logger, String message, String level){
        if (level.equalsIgnoreCase("INFO")){
            logger.log(Level.INFO, message);
        }if(level.equalsIgnoreCase("WARNING")){
            logger.log(Level.WARNING, message);
        }else if (level.equalsIgnoreCase("SEVERE")){
            logger.log(Level.SEVERE, message);
        }
        logs.add(message);
    }

    public static void clearLogs(){
        logs.clear();
        logInfo(logger, "Cleared previous logs successfully", "INFO");
    }


}
