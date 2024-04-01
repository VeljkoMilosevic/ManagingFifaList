/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.exceptions;

/**
 * @author Veljko
 */
public class MatchNotFound extends RuntimeException {

    public MatchNotFound() {
    }

    public MatchNotFound(final String message) {
        super(message);
    }
}