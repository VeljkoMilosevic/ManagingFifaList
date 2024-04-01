/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.server.model.Match;
import spring.project.server.model.Selection;
import spring.project.server.repositories.MatchRepository;
import spring.project.server.repositories.SelectionRepository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Veljko
 */
@Service
@Transactional
public class CalculateRangList {

    SelectionRepository selectionRepository;
    MatchRepository matchRepository;

    public SelectionRepository getSelectionRepository() {
        return selectionRepository;
    }

    public MatchRepository getMatchRepository() {
        return matchRepository;
    }


    @Autowired
    public CalculateRangList(final SelectionRepository selectionRepository, final MatchRepository matchRepository) {
        this.selectionRepository = selectionRepository;
        this.matchRepository = matchRepository;
    }


    public void calculate() {
        final List<Selection> selections = selectionRepository.findAll();
        for (final Selection selection : selections) {
            calculatePoints(selection);
        }
        updateRangList(selections);
    }

    private void calculatePoints(final Selection selection) {
        final List<Match> matches = matchRepository.findAllMatchesAway(selection.getId());
        matches.addAll(matchRepository.findAllMatchesHost(selection.getId()));
        int points = 0;
        for (final Match m : matches) {
            points += checkWinner(m, selection) * checkImportance(m) * checkDate(m)
                    * checkOpponentStrength(m, selection) * checkConfederationStrength(m, selection);
        }
        selection.setPoints(points);
    }

    private double checkWinner(final Match match, final Selection selection) {
        if (match.getHost().equals(selection)) {
            return checkWinnerHost(match);
        } else {
            return checkWinnerAway(match);
        }
    }

    private double checkWinnerAway(final Match match) {
        if (match.getHostGoals() < match.getAwayGoals()) {
            return MatchConstants.WINNER;
        } else if (match.getHostGoals() == match.getAwayGoals()) {
            return MatchConstants.DRAW;
        } else {
            return MatchConstants.LOSE;
        }
    }

    private double checkWinnerHost(final Match match) {
        if (match.getHostGoals() > match.getAwayGoals()) {
            return MatchConstants.WINNER;
        } else if (match.getHostGoals() == match.getAwayGoals()) {
            return MatchConstants.DRAW;
        } else {
            return MatchConstants.LOSE;
        }
    }

    private double checkImportance(final Match m) {
        return m.getMatchType().getStrenght();
    }

    private double checkDate(final Match m) {
        final Date date = new Date();

        if (m.getDate().getYear() == date.getYear()) {
            return MatchConstants.THIS_YEAR;
        } else if (m.getDate().getYear() + 1 == date.getYear()) {
            return MatchConstants.LAST_YEAR;
        } else if (m.getDate().getYear() + 2 == date.getYear()) {
            return MatchConstants.TWO_YEARS_AGO;
        } else if (m.getDate().getYear() + 3 == date.getYear()) {
            return MatchConstants.THREE_YEARS_AGO;
        }
        return 0;
    }

    private double checkOpponentStrength(final Match match, final Selection selection) {
        return MatchConstants.INITIAL_OPPONENT_STRENGTH - getOpponent(match, selection).getRang();
    }

    private double checkConfederationStrength(final Match match, final Selection selection) {
        return getOpponent(match, selection).getConfederation().getStrenght();
    }

    private static Selection getOpponent(final Match match, final Selection selection) {
        if (selection.equals(match.getAway())) {
            return match.getHost();
        }
        return match.getAway();
    }

    private void updateRangList(List<Selection> selections) {
        selections = selections.stream()
                .sorted(Comparator.comparing(Selection::getPoints).reversed())
                .collect(Collectors.toList());
        int rang = 1;
        for (final Selection selection : selections) {
            selection.setRang(rang++);
            selectionRepository.save(selection);
        }
    }
}
