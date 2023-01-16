package com.bugtracking.repository;

import com.bugtracking.entity.Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class ApplicationRepositoryTest {
    @Autowired
    public ApplicationRepository applicationRepository;
    @Test
    public void testSaveApplication(){
        Application application = new Application();
        application.setName("LRI");
        application.setOwner("JPMC");
        application.setDescription("JPMC");
        Application application1 = applicationRepository.save(application);
        Optional<Application> application2 = applicationRepository.findById(1);
        if (application2.isPresent()){
            Application app = application2.get();
            assertEquals(app.getName(),application1.getName());

        }

    }

}