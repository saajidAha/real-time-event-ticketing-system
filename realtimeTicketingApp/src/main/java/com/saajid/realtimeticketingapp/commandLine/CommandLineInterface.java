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
     * Initializes & starts the command line interface
     */
    public static void start() {
        System.out.println(getWelcomeMsg());

        // Prompt user for configuration
        int totalTickets = validateNum("Enter the total number of initial tickets", 0);
        int ticketReleaseRate = validateNum("Enter the ticket release rate (in milliseconds)", 0);
        int customerRetrievalRate = validateNum("Enter the customer retrieval rate (in milliseconds)", 0);

        int maxTicketCapacity = validateNum("Enter the maximum ticket capacity", 1);
        while (maxTicketCapacity < totalTickets) {
            System.out.println("Maximum ticket capacity cannot be less than the initial total tickets. please try again.");
            maxTicketCapacity = validateNum("Enter the maximum ticket capacity", 1);
        }
        int numOfVendors = validateNum("Enter the number of vendors", 1);
        int numOfCustomers = validateNum("Enter the number of customers", 1);

        // Create config object
        Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, numOfVendors, numOfCustomers);
        config.serialize();

        // Start / Stop simulation
        if ( checkYesNoResponse("Do you want to run the simulation?") ){
            Simulator simulator = new Simulator(config);
            logInfo(logger, "Simulation Request recieved from the CLI started successfully", "INFO");
            simulator.start(); // Run simulation
        } else{
            logInfo(logger, "System stopped as per requested.", "INFO");
        }
    }

    /**
     * Validates user inputs to check if it is greater the given number
     * @param prompt The prompt message to be displayed to the user
     * @return An integer greater than the specified minimum number
     */
    public static int validateNum(String prompt, int minNum){
        int userNum = 0;
        boolean validated = false;

        while (!validated){
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println(prompt + ":   ");
                userNum = scanner.nextInt();
                if (userNum >= minNum){
                    validated = true;
                }else{
                    System.out.println("Input should be a greater than or equal to " + minNum);
                }
            } catch (Exception e){
                System.out.println("Invalid input please try again.");
            }
        }
        return userNum;
    }

    /**
     * Validates user input for the binary choices of 'yes' or 'no'
     * @return True if response is "yes", false if "no"
     */
    public static boolean checkYesNoResponse(String prompt){
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
