/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.project.server.helpers;

import java.util.Random;

/**
 * @author Veljko
 */
public class SimpleIDGenerator {

    public static int generate(final int bound) {
        final Random random = new Random();
        return random.nextInt(bound) + 1;
    }
}
