/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.exceptions;

/**
 * @author Veljko
 */
public class SelectionNotFoundException extends RuntimeException {

    public SelectionNotFoundException() {
    }

    public SelectionNotFoundException(final String message) {
        super(message);
    }
}
