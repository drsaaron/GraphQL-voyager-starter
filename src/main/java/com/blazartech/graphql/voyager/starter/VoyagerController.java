/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.graphql.voyager.starter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * a controller to provide a REST service that will return the path of the 
 * GraphQL server.  Basically, I can't figure out how to get that variable
 * into the static HTML so we'll for now get it by a REST call.
 * 
 * @author AAR1069
 */
@RestController
public class VoyagerController {
    
    @Value("${graphql.servlet.mapping}")
    private String graphQLMapping;
    
    public static record ServerPathResponse(String mapping) {
                
    }
    
    @GetMapping(value = "${graphql.root}/voyager/mapping")
    public ServerPathResponse getServerPath() {
        return new ServerPathResponse(graphQLMapping);
    }
}
