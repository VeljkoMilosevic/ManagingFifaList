/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import spring.project.server.exceptions.MatchNotFound;
import spring.project.server.model.Match;
import spring.project.server.services.MatchService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Veljko
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/matches")
@RestController
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(final MatchService matchService) {
        this.matchService = matchService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSelection(@Valid @RequestBody final Match match) {
        matchService.saveMatch(match);
    }

    @GetMapping("{id}")
    public Match getMatchById(@PathVariable(value = "id") final int id) {
        final Match match = matchService.getMatchById(id);
        if (match == null) {
            throw new MatchNotFound("Match with id " + id + " does not exists.");
        }
        return match;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatch(
            @PathVariable(value = "id") final int id) {
        matchService.deleteMatch(id);
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMatch(@PathVariable final int id, @RequestBody final Match match) {
        matchService.updateMatch(match, id);
    }

    @GetMapping()
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

}
