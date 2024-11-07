package com.saajid.realtimeticketingapp.mainLogic;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * This class is solely for the purpose of creating a single FileHandler and using it across the program.
 */
public class LoggerHandler {
    private static Logger logger = Logger.getLogger(LoggerHandler.class.getName());
    private static FileHandler fileHandler;

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
     * @return a single FileHandler
     */
    public static FileHandler getFileHandler(){
        return fileHandler;
    }


}
