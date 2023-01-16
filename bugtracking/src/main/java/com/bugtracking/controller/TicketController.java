package com.bugtracking.controller;

import com.bugtracking.entity.Release;
import com.bugtracking.entity.Ticket;
import com.bugtracking.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/tickets")
public class TicketController {
    public Logger logger = LoggerFactory.getLogger("TicketController.class");
    @Autowired
    public TicketService ticketService;

    @GetMapping("/")
    public ResponseEntity findAllTickets(){
        logger.info("findAllTickets");
        try {
            List<Ticket> tickets = ticketService.listTickets();
            if (tickets.size() > 0) {
                return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            logger.error("Error....{}"+ e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity findApplicationById(@PathVariable("id")String id){
        logger.info("Id..."+ id);
        try{
            Ticket ticket = ticketService.findTicket(Integer.parseInt(id));
            return new ResponseEntity(ticket,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error....{}"+ e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Ticket ticket){
        logger.info("ticket..."+ ticket.toString());
        try{
            Ticket i = ticketService.save(ticket);
            return new ResponseEntity("record inserted successfully",HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error....{}"+ e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByName")
    public ResponseEntity findByName(@RequestParam(name = "name")String name){
        logger.info("name..."+ name);
        try{
            Optional<Ticket> i = ticketService.findTicketByName(name);
            return new ResponseEntity(i,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error....{}"+ e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @GetMapping("/findSortedApps")
    public ResponseEntity findSortedApps(){
        try{
            Optional<List<Ticket>> list = ticketService.listSortedReleases();
            return new ResponseEntity(list,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error....{}"+ e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping
    public ResponseEntity updateApplication(@RequestBody Ticket ticket){
        logger.info("updating release..{}",ticket);
        try{
            Optional<Ticket> ticket1 = Optional.ofNullable(ticketService.save(ticket));
            logger.info("Application updated successfully");
            return new ResponseEntity(ticket1,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Exception...{}",e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteApplication(@RequestParam("id") int id){
        logger.info("Deleting application with Id..{}",id);
        try {
            ticketService.delete(id);
            logger.info("record deleted successfully");
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            logger.error("Exception:{}",e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
