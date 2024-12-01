package com.saajid.realtimeticketingapp.server;

import com.saajid.realtimeticketingapp.mainLogic.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class responsible for handling CRUD operations with the database
 */
@Repository
public interface TransactionRepository extends JpaRepository<Ticket, String> {
//    all the neccessary database methods are already implemented by spring.
}
