package com.saajid.realtimeticketingapp.mainLogic;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Class to start the react server
 * Acknowledgement: The methodology to implement this react server initializer class was done with the assistance of generative AI.
 */
@Component
public class ReactServerInitializer {

    private Process reactProcess;

    @PostConstruct
    public void startReactServer() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", "npm run dev");
        processBuilder.directory(new File("../ticketingFrontEnd"));

        try {
            reactProcess = processBuilder.start();
            System.out.println("React server started successfully.");
        } catch (IOException e) {
            System.err.println("Failed to start React server: " + e.getMessage());
        }
    }

    /**
     * Stops the react server and kills the port:3000 when program terminates
     */
    @PreDestroy
    public void stopReactServer() {
        if (reactProcess != null) {
            System.out.println("Stopping React server...");
            // Destroy forcibly
            reactProcess.descendants().forEach(ProcessHandle::destroy); // Kill child processes
            reactProcess.destroy();
            System.out.println("React server stopped.");
        }
    }
}

