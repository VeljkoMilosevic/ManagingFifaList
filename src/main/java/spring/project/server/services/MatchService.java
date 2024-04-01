/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.server.exceptions.MatchNotFound;
import spring.project.server.model.Match;
import spring.project.server.repositories.MatchRepository;

import java.util.List;

/**
 * @author Veljko
 */
@Service
@Transactional
public class MatchService {

    MatchRepository matchRepository;

    @Autowired
    public MatchService(final MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match getMatchById(final int id) {
        return matchRepository.findById(id).orElse(null);
    }

    public void deleteMatch(final int id) {
        matchRepository.deleteById(id);
    }

    public void updateMatch(final Match updatedMatch, final int id) {
        final Match match = getMatchById(id);
        if (match == null) {
            throw new MatchNotFound("Match with id " + id + " does not exists.");
        }
        match.setAway(updatedMatch.getAway());
        match.setHost(updatedMatch.getHost());
        match.setHostGoals(updatedMatch.getHostGoals());
        match.setAwayGoals(updatedMatch.getAwayGoals());
        match.setMatchType(updatedMatch.getMatchType());
        match.setUser(updatedMatch.getUser());
        match.setDate(updatedMatch.getDate());
        matchRepository.save(match);
    }

    public void saveMatch(final Match match) {
        matchRepository.save(match);
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

}
