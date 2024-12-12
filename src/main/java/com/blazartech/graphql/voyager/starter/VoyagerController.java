/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.graphql.voyager.starter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * a controller to provide the voyager template
 * 
 * @author AAR1069
 */
@Controller
public class VoyagerController {
    
    @Value("${graphql.servlet.mapping}")
    private String graphQLMapping;
    
    @GetMapping(value = "${graphql.root}/voyager")
    public String getServerPath(Model model) {
        model.addAttribute("graphQLMapping", graphQLMapping);
        return "voyager";
    }
}
