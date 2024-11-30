package com.saajid.realtimeticketingapp.commandLine;

import com.saajid.realtimeticketingapp.mainLogic.Configuration;
import com.saajid.realtimeticketingapp.mainLogic.LoggerHandler;
import com.saajid.realtimeticketingapp.mainLogic.Simulator;

import java.util.Scanner;
import java.util.logging.Logger;

import static com.saajid.realtimeticketingapp.mainLogic.LoggerHandler.logInfo;

/**
 * Utility class containing the logic to implement the core java CLI functionality
 */
public class CommandLineInterface {

//    initialize logger
    private static Logger logger = Logger.getLogger(CommandLineInterface.class.getName());

    static{
        logger.addHandler(LoggerHandler.getFileHandler());
    }

    /**
     * Initializes the command line interface
     */
    public static void initCLI() {

        System.out.println(getWelcomeMsg());

        // Prompt user for configuration
        int totalTickets = validatePositiveInt("Enter the total number of initial tickets");
        int ticketReleaseRate = validatePositiveInt("Enter the ticket release rate (in milliseconds)");
        int customerRetrievalRate = validatePositiveInt("Enter the customer retrieval rate (in milliseconds)");

        int maxTicketCapacity = validatePositiveInt("Enter the maximum ticket capacity");
        while (maxTicketCapacity < totalTickets) {
            System.out.println("Maximum ticket capacity cannot be less than the initial total tickets. please try again.");
            maxTicketCapacity = validatePositiveInt("Enter the maximum ticket capacity");
        }
        int numOfVendors = validatePositiveInt("Enter the number of vendors");
        int numOfCustomers = validatePositiveInt("Enter the number of customers");

        // Create config object
        Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, numOfVendors, numOfCustomers);
        config.serialize();

        // Start / Stop simulation
        if ( validateYesNo("Do you want to run the simulation?") ){
            Simulator simulator = new Simulator(config);
            logInfo(logger, "Simulation Request recieved from the CLI started successfully", "INFO");
            simulator.simulate(); // Run simulation
        } else{
            logInfo(logger, "System stopped as per requested.", "INFO");
        }
    }

    /**
     * Validates user inputs
     * @param prompt The prompt message to be displayed to the user
     * @return A positive integer
     */
    public static int validatePositiveInt(String prompt){
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
    public static boolean validateYesNo(String prompt){
        boolean startSystem = false;
        boolean validated = false;

        while (!validated){
            try{
                Scanner scanner = new Scanner(System.in);
                System.out.println(prompt + " Enter (Yes / No): ");
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

    /**
     * Method to display fancy welcome message
     * @return A fancy welcome message for the CLI
     */
    private static String getWelcomeMsg(){
        return
                "\n" +
                        "▀▀█▀▀ ▀█▀ ░█▀▀█ ░█─▄▀ ░█▀▀▀ ▀▀█▀▀ ▀█▀ ░█▄─░█ ░█▀▀█ 　 ░█▀▀█ ░█▀▀▀█ ░█▀▄▀█ ░█▀▄▀█ ─█▀▀█ ░█▄─░█ ░█▀▀▄ 　 ░█─── ▀█▀ ░█▄─░█ ░█▀▀▀ \n" +
                        "─░█── ░█─ ░█─── ░█▀▄─ ░█▀▀▀ ─░█── ░█─ ░█░█░█ ░█─▄▄ 　 ░█─── ░█──░█ ░█░█░█ ░█░█░█ ░█▄▄█ ░█░█░█ ░█─░█ 　 ░█─── ░█─ ░█░█░█ ░█▀▀▀ \n" +
                        "─░█── ▄█▄ ░█▄▄█ ░█─░█ ░█▄▄▄ ─░█── ▄█▄ ░█──▀█ ░█▄▄█ 　 ░█▄▄█ ░█▄▄▄█ ░█──░█ ░█──░█ ░█─░█ ░█──▀█ ░█▄▄▀ 　 ░█▄▄█ ▄█▄ ░█──▀█ ░█▄▄▄"
                +
                        "\n\n✨✨✨ Welcome to the Real-Time Event Ticketing System ⚡ Command Line Interface! ⚡ ✨✨✨\n"
                ;
    }

}
