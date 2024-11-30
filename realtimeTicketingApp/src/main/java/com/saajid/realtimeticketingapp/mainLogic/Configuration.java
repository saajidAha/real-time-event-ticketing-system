package com.saajid.realtimeticketingapp.mainLogic;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import static com.saajid.realtimeticketingapp.mainLogic.LoggerHandler.logInfo;

/**
 * This class is responsible for the configuration of how the user wants to simulate the ticketing system
 */
public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int numOfVendors;
    private int numOfCustomers;
    private static Logger logger = Logger.getLogger(Configuration.class.getName());


//      Static variable for the path to save any serialized files.
    public static final String PATH = "./src/main/java/com/saajid/realtimeticketingapp/data/";

//    add the handler just once when the class loads. (static initialization)
    static{
        logger.addHandler( LoggerHandler.getFileHandler() );
    }

    // Default constructor for Jackson deserialization
    public Configuration() {
    // this constructor is required for jackson to convert the json payload to object;
    }

    /**
     * Constructor to initialize the Configuration
     * @param totalTickets Total tickets in the ticket pool
     * @param ticketReleaseRate Frequency of tickets released by vendors (milliseconds)
     * @param customerRetrievalRate Frequency of tickets purchased by customers (milliseconds)
     * @param maxTicketCapacity Maximum number of tickets in the ticket pool
     * @param numOfVendors Number of vendors releasing tickets in the simulation
     * @param numOfCustomers Number of customers purchasing in the simulation
     */
    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, int numOfVendors, int numOfCustomers){
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.numOfVendors = numOfVendors;
        this.numOfCustomers = numOfCustomers;
    }

    /**
     * Constructor to initialize configuration
     * @param totalTickets Total tickets in the ticket pool
     * @param maxTicketCapacity Maximum number of tickets in the ticket pool
     */
    public Configuration(int totalTickets, int maxTicketCapacity){
        this.totalTickets = totalTickets;
        this.maxTicketCapacity = maxTicketCapacity;
    }


    /**
     * Maps object to JSON format, Text format and saves it to a File;
     */
    public void serialize(){
            Gson gson = new Gson();
        try{
            FileWriter writer = new FileWriter(PATH + "TicketConfig.json");
            gson.toJson(this, writer); writer.close();

            writer = new FileWriter(PATH + "TicketConfig.txt");
            String ticketLog = "Total Tickets: " + this.totalTickets  + ", Ticket Release Rate: " + this.ticketReleaseRate  + ", Customer Retrieval Rate: " +  this.customerRetrievalRate  + ", Maximum Ticket Capacity: " +  this.maxTicketCapacity  + ", Number of Vendors: " + this.numOfVendors  + ", Number of Customers: " + this.numOfCustomers;
            writer.write(ticketLog); writer.close();

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

    public static Logger getLogger() {
        return logger;
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

    public int getNumOfVendors() {
        return numOfVendors;
    }

    public int getNumOfCustomers() {
        return numOfCustomers;
    }

    public void setNumOfVendors(int numOfVendors) {
        this.numOfVendors = numOfVendors;
    }

    public void setNumOfCustomers(int numOfCustomers) {
        this.numOfCustomers = numOfCustomers;
    }

    public static void setLogger(Logger logger) {
        Configuration.logger = logger;
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
