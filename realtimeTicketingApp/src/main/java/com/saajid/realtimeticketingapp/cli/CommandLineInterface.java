package com.saajid.realtimeticketingapp.cli;

import com.saajid.realtimeticketingapp.sharedResource.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineInterface implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Configuration config = new Configuration(104,5,4,100);

        config.serialize();

        Configuration newConfig = config.deSerialize();
        System.out.println(newConfig.getTotalTickets());
    }
}
