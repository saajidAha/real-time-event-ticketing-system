package com.saajid.realtimeticketingapp.mainLogic;

import lombok.AllArgsConstructor;

/**
 * This class represents the Individual Vendor thread logic
 */
@AllArgsConstructor // creates constructor and takes ticketPool as an argument
public class Vendor implements Runnable{
    private TicketPool ticketPool;

    @Override
    public void run(){
        if(ticketPool.vendorThreadCanRun){
            this.ticketPool.addTicket( new Ticket() ); // each vendor releases a single ticket
        }
    }
}
