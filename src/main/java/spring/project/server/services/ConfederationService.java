/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.server.model.Confederation;
import spring.project.server.repositories.ConfederationRepository;

import java.util.List;

/**
 * @author Veljko
 */
@Service
@Transactional
public class ConfederationService {

    ConfederationRepository confederationRepository;

    @Autowired
    public ConfederationService(final ConfederationRepository confederationRepository) {
        this.confederationRepository = confederationRepository;
    }

    public List<Confederation> getAllConfederation() {
        return confederationRepository.findAll();
    }

}
