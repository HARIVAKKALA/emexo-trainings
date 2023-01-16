package com.bugtracking.controller;

import com.bugtracking.entity.Application;
import com.bugtracking.entity.Release;
import com.bugtracking.service.ApplicationService;
import com.bugtracking.service.ReleaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/releases")
public class ReleaseController {
    public Logger logger = LoggerFactory.getLogger("ReleaseController.class");
    @Autowired
    public ReleaseService releaseService;


    @GetMapping("/")
    public ResponseEntity findAllReleases(){
        logger.info("findAllApplications");
        try {
            List<Release> releases = releaseService.listReleases();
            if (releases.size() > 0) {
                return new ResponseEntity<List<Release>>(releases, HttpStatus.OK);

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
            Release release = releaseService.findRelease(Integer.parseInt(id));
            return new ResponseEntity(release,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error....{}"+ e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Release release){
        logger.info("application..."+ release.toString());
        try{
            Release i = releaseService.save(release);
            return new ResponseEntity("record inserted successfully",HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error....{}"+ e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByName")
    public ResponseEntity findByName(@RequestParam(name = "id")int id){
        logger.info("id..."+ id);
        try{
            Optional<Release> i = releaseService.findReleaseById(id);
            return new ResponseEntity(i,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error....{}"+ e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findSortedApps")
    public ResponseEntity findSortedApps(){
        try{
            Optional<List<Release>> list = releaseService.listSortedReleases();
            return new ResponseEntity(list,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error....{}"+ e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping
    public ResponseEntity updateApplication(@RequestBody Release release){
        logger.info("updating release..{}",release);
        try{
            Optional<Release> release1 = Optional.ofNullable(releaseService.save(release));
            logger.info("Application updated successfully");
            return new ResponseEntity(release1,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Exception...{}",e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteApplication(@RequestParam("id") int id){
        logger.info("Deleting application with Id..{}",id);
        try {
            releaseService.delete(id);
            logger.info("record deleted successfully");
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            logger.error("Exception:{}",e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
