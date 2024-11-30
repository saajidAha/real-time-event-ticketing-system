package com.saajid.realtimeticketingapp;

import com.saajid.realtimeticketingapp.commandLine.CommandLineInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RealtimeTicketingAppApplication {

	public static void main(String[] args) {
//		Run the CLI until user wants to exit
		do{
			CommandLineInterface.init();
		}
		while(CommandLineInterface.checkYesNoResponse("Do you want to use the command line again?"));

//		Start the springboot server based on user response else terminate the program
		if (CommandLineInterface.checkYesNoResponse("Do you want to start the SpringBoot Server?")){
			SpringApplication.run(RealtimeTicketingAppApplication.class, args);
		}
		else{
			System.out.println("Program terminated successfully.");
		}
	}

}
