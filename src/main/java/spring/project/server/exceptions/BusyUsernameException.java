/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.exceptions;

/**
 * @author Veljko
 */
public class BusyUsernameException extends RuntimeException {

    public BusyUsernameException() {
    }

    public BusyUsernameException(final String message) {
        super(message);
    }
}