package com.example.testDemoSpringPostgres.controller;

import com.example.testDemoSpringPostgres.repository.unitRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
/*import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;*/
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "schema.sql")
class controllerTest {

   /* @LocalServerPort
    private Integer port;*/
    @Autowired
    MockMvc mockMvc;

    @Autowired
    unitRepository uniRepository;

    //final TestRestTemplate template = new TestRestTemplate();


    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

   @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.datasource.url", postgres::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgres::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgres::getPassword);
    }

/*    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }*/


    @Test
    void  postUnit() throws Exception {

        mockMvc.perform(post("/unit")
                .contentType(MediaType.APPLICATION_JSON).content("""
                                                                    {"id": 234,
                                                                    "nameNetwork": "zzzzzz"
                                                                     }
                                                                    """));
        var result = mockMvc.perform(get("/unit"))
                .andExpectAll(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();// не смог десерилизовать LIST
        Assertions.assertEquals(result, new ObjectMapper().writeValueAsString(uniRepository.findAll()));
        System.out.println(result);
    }

    @Test
    void  findAllNetwork() throws Exception {
        mockMvc.perform(get("/unit"))
                .andExpectAll(
                        status().isOk());
    }

}