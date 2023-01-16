package com.bugtracking.service;


import com.bugtracking.entity.Application;
import com.bugtracking.entity.Release;

import java.util.List;
import java.util.Optional;

public interface ReleaseService {
    List<Release> listReleases();
    Release findRelease(int id);


    Release save(Release release) throws Exception;

    Optional<Release> findReleaseById(int id)throws Exception;

    Optional<List<Release>> listSortedReleases() throws Exception;

    void delete(int id) throws Exception;

}


