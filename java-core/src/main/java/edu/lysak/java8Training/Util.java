package edu.lysak.java8Training;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Util {
    private static final int BOUND = 2000;
    private static SecureRandom sr = new SecureRandom();

    public static IntFunction<String> f = v -> v % 3 == 0 ? "UAH" : v % 1 == 1 ? "USD" : "EUR";

    private static Transaction[] tr = {
            new Transaction(sr.nextInt(BOUND), "USD"),
            new Transaction(sr.nextInt(BOUND), "UAH"),
            new Transaction(sr.nextInt(BOUND), "USD"),
            new Transaction(sr.nextInt(BOUND), "UAH"),
            new Transaction(sr.nextInt(BOUND), "USD"),
            new Transaction(sr.nextInt(BOUND), "UAH"),
    };

    public static Collection<Transaction> createTransactions(int n,
                                                             IntFunction<String> f, Supplier<Collection<Transaction>> factory) {

        Collection<Transaction> Collection = factory.get();
        for (int i = 0; i < n; i++) {
            Collection.add(new Transaction(i, f.apply(i)));
        }
        return Collection;
    }

    public static Collection<Transaction> createTransactionsWithStream(int n,
                                                                       IntFunction<String> f, Supplier<Collection<Transaction>> factory) {

        return IntStream.rangeClosed(0, n).boxed()
                .map(v -> new Transaction(v, f.apply(v)))
                .collect(Collectors.toCollection(factory));
    }

    public static Collection<Transaction> createRandomTransactions(int n, int bound,
                                                                   IntFunction<String> f, Supplier<Collection<Transaction>> factory) {

        Collection<Transaction> Collection = factory.get();
        for (int i = 0; i < n; i++) {
            int v = sr.nextInt(bound);
            Collection.add(new Transaction(v, f.apply(v)));
        }
        return Collection;
    }

    public static Collection<Transaction> createRandomTransactionsWithStreem(int n, int bound,
                                                                             IntFunction<String> f, Supplier<Collection<Transaction>> factory) {
        return Stream.generate(() -> {
            int v = sr.nextInt(bound);
            return new Transaction(v, f.apply(v));
        }).limit(n).collect(Collectors.toCollection(factory));
    }
}
