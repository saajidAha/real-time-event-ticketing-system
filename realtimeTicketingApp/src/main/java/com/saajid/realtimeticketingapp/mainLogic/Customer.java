package com.saajid.realtimeticketingapp.mainLogic;

import lombok.AllArgsConstructor;

/**
 * This class represents the Individual Customer thread logic
 */
@AllArgsConstructor // creates constructor and takes ticketpool as an argument
public class Customer implements Runnable{
    private TicketPool ticketPool;

    @Override
    public void run(){
        if (ticketPool.customerThreadCanRun){
            this.ticketPool.removeTicket(); // each customer purchases a single ticket
        }
    }
}
