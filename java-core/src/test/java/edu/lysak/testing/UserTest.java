package edu.lysak.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    static String[] names;
    static String[] passwords;
    static boolean[] expectedOutcomes;

    static int index = 0;

    User user;
    boolean expected;

    @BeforeAll
    static void setUp() {
        names = new String[] {"Alice", "Alice", "Alice", "", null, "    "};
        passwords = new String[] {"12345678", "123", null, "12345678", "12345678", "12345678"};
        expectedOutcomes = new boolean[] {true, false, false, false, false, false};
    }

    @BeforeEach
    void createUser() {
        user = new User(names[index], passwords[index]);
        expected = expectedOutcomes[index];
    }

    @AfterEach
    void incrementIndex() {
        index++;
    }

    @RepeatedTest(value = 6, name = "user.isValid() test {currentRepetition}/{totalRepetitions}")
    void isValid() {
        assertEquals(expected, user.isValid());
    }
}