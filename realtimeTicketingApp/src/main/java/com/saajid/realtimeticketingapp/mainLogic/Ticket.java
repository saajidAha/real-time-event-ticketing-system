package com.saajid.realtimeticketingapp.mainLogic;

import java.util.UUID;

public class Ticket {
    private String ticketID;

    public Ticket () {
        this.ticketID = UUID.randomUUID().toString().substring(0,8);
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    @Override
    public String toString() {
        return ticketID;
    }
}
