package com.saajid.realtimeticketingapp.sharedResource;

/**
 * This class is responsible for the configuration of how the user wants to simulate the ticketing system
 */
public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetreivalRate;
    private int maxTicketCapacity;

    /**
     * Constructor
     * @param totalTickets Sets the total tickets in the ticket pool
     * @param ticketReleaseRate Sets the frequency tickets are released by vendors
     * @param customerRetreivalRate Sets the frequency tickets are purchased by customers
     * @param maxTicketCapacity Sets the maximum number of tickets in the ticket pool
     */
    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetreivalRate, int maxTicketCapacity){
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetreivalRate = customerRetreivalRate;
        this.maxTicketCapacity = maxTicketCapacity;
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
}
