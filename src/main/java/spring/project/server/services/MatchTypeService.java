/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.server.model.MatchType;
import spring.project.server.repositories.MatchTypeRepository;

import java.util.List;

/**
 * @author Veljko
 */
@Service
@Transactional
public class MatchTypeService {

    MatchTypeRepository matchTypeRepository;

    @Autowired
    public MatchTypeService(final MatchTypeRepository matchTypeRepository) {
        this.matchTypeRepository = matchTypeRepository;
    }

    public List<MatchType> getAllMatchTypes() {
        return matchTypeRepository.findAll();
    }

}
