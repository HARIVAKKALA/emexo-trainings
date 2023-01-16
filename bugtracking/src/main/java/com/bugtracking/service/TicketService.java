package com.bugtracking.service;


import com.bugtracking.entity.Release;
import com.bugtracking.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> listTickets();

    Ticket findTicket(int id);


    Ticket save(Ticket ticket) throws Exception;

    Optional<Ticket> findTicketByName(String name)throws Exception;

    Optional<List<Ticket>> listSortedReleases() throws Exception;

    void delete(int id) throws Exception;

}


