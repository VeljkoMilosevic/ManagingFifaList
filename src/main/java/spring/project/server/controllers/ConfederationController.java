/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.project.server.model.Confederation;
import spring.project.server.services.ConfederationService;

import java.util.List;

/**
 * @author Veljko
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/confederations")
@RestController
public class ConfederationController {

    ConfederationService confederationService;

    @Autowired
    public ConfederationController(final ConfederationService confederationService) {
        this.confederationService = confederationService;
    }

    @GetMapping()
    public List<Confederation> getAllConfederation() {
        return confederationService.getAllConfederation();
    }
}
