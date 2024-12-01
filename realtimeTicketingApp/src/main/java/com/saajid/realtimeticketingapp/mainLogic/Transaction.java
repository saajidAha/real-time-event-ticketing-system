package com.saajid.realtimeticketingapp.mainLogic;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Class representing transaction details (Used to store in database)
 */
@Entity
@NoArgsConstructor (force = true) // generate no argument constructor (because required for JPA repository) (force=true makes sure that null values are assigned to the fields)
@RequiredArgsConstructor // generate constructor with all arguments for the instance variables
public class Transaction {
    @Id
    private String transactionID = UUID.randomUUID().toString().substring(0,8); // Generate a random ID with 4 characters
    private String transactionDateTime = LocalDateTime.now().format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") ); // formatted current date and time
    private final String ticketID;
    private final String transactionType;
    private final String threadName;
}
