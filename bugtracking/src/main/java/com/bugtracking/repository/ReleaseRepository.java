package com.bugtracking.repository;

import com.bugtracking.entity.Application;
import com.bugtracking.entity.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;

@Repository
public interface ReleaseRepository extends JpaRepository<Release,Integer> {
    Optional<Release> findById(Integer id);

}
