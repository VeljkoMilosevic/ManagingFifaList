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
public class SimpleStringGenerator {

    public static String generate(final int numberOfChars) {
        final StringBuilder builder = new StringBuilder("test_");
        final Random random = new Random();
        for (int i = 0; i < numberOfChars; i++) {
            final int number = random.nextInt(26) + 'a';
            builder.append((char) (number));
        }
        return builder.toString();
    }
}
