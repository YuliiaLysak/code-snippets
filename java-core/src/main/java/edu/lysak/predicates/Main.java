package edu.lysak.predicates;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String row1 = "ivanov;Ivan Ivanov;ivanov@mail.com";
        String row2 = "петров;Петр Петров;petrov@google.com";
        Predicate<String> isGoogleMail = Pattern.compile("@google.com").asPredicate();
        if (isGoogleMail.test(row1)) {
            System.out.println("row1 is google mail");
        }
        if (isGoogleMail.test(row2)) {
            System.out.println("row2 is google mail");
        }


        Predicate<String> isLongString = (str) -> str.length() > 5;
        System.out.println(isLongString.test("1234"));
        System.out.println(isLongString.test("123456780"));
    }
}
