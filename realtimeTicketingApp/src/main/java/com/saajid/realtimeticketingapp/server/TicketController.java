package com.saajid.realtimeticketingapp.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TicketController {

    @GetMapping("")
    public String getName(){
        return "<h1>Hello This springboot project is working!</h1>";
    }
}
