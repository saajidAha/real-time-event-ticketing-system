package com.saajid.realtimeticketingapp.mainLogic;

import lombok.AllArgsConstructor;

/**
 * This class represents the Customer threads
 */
@AllArgsConstructor
public class Customer implements Runnable{
    private TicketPool ticketPool;

    // each customer purchases a single ticket
    @Override
    public void run(){
        if (ticketPool.customerThreadCanRun){
            this.ticketPool.removeTicket();
        }
    }
}
