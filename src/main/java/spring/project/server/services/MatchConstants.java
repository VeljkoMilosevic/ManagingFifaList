/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.services;


/**
 * @author Veljko
 */
public interface MatchConstants {
    int WINNER = 3;
    int DRAW = 1;
    int LOSE = 0;

    double THIS_YEAR = 1;
    double LAST_YEAR = 0.5;
    double TWO_YEARS_AGO = 0.3;
    double THREE_YEARS_AGO = 0.2;

    int INITIAL_OPPONENT_STRENGTH = 200;
}
