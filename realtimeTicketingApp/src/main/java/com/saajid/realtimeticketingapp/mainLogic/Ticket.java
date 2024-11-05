package com.saajid.realtimeticketingapp.mainLogic;

import java.util.UUID;

public class Ticket {
    private UUID ticketID;

    public Ticket () {
        this.ticketID = UUID.randomUUID();
    }

    public UUID getTicketID() {
        return ticketID;
    }

    public void setTicketID(UUID ticketID) {
        this.ticketID = ticketID;
    }

}
