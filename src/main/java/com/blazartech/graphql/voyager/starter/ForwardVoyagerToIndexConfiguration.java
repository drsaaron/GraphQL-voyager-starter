/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.graphql.voyager.starter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author AAR1069
 */
@Configuration
public class ForwardVoyagerToIndexConfiguration {
    
    @Value("${graphql.root}")
    private String graphQLRoot;
    
    @Bean
    public WebMvcConfigurer forwardToIndex() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // forward requests to /voyager to its index.html
                registry.addViewController(graphQLRoot + "/voyager").setViewName("forward:/voyager/index.html");
            }
        };
    }
}
