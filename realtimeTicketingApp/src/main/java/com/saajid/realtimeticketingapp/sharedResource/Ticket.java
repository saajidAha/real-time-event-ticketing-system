package com.saajid.realtimeticketingapp.sharedResource;

public class Ticket {
    private int ticketID;

    public Ticket(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }
}
