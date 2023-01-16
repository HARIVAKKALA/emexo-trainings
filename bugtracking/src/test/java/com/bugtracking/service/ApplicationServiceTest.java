package com.bugtracking.service;

import com.bugtracking.entity.Application;
import com.bugtracking.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {
    @Mock
    ApplicationRepository applicationRepository;

    @InjectMocks
    ApplicationServiceImpl applicationService;

    @Test
    public void testListApplications()
    {
        List<Application> list = new ArrayList<>();
        Application application = new Application();
        application.setApplication_id(1);
        application.setName("LRI");
        application.setOwner("JPMC");
        application.setDescription("JPMC");
        list.add(application);
        when(applicationRepository.findAll()).thenReturn(list);
        List<Application> applicationList = applicationService.listApplications();
        assertEquals(1, applicationList.size());

    }


}