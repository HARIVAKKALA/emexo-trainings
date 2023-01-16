package com.bugtracking.repository;

import com.bugtracking.entity.Release;
import com.bugtracking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    Optional<Ticket> findByLabel(String label);

}
