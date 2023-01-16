package com.bugtracking.controller;

import com.bugtracking.entity.Application;
import com.bugtracking.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/applications")
public class ApplicationController {

    @Autowired
    public ApplicationService applicationService;

    public Logger logger = LoggerFactory.getLogger("ApplicationController.class");
    @GetMapping("/")
    public  ResponseEntity findAllApplications(){
        logger.info("findAllApplications");
        try {
            List<Application> applications = applicationService.listApplications();
            if (applications.size() > 0) {
                return new ResponseEntity<List<Application>>(applications, HttpStatus.OK);

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
            Application application = applicationService.findApplication(Integer.parseInt(id));
            return new ResponseEntity(application,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error....{}"+ e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/findByName")
    public ResponseEntity findByName(@RequestParam(name = "name")String name){
        logger.info("name..."+ name);
        try{
            Optional<Application> i = applicationService.findApplicationByName(name);
            return new ResponseEntity(i,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error....{}"+ e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @GetMapping("/findSortedApps")
    public ResponseEntity findSortedApps(){
        try{
            Optional<List<Application>> list = applicationService.listSortedApps();
            return new ResponseEntity(list,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error....{}"+ e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping
    public ResponseEntity saveApplication(@RequestBody Application application){
        logger.info("trying to save application",application.toString());
        if (application == null){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

            try{
                Optional<Application> application1 =applicationService.save(application);
                logger.info("Application saved successfully");
                return new ResponseEntity(application1,HttpStatus.OK);

            }catch (Exception e){
                logger.error("Exception...{}",e);
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

            }
    }

    @PutMapping
    public ResponseEntity updateApplication(@RequestBody Application application){
        logger.info("updating application..{}",application);
        try{
            Optional<Application> application1 =applicationService.save(application);
            logger.info("Application updated successfully");
            return new ResponseEntity(application1,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Exception...{}",e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteApplication(@RequestParam("id") int id){
        logger.info("Deleting application with Id..{}",id);
        try {
            applicationService.delete(id);
            logger.info("record deleted successfully");
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            logger.error("Exception:{}",e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/findByIdAndName")
    public ResponseEntity findByIdAndName(@RequestParam("id") int id,@RequestParam("name") String name){
        ArrayList l = null;
        try {
            l = new ArrayList();
            CompletableFuture<Application> a1 = applicationService.findApplicationById(id);
            CompletableFuture<Application> a2 = applicationService.findApplicationByNameNew(name);
            CompletableFuture.allOf(a1,a2).join();
            l.add(a1.get());
            l.add(a2.get());


        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(l,HttpStatus.OK);
    }
    @GetMapping("/clearCache")
    public ResponseEntity clearCache(){
        applicationService.clearByName();
        applicationService.clearById();
        return new ResponseEntity<>("cleared cache",HttpStatus.OK);
    }


}
