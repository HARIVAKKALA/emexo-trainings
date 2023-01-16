package com.bugtracking.service;


import com.bugtracking.entity.Application;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface ApplicationService {
    List<Application> listApplications();
    Application findApplication(int id);

    CompletableFuture<Application> findApplicationById(int id);



    Optional<Application> save(Application application) throws Exception;

    Optional<Application> findApplicationByName(String name)throws Exception;

    CompletableFuture<Application> findApplicationByNameNew(String name)throws Exception;


    Optional<List<Application>> listSortedApps() throws Exception;

    void delete(int id) throws Exception;

    void clearByName();
    void clearById();


}


