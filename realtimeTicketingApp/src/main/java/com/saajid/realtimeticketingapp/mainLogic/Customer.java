package com.saajid.realtimeticketingapp.mainLogic;

import lombok.AllArgsConstructor;

/**
 * This class represents the Individual Customer thread logic
 */
@AllArgsConstructor
public class Customer implements Runnable{
    private TicketPool ticketPool;

    @Override
    public void run(){
        if (ticketPool.customerThreadCanRun){
            this.ticketPool.removeTicket(); // each customer purchases a single ticket
        }
    }
}
