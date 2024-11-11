package com.saajid.realtimeticketingapp.commandLine;

import com.saajid.realtimeticketingapp.mainLogic.Configuration;
import com.saajid.realtimeticketingapp.mainLogic.LoggerHandler;
import com.saajid.realtimeticketingapp.mainLogic.Simulator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.logging.Logger;

import static com.saajid.realtimeticketingapp.mainLogic.LoggerHandler.logInfo;

/**
 * This class will have the logic to implement the main CLI functionality
 */
@Component
public class CommandLineInterface implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        // Prompt user for configuration
        int totalTickets = validatePositiveInt("Enter the total number of initial tickets");
        int ticketReleaseRate = validatePositiveInt("Enter the ticket release rate (in milliseconds)");
        int customerRetrievalRate = validatePositiveInt("Enter the customer retrieval rate (in milliseconds)");

        int maxTicketCapacity = validatePositiveInt("Enter the maximum ticket capacity");
        while (maxTicketCapacity < totalTickets) {
            System.out.println("Maximum ticket capacity cannot be less than the initial total tickets. please try again.");
            maxTicketCapacity = validatePositiveInt("Enter the maximum ticket capacity");
        }

        // Create config object
        Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        config.serialize();

//        add the handler for the logger
        Logger logger = Logger.getLogger(CommandLineInterface.class.getName());
        logger.addHandler( LoggerHandler.getFileHandler() );
        // Start / Stop system
        if ( validateYesNo() ){
            Simulator simulator = new Simulator(config);
            logInfo(logger, "Simulation Request recieved from the CLI started successfully", "INFO");

            // Run simulation
            simulator.simulate();
        } else{
            logInfo(logger, "System stopped as per requested.", "INFO");
        }
    }

    /**
     * Validates user inputs
     * @param prompt The prompt message to be displayed to the user
     * @return A positive integer
     */
    private static int validatePositiveInt(String prompt){
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
    private static boolean validateYesNo(){
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
