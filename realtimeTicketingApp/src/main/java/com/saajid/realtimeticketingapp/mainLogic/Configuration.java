package com.saajid.realtimeticketingapp.mainLogic;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * This class is responsible for the configuration of how the user wants to simulate the ticketing system
 */
public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private static Logger logger = Logger.getLogger(Configuration.class.getName());


//      Static variable for the path to save any serialized files.
    public static final String PATH = "./src/main/java/com/saajid/realtimeticketingapp/data/";

//    add the handler just once when the class loads. (static initialization)
    static{
        logger.addHandler( LoggerHandler.getFileHandler() );
    }

    /**
     * Constructor
     * @param totalTickets Sets the total tickets in the ticket pool
     * @param ticketReleaseRate Sets the frequency tickets are released by vendors
     * @param customerRetrievalRate Sets the frequency tickets are purchased by customers
     * @param maxTicketCapacity Sets the maximum number of tickets in the ticket pool
     */
    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity){
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        serialize();
    }



    /**
     * Maps object to JSON format, Text format and saves it to a File;
     */
    public void serialize(){
            Gson gson = new Gson();
        try{
            FileWriter writer = new FileWriter(PATH + "TicketConfig.json");
            gson.toJson(this, writer);
            writer.close();

            writer = new FileWriter(PATH + "TicketConfig.txt");
            writer.write("Total Tickets: " + this.totalTickets + "\n" + "Ticket Release Rate: " + this.ticketReleaseRate + "\n" + "Customer Retrieval Rate: " +  this.customerRetrievalRate + "\n" + "Maximum Ticket Capacity: " +  this.maxTicketCapacity);
            writer.close();

            logger.log(Level.INFO, "Configuration: Total initial Tickets: " + this.totalTickets + ", Ticket Release Rate: " + this.ticketReleaseRate + ", Customer Retreival Rate: " + this.customerRetrievalRate + ", Maximum Ticket Capacity: "  + this.maxTicketCapacity);
            logger.log(Level.INFO,"Serialized and saved Configuration files as TicketConfig.json & TicketConfig.txt Successfully");
        }catch (IOException e){
            logger.log(Level.SEVERE,"Failed to serialize Configuration.");
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
            logger.log(Level.INFO,"TicketConfig.json file de-serialized to Configuration object successfully");
            reader.close();
        }catch (IOException e){
            logger.log(Level.SEVERE,"Unable to read file.");
        }
        return config;
    }


    /**
     * Getters and Setters
     */
    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "totalTickets=" + totalTickets +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetreivalRate=" + customerRetrievalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                '}';
    }
}
