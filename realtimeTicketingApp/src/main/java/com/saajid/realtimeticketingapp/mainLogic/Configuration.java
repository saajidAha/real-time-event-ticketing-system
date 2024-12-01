package com.saajid.realtimeticketingapp.mainLogic;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import static com.saajid.realtimeticketingapp.mainLogic.LoggerHandler.logInfo;

/**
 * This class is responsible for the configuration of how the user wants to simulate the ticketing system
 */
@Data
@NoArgsConstructor (force = true)// Default constructor for Jackson deserialization
@AllArgsConstructor // main constructor
@RequiredArgsConstructor // overloaded constructor (with the 2 final fields)
public class Configuration {
    private final int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private final int maxTicketCapacity;
    private int numOfVendors;
    private int numOfCustomers;
    private static Logger logger = Logger.getLogger(Configuration.class.getName());

//     path to save any serialized files.
    public static final String PATH = "./src/main/java/com/saajid/realtimeticketingapp/data/";

//    add the handler just once when the class loads. (static initialization)
    static{
        logger.addHandler( LoggerHandler.getFileHandler() );
    }

    /**
     * Maps object to JSON format and text format and saves it to a File;
     */
    public void serialize(){
            Gson gson = new Gson();
        try{
            PrintWriter writer = new PrintWriter(PATH + "TicketConfig.json");
            gson.toJson(this, writer);
            writer.flush(); // immediately writes to the file
            writer.close();

            writer = new PrintWriter(PATH + "TicketConfig.txt");
            String ticketLog = "Total Tickets: " + this.totalTickets  + ", Ticket Release Rate: " + this.ticketReleaseRate  + ", Customer Retrieval Rate: " +  this.customerRetrievalRate  + ", Maximum Ticket Capacity: " +  this.maxTicketCapacity  + ", Number of Vendors: " + this.numOfVendors  + ", Number of Customers: " + this.numOfCustomers;
            writer.println(ticketLog);
            writer.flush(); // immediately writes to the file
            writer.close();

            logInfo(logger, ticketLog, "INFO");
            logInfo(logger, "Serialized and saved Configuration files as TicketConfig.json & TicketConfig.txt Successfully", "INFO");
        }catch (IOException e){
            logInfo(logger,"Failed to serialize Configuration.", "SEVERE");
        }
    }

    /**
     * Maps JSON file to Configuration object
     */
    public static Configuration deSerialize(){
        Gson gson = new Gson();
        Configuration config = null;
        try{
            FileReader reader = new FileReader(PATH + "TicketConfig.json");
            config = gson.fromJson(reader, Configuration.class);

            logInfo(logger,"TicketConfig.json file de-serialized to Configuration object successfully", "INFO");
            reader.close();
        }catch (IOException e){
            logInfo(logger,"Unable to read file.", "SEVERE");
        }
        return config;
    }
}
