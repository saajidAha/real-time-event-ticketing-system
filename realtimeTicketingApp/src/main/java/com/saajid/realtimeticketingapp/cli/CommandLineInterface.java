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
        // Prompt user
        int totalTickets = validateConfig("Enter the total number of tickets");
        int ticketReleaseRate = validateConfig("Enter the ticket release rate");
        int customerRetreivalRate = validateConfig("Enter the customer retrieval rate");
        int maxTicketCapacity = validateConfig("Enter the maximum ticket capcity");

        // Serialize
        Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetreivalRate, maxTicketCapacity);
        config.serialize();

        // Start / Stop system
        if ( validateSystemStart() ){
            System.out.println("System started successfully");
        } else{
            System.out.println("System process aborted.");
        }

    }

    /**
     * Validates user inputs
     * @param prompt The prompt message to be displayed to the user
     * @return A positive integer
     */
    private static int validateConfig(String prompt){
        int response = 0;
        boolean isValidated = false;

        while (!isValidated){
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println(prompt + ":   ");
                response = scanner.nextInt();
                if (response > 0){
                    isValidated = true;
                }else{
                    System.out.println("Input should be a positive integer.");
                }
            } catch (Exception e){
                System.out.println("Invalid input please try again.");
            }
        }
        return response;
    }

    /**
     * Validates user input to start / stop system
     * @return True if response is "yes", false if "no"
     */
    private static boolean validateSystemStart(){
        boolean startSystem = false;
        boolean validated = false;

        while (!validated){
            try{
                Scanner scanner = new Scanner(System.in);
                System.out.println("Do you want to start the system? Enter (Yes / No): ");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("yes")){
                    validated = true;
                    startSystem = true;
                } else if (response.equalsIgnoreCase("no")) {
                    validated = true;
                } else {
                    System.out.println("Invalid response. Please type 'yes' or 'no'");
                }
            }catch (Exception e){
                System.out.println("Invalid response. Please type 'yes' or 'no'");
            }
        }
        return startSystem;
    }
}
