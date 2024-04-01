/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.server.exceptions.SelectionNotFoundException;
import spring.project.server.model.Match;
import spring.project.server.model.Selection;
import spring.project.server.repositories.MatchRepository;
import spring.project.server.repositories.SelectionRepository;

import java.util.List;

/**
 * @author Veljko
 */
@Service
@Transactional
public class SelectionService {

    SelectionRepository selectionRepository;
    MatchRepository matchRepository;

    @Autowired
    public SelectionService(final SelectionRepository selectionRepository, final MatchRepository matchRepository) {
        this.selectionRepository = selectionRepository;
        this.matchRepository = matchRepository;
    }

    public void save(final Selection selection) {
        selection.setRang(selectionRepository.findAll().size() + 1);
        selectionRepository.save(selection);
    }

    public void deleteSelection(final int id) {
        selectionRepository.deleteById(id);
    }

    public Selection getSelectionById(final int id) {
        return selectionRepository.findById(id).orElse(null);
    }

    public void updateSelection(final Selection selection, final int id) {
        if (!selectionRepository.findById(id).isPresent()) {
            throw new SelectionNotFoundException();
        }
        selectionRepository.save(selection);
    }

    public List<Selection> getAllSelections() {
        return selectionRepository.findAll();
    }

    public List<Match> getAllMatchesBySelection(final int id) {
        final List<Match> matches = matchRepository.findAllMatchesHost(id);
        matches.addAll(matchRepository.findAllMatchesAway(id));
        return matches;
    }
}
