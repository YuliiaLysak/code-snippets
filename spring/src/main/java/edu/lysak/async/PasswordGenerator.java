package edu.lysak.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PasswordGenerator {
    private static final Random random = new Random();
    private final PasswordConfig.PasswordAlphabet alphabet;

    public PasswordGenerator(@Autowired PasswordConfig.PasswordAlphabet alphabet) {
        this.alphabet = alphabet;
    }

    public String generate(int length) {
        String allCharacters = alphabet.getCharacters(); // take the characters from the bean
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allCharacters.length());
            result.append(allCharacters.charAt(index));
        }
        return result.toString();
    }
}
