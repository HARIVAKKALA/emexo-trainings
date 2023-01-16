package com.bugtracking.service;

import com.bugtracking.entity.Application;
import com.bugtracking.entity.Release;
import com.bugtracking.repository.ReleaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReleaseServiceImpl implements ReleaseService {
    private static final Logger LOGGER= LoggerFactory.getLogger(ReleaseServiceImpl.class);

    @Autowired
    private ReleaseRepository releaseRepository;

    @Override
    public List<Release> listReleases() {
        return (List<Release>) releaseRepository.findAll();
    }

    @Override
    public Release findRelease(int id) {
        LOGGER.info("Input to ReleaseServiceImpl.findRelease, id:{}", id);
        Optional<Release> application = releaseRepository.findById(id);
        if (application.isPresent()){
            Release release = application.get();
            LOGGER.info("Find application by id response:{}", release);
            return release;
        }
        return null;
    }

    @Override
    public Release save(Release release) throws Exception {
        LOGGER.info("Input to ReleaseServiceImpl.save, application:{}", release);
        Release release1 = null;
        int i = 0;
        try {
            release1 = (Release) releaseRepository.save(release);
        } catch (Exception ex){
            LOGGER.error("Exception while saving application", ex);
            throw new Exception("Exception while saving application");
        }

        return release1;
    }

    @Override
    public Optional<Release> findReleaseById(int id) throws Exception {
        LOGGER.info("Input to ReleaseServiceImpl.findApplicationByName, name:{} ", id);
        if(id == 0){
            throw new IllegalArgumentException("Invalid input");
        }
        Optional<Release> release = releaseRepository.findById(id);
        LOGGER.info("Find Release by name response: {}", release.get());
        if (release.isPresent()){
            return release;
        }
        return null;
    }

    @Override
    public Optional<List<Release>> listSortedReleases() throws Exception {
        return Optional.of((List<Release>) releaseRepository.findAll(Sort.by(Sort.Direction.ASC, "release_id")));

    }

    public void delete(int id){
        releaseRepository.deleteById(id);
    }


}
