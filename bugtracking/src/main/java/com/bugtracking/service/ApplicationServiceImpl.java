package com.bugtracking.service;

import com.bugtracking.entity.Application;
import com.bugtracking.repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private static final Logger LOGGER= LoggerFactory.getLogger(ApplicationServiceImpl.class);

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    @PreAuthorize("hasRole('USER')")
    public List<Application> listApplications() {

        return (List<Application>) applicationRepository.findAll();
    }

    @Override
    public Application findApplication(int id) {
        LOGGER.info("Input to ApplicationServiceImpl.findApplication, id:{}", id);
        Optional<Application> application = applicationRepository.findById(id);
        if (application.isPresent()){
            Application app = application.get();
            LOGGER.info("Find application by id response:{}", app);
            return app;
        }
        return null;
    }

    @Override
    @Async
    @Cacheable("findApplicationById")
    public CompletableFuture<Application> findApplicationById(int id) {
        LOGGER.info("Input to ApplicationServiceImpl.findApplication, id:{}", id);
        Optional<Application> application = applicationRepository.findById(id);
        if (application.isPresent()){
            Application app = application.get();
            LOGGER.info("Find application by id response:{}", app);
            return CompletableFuture.completedFuture(app);
        }
        return null;
    }

    @Override
    public Optional<Application> findApplicationByName(String name) throws IllegalArgumentException {
        LOGGER.info("Input to ApplicationServiceImpl.findApplicationByName, name:{} ", name);
        if(name == null){
            throw new IllegalArgumentException("Invalid input");
        }
        Optional<Application> application = applicationRepository.findByName(name);
        LOGGER.info("Find application by name response: {}", application.get());
        if (application.isPresent()){
            return application;
        }
        return null;
    }

    @Override
    @Async
    @Cacheable("findApplicationByNameNew")
    public CompletableFuture<Application> findApplicationByNameNew(String name) throws IllegalArgumentException {
        LOGGER.info("Input to ApplicationServiceImpl.findApplicationByName, name:{} ", name);
        if(name == null){
            throw new IllegalArgumentException("Invalid input");
        }
        Optional<Application> application = applicationRepository.findByName(name);
        LOGGER.info("Find application by name response: {}", application.get());
        if (application.isPresent()){
            return CompletableFuture.completedFuture(application.get());
        }

        return null;
    }


    @Override
    public Optional<Application> save(Application application) throws Exception {
        LOGGER.info("Input to ApplicationServiceImpl.save, application:{}", application);
        Application application1 = null;
        int i = 0;
        try {
            application1 = (Application) applicationRepository.save(application);
        } catch (Exception ex){
            LOGGER.error("Exception while saving application", ex);
            throw new Exception("Exception while saving application");
        }

       return Optional.of(application1);
    }

    public Optional<List<Application>> listSortedApps() throws Exception {
        try{
             Optional<List<Application>> list = Optional.ofNullable(applicationRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
             return list;
        }catch (Exception e){
            LOGGER.error("Exception while saving application", e);
            throw new Exception("Exception while saving application");

        }
    }

    public void delete(int id){
        applicationRepository.deleteById(id);
    }

    @Override
    @CacheEvict("findApplicationById")
    public void clearById() {

    }

    @Override
    @CacheEvict("findApplicationByNameNew")
    public void clearByName() {

    }
}
