package com.saajid.realtimeticketingapp.mainLogic;

import lombok.Data;

import java.util.UUID; // required for random id generation

/**
 * This class represents the ticket entity that will be released by vendors and purchased by customers
 */
@Data // auto generate getters & setters
public class Ticket {
    private String ticketID;

    /**
     * Constructor to initialize ticket
     */
    public Ticket () {
        // Generate a random ID with 8 characters
        this.ticketID = UUID.randomUUID().toString().substring(0,8);
    }
}
