package com.saajid.realtimeticketingapp.mainLogic;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID; // required for random id generation

/**
 * This class represents the ticket entity that will be released by vendors and purchased by customers
 */
@Data // auto generate getters & setters
@NoArgsConstructor
public class Ticket {
    // Generate a random ID with 8 characters
    private String ticketID = UUID.randomUUID().toString().substring(0,8);

    @Override
    public String toString() {
        return ticketID;
    }
}
