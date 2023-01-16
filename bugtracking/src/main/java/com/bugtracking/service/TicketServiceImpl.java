package com.bugtracking.service;

import com.bugtracking.entity.Release;
import com.bugtracking.entity.Ticket;
import com.bugtracking.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    private static final Logger LOGGER= LoggerFactory.getLogger(TicketServiceImpl.class);


    @Override
    public List<Ticket> listTickets() {
        return (List<Ticket>) ticketRepository.findAll();
    }

    @Override
    public Ticket findTicket(int id) {
        LOGGER.info("Input to TicketServiceImpl.findTicket, id:{}", id);
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()){
            Ticket ticket1 = ticket.get();
            LOGGER.info("Find application by id response:{}", ticket1);
            return ticket1;
        }
        return null;
    }

    @Override
    public Ticket save(Ticket ticket) throws Exception {
        LOGGER.info("Input to TicketServiceImpl.save, application:{}", ticket);
        Ticket ticket1 = null;
        int i = 0;
        try {
            ticket1 = (Ticket) ticketRepository.save(ticket);
        } catch (Exception ex){
            LOGGER.error("Exception while saving application", ex);
            throw new Exception("Exception while saving application");
        }

        return ticket1;
    }

    @Override
    public Optional<Ticket> findTicketByName(String name) throws Exception {
        LOGGER.info("Input to TicketServiceImpl.findTicketByName, name:{} ", name);
        if(name == null){
            throw new IllegalArgumentException("Invalid input");
        }
        Optional<Ticket> ticket = ticketRepository.findByLabel(name);
        LOGGER.info("Find Ticket by name response: {}", ticket.get());
        if (ticket.isPresent()){
            return ticket;
        }
        return null;
    }

    @Override
    public Optional<List<Ticket>> listSortedReleases() throws Exception {
        return Optional.of((List<Ticket>) ticketRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));

    }

    public void delete(int id){
        ticketRepository.deleteById(id);
    }


}
