package com.saajid.realtimeticketingapp.mainLogic;

import lombok.AllArgsConstructor;

/**
 * This class represents the Vendor threads
 */
@AllArgsConstructor
public class Vendor implements Runnable{
    private TicketPool ticketPool;

//    each vendor releases a single ticket
    @Override
    public void run(){
        if(ticketPool.vendorThreadCanRun){
            this.ticketPool.addTicket( new Ticket() );
        }
    }
}
