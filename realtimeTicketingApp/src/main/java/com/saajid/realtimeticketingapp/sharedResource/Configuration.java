package com.saajid.realtimeticketingapp.sharedResource;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is responsible for the configuration of how the user wants to simulate the ticketing system
 */
public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetreivalRate;
    private int maxTicketCapacity;

    /**
     * Static variable for the path to serialize and deserialize the JSON configuration file.
     */
    private static final String PATH = "./src/main/java/com/saajid/realtimeticketingapp/sharedResource/";

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
        this.customerRetreivalRate = customerRetrievalRate;
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
            writer.write("Total Tickets: " + this.totalTickets + "\n" + "Ticket Release Rate: " + this.ticketReleaseRate + "\n" + "Customer Retrieval Rate: " +  this.customerRetreivalRate + "\n" + "Maximum Ticket Capacity: " +  this.maxTicketCapacity);
            System.out.println("Successfully serialized and saved Configuration files as TicketConfig.json & TicketConfig.txt");
            writer.close();
        }catch (IOException e){
            System.out.println("Failed to serialize Configuration.");
        }
    }

    /**
     * Maps JSON file to Configuration object
     */
    public Configuration deSerialize(){
        Configuration config = null;
        Gson gson = new Gson();
        try{
            FileReader reader = new FileReader(PATH + "TicketConfig.json");
            config = gson.fromJson(reader, Configuration.class);
            System.out.println("TicketConfig.json file de-serialized to Configuration object successfully");
            reader.close();
        }catch (IOException e){
            System.out.println("Unable to read file.");
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

    public int getCustomerRetreivalRate() {
        return customerRetreivalRate;
    }

    public void setCustomerRetreivalRate(int customerRetreivalRate) {
        this.customerRetreivalRate = customerRetreivalRate;
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
                ", customerRetreivalRate=" + customerRetreivalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                '}';
    }
}
