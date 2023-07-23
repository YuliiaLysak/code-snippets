package edu.lysak.java8Training;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Composing {

    public static void main(String[] args) {
        Collection<Transaction> transactions = Util.createRandomTransactions(25, 2000, Util.f, ArrayList<Transaction>::new);

        // Composition of comparators
        transactions.stream()
                .sorted(Comparator.comparing(Transaction::getPrice)
                        .reversed()
                        .thenComparing(Transaction::getCurrency));

        // Composition of predicates
        Predicate<Transaction> big = t -> t.getPrice() > 1000;
        Predicate<Transaction> small = big.negate();
        Predicate<Transaction> smallAndUAH = small.and(t -> t.getCurrency().equalsIgnoreCase("UAH"));
        System.out.println(transactions.stream().filter(smallAndUAH).collect(Collectors.toList()));

        // Composition of functions
        Function<Transaction, Double> price = Transaction::getPrice;
        Function<Double, Double> toUSD = v -> v / 26;
        // toUSD(price)
        System.out.println(transactions.stream()
                .filter(smallAndUAH)
                .map(price.andThen(v -> v / 26))
                .collect(Collectors.toList()));

        // toUSD(price)
        System.out.println(transactions.stream()
                .filter(smallAndUAH)
                .map(toUSD.compose(price))
                .collect(Collectors.toList()));

    }
}
