package com.saajid.realtimeticketingapp.mainLogic;

import com.saajid.realtimeticketingapp.commandLine.CommandLineInterface;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import static com.saajid.realtimeticketingapp.mainLogic.LoggerHandler.logInfo;


/**
 * Class to start the react server
 * Acknowledgement: The methodology to implement this react server initializer class was done with the assistance of generative AI.
 */
@Component
public class ReactServerInitializer {

    private Process reactProcess;

    private static Logger logger = Logger.getLogger(CommandLineInterface.class.getName());

    static{
        logger.addHandler(LoggerHandler.getFileHandler());
    }

    @PostConstruct
    public void startReactServer() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", "npm run dev");
        processBuilder.directory(new File("../ticketingFrontEnd"));

        try {
            reactProcess = processBuilder.start();
            logInfo(logger,"React server started successfully.", "Info");
        } catch (IOException e) {
            logInfo(logger,"Failed to start React server: " + e.getMessage(), "Severe");
        }
    }

    /**
     * Stops the react server and kills the port:3000 when program terminates
     */
    @PreDestroy
    public void stopReactServer() {
        if (reactProcess != null) {
            logInfo(logger,"Stopping React server...", "Info");
            // Destroy forcibly
            reactProcess.descendants().forEach(ProcessHandle::destroy); // Kill child processes
            reactProcess.destroy();
            logInfo(logger,"React server stopped.", "Info");
        }
    }
}

