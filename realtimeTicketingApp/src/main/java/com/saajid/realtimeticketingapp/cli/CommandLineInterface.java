package com.saajid.realtimeticketingapp.cli;

import com.saajid.realtimeticketingapp.sharedResource.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * This class will have the logic to implement the main CLI functionality
 */
@Component
public class CommandLineInterface implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        /*
         * Prompt user and initialize configuration;
         */
        int totalTickets = validateInput("Enter the total number of tickets");
        int ticketReleaseRate = validateInput("Enter the ticket release rate");
        int customerRetreivalRate = validateInput("Enter the customer retrieval rate");
        int maxTicketCapacity = validateInput("Enter the maximum ticket capcity");

        Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetreivalRate, maxTicketCapacity);
        config.serialize();
        Configuration value = config.deSerialize();
        System.out.println(value);



    }

    /**
     * Method that validates user inputs
     * @param promptMessage The prompt message to be displayed to the user
     * @return A positive integer
     */
    private static int validateInput(String promptMessage){
        int value = 0;
        boolean isValidated = false;

        while (!isValidated){
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println(promptMessage + ":   ");
                value = scanner.nextInt();
                if (value > 0){
                    isValidated = true;
                }else{
                    System.out.println("Input should be a positive integer.");
                }
            } catch (Exception e){
                System.out.println("Invalid input please try again.");
            }
        }
        return value;
    }
}
