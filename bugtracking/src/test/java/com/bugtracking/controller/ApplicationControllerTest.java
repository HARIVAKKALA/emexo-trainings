package com.bugtracking.controller;

import com.bugtracking.entity.Application;
import com.bugtracking.service.ApplicationService;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class ApplicationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ApplicationService applicationService;
    @Test
    @Ignore
    void findAllApplications() throws Exception {
        List<Application> applicationList = new ArrayList<>();
        Application application = new Application();
        application.setApplication_id(1);
        application.setName("LRI");
        application.setOwner("JPMC");
        application.setDescription("JPMC");
        applicationList.add(application);
        when(applicationService.listApplications()).thenReturn(applicationList);

        //Create a post request with an accept header for application\json
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/applications/")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //Assert that the return status is OK
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void findAllApplicationsWithEmptyList() throws Exception {
        List<Application> applicationList = new ArrayList<>();
//        Application application = new Application();
//        application.setApplication_id(1);
//        application.setName("LRI");
//        application.setOwner("JPMC");
//        application.setDescription("JPMC");
        //applicationList.add(application);
        when(applicationService.listApplications()).thenReturn(applicationList);

        //Create a post request with an accept header for application\json
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/applications/")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //Assert that the return status is OK
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void findAllApplicationsWithExceptions() throws Exception {
        when(applicationService.listApplications()).thenThrow(new NullPointerException());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/applications/")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //Assert that the return status is OK
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void testGetById() throws Exception{
        int id =1;
        Application application = new Application();
        application.setApplication_id(1);
        application.setName("LRI");
        application.setOwner("JPMC");
        application.setDescription("JPMC");
        when(applicationService.findApplication(anyInt())).thenReturn(application);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/applications/{id}",id)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        mockMvc.perform(requestBuilder)
                .andExpect(jsonPath ("$.application_id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.owner").value("JPMC"))
                .andExpect(jsonPath("$.description").value("JPMC"))
                .andDo(print());

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void testSaveApplication() throws Exception {
        Application application = new Application();
        application.setApplication_id(1);
        application.setName("LRI");
        application.setOwner("JPMC");
        application.setDescription("JPMC");
        when(applicationService.save(any())).thenReturn(Optional.of(application));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/applications").content(asJsonString(application)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());

    }

    @Test
    void testSaveApplicationWithEmptyData() throws Exception {
        Application application = new Application();
        application.setApplication_id(1);
        application.setName("LRI");
        application.setOwner("JPMC");
        application.setDescription("JPMC");
        when(applicationService.save(any())).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/applications").content(asJsonString(application)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isNotFound());

    }

    @Test
    void testSaveApplicationWithException() throws Exception {
        Application application = new Application();
        application.setApplication_id(1);
        application.setName("LRI");
        application.setOwner("JPMC");
        application.setDescription("JPMC");
        when(applicationService.save(any())).thenThrow (new NullPointerException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/applications").content(asJsonString(application)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isNotFound());

    }
    @Test
    void testUpdateApplication() throws Exception{
        Application application = new Application();
        application.setApplication_id(1);
        application.setName("LRI");
        application.setOwner("JPMC");
        application.setDescription("JPMC");
        when(applicationService.save(any())).thenReturn (Optional.of(application));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/applications").content(asJsonString(application)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }
    @Test
    void testUpdateApplicationWithException() throws Exception {
        Application application = new Application();
        application.setApplication_id(1);
        application.setName("LRI");
        application.setOwner("JPMC");
        application.setDescription("JPMC");
        when(applicationService.save(any())).thenThrow (new NullPointerException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/applications").content(asJsonString(application)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isInternalServerError());
    }
    @Test
    void testDeleteApplication() throws Exception {
        int id =1;
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/applications").param("id", String.valueOf(1)).contentType(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
        //assertEquals(HttpStatus.OK.value(), response.getStatus());
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }
    String asJsonString(Object obj)  {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStringObj = objectMapper.writeValueAsString(obj);
            return jsonStringObj;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

