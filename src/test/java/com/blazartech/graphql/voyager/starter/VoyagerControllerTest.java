/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.blazartech.graphql.voyager.starter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 *
 * @author scott
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(VoyagerController.class)
@ContextConfiguration(classes = {
    VoyagerControllerTest.VoyagerControllerTestConfiguration.class
})
@Slf4j
public class VoyagerControllerTest {
    
    @Configuration
    @PropertySource("classpath:unittest.properties") 
    public static class VoyagerControllerTestConfiguration {
        
        @Bean
        public VoyagerController instance() {
            return new VoyagerController();
        }
    }
    
    @Autowired
    private MockMvc mockMvc;
    
    public VoyagerControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {   
    }
    
    @Value("${graphql.root}")
    private String graphQLRoot;
    
    @Value("${graphql.servlet.mapping}")
    private String graphQLMapping;

    /**
     * Test of getServerPath method, of class VoyagerController.
     */
    @Test
    public void testGetServerPath() {
        log.info("getServerPath");
        
        try {
            MvcResult result = mockMvc
                    .perform(
                            get(graphQLRoot + "/voyager")
                    )
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andReturn();
            
            String html = result.getResponse().getContentAsString();
            log.info("html = {}", html);

            assertNotNull(html);
            
            // thymeleaf should have inserted the graphQL mapping to a line of the form var graphQLMapping = "/my-unittest";
            int position = html.indexOf(graphQLMapping);
            log.info("position = {}", position);
            assertTrue(position > 0);

        } catch (Exception e) {
            throw new RuntimeException("error running test: " + e.getMessage(), e);
        }

    }
    
}
