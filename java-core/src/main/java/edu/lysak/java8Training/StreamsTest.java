package edu.lysak.java8Training;

import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsTest {
    private static final int STEPS = 20;
    private static String[] aStrings = {"abc", "", "bc", "efg", "abc", "", "jkl", "bc", "abcd"};
    private static List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abc", "", "jkl", "bc", "abcd");
    private static List<List<String>> countriesList = Arrays.asList(
            Arrays.asList("Ukraine"),
            Arrays.asList("China", "Germany"),
            Arrays.asList("China", "Italy"),
            Arrays.asList("USA", "Canada")
    );

    public static void main(String[] args) throws Exception {
		testCreate();

		testFilter();
		testLazy();
		testAsConveyor();
		testMap();
		testMap2();
		testFlatMap();
		testMathHelpers();
		testFinders();
		testReduce();
		testCollectorsJoining();
		testCollectorsGroupingBy();
		testCollectorsGroupingBy2();
		testCollectorsGroupingByCurrencyAndCount();

		performance();
        performance2();
//		giveRangeOfLongs();
    }

    public static void testCreate() {
        System.out.println("~~~~~ testCreate ~~~~~");

        Stream<String> stringsStream = Arrays.stream(aStrings);
        System.out.println(stringsStream.collect(Collectors.toList()));

        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
        System.out.println(stream.collect(Collectors.toList()));

        Stream<Integer> stream1 = Stream.of(new Integer[]{1, 2, 3, 4});
        System.out.println(stream1.collect(Collectors.toList()));

//		Stream<Integer> stream2 = Stream.of(new int[]{1,2,3,4}); 
        // Compile time error, Type mismatch: cannot convert from
        // Stream<int[]> to Stream<Integer>

        Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);
        System.out.println(streamIterated.collect(Collectors.toList()));

        SecureRandom sr = new SecureRandom();
        Stream<String> streamGenerated = Stream.generate(() -> sr.nextInt() + "").limit(10);
        System.out.println(streamGenerated.collect(Collectors.toList()));

        Stream<String> streamBuilder =
                Stream.<String>builder().add("a").add("b").add("c").build();
        System.out.println(Arrays.toString(streamBuilder.toArray()));
    }

    public static void testFilter() {
        System.out.println("~~~~~ testFilter ~~~~~");
        Stream<String> stream = strings.stream()
                .filter(s -> !s.isBlank())
                .peek(s -> System.out.print(s + ", "));
        System.out.println(stream);
        System.out.println(Arrays.toString(stream.toArray()));
    }

    public static void testMap() {
        System.out.println("~~~~~ testMap ~~~~~");
        Stream<String> stream = strings.stream()
                .filter(s -> !s.isBlank())
                .map(String::toUpperCase);
        System.out.println(stream.collect(Collectors.toList()));
    }

    public static void testMap2() {
        System.out.println("~~~~~ testMap2 ~~~~~");
        Stream<Integer> stream = strings.stream()
                .filter(s -> !s.isBlank())
                .map(String::length);
        IntStream ints = stream.mapToInt(i -> i);
        System.out.println(Arrays.toString(ints.toArray()));
    }

    public static void testFlatMap() {
        System.out.println("~~~~~ testFlatMap ~~~~~");
        Stream<List<String>> countries = countriesList.stream();
        // flat the stream from List<String> to String stream
        Stream<String> flatStream = countries
                .flatMap(strList -> strList.stream())
                .distinct()
                .sorted(Comparator.comparing(String::length).reversed().thenComparing(s -> s));

        flatStream.forEach(System.out::println);
    }

    public static void testMathHelpers() {
        System.out.println("~~~~~ testMathHelpers ~~~~~");
        Stream<List<String>> countries = countriesList.stream();
        long count = countries.flatMap(strList -> strList.stream())
                .distinct()
                .mapToInt(String::length)
                .count();
        System.out.println("Count " + count);

        countries = countriesList.stream();
        Optional<String> max = countries.flatMap(strList -> strList.stream())
                .distinct() // .sorted()
                .max(Comparator.comparing(String::length));
        System.out.println("Max " + max);

        countries = countriesList.stream();
        String maxString = countries.flatMap(strList -> strList.stream())
                .distinct() // .sorted()
                .max(Comparator.comparing(String::length)).get();
        System.out.println("Max " + maxString);

        countries = countriesList.stream();
        IntSummaryStatistics summaryStatistics = countries.flatMap(strList -> strList.stream())
                .distinct()
                .mapToInt(String::length)
                .summaryStatistics();
        System.out.println("Statistics " + summaryStatistics);
    }

    public static void testFinders() {
        System.out.println("~~~~~ testFinders ~~~~~");
        Stream<String> stream = strings.stream();
        boolean matches = stream.anyMatch(s -> !s.isBlank());
        System.out.println("Matches " + matches);

        stream = strings.stream();
        String str = stream.findFirst().get();
        System.out.println("First " + str);

    }

    public static void testReduce() {
        System.out.println("~~~~~ testReduce ~~~~~");
        Stream<String> stream = strings.stream();
        String result = stream.reduce("", (s, v) -> s + v);
        System.out.println("Reduce result " + result);

        stream = strings.stream();
        int len = stream.mapToInt(String::length).filter(x -> x > 0).reduce(1, (x, y) -> x * y);
        System.out.println("Product of lengths " + len);
    }

    public static void testCollectorsJoining() {
        System.out.println("~~~~~ testCollectorsJoining ~~~~~");
        Stream<String> stream = strings.stream();
        String result = stream.collect(
                Collectors.filtering(s -> s.length() > 0, Collectors.joining(", ")));
        System.out.println("Collect result " + result);
    }

    public static void testCollectorsGroupingBy() {
        System.out.println("~~~~~ testCollectorsGroupingBy ~~~~~");
        Stream<String> stream = strings.stream();
        Map<String, List<String>> result = stream.collect(Collectors.groupingBy(Function.identity()));
        System.out.println("Collect result " + result);

        stream = strings.stream();
        Map<Integer, List<String>> result2 = stream.collect(Collectors.groupingBy(String::length)); // Exception
        System.out.println("Collect result2 " + result2);
    }

    public static void testCollectorsGroupingBy2() {
        System.out.println("~~~~~ testCollectorsGroupingBy2 ~~~~~");
        Stream<Transaction> stream = Util.createTransactions(5, Util.f, ArrayList::new).stream();
        Map<String, List<Transaction>> result = stream.collect(
                Collectors.groupingBy(Transaction::getCurrency));
        System.out.println("Collect result " + result);
    }

    public static void testCollectorsGroupingByCurrencyAndCount() {
        System.out.println("~~~~~ testCollectorsGroupingByCurrencyAndCount ~~~~~");
        Stream<Transaction> stream = Util.createTransactions(5, Util.f, ArrayList::new).stream();
        Map<String, Long> result = stream.collect(
                Collectors.groupingBy(Transaction::getCurrency, Collectors.counting()));
        System.out.println("Collect result " + result);
    }

    public static void testLazy() {
        System.out.println("~~~~~ testLazy ~~~~~");
        System.out.println("Sequntal");
        Stream<String> stream = strings.stream()
                .peek(e -> System.out.print(e + ", "))
                .map(String::toUpperCase)
                .peek(e -> System.out.print(e + ", "));
        List<String> list = stream.collect(Collectors.toList());
        System.out.println("\n" + list);

        System.out.println("Parallel");
        stream = strings.parallelStream()
                .peek(e -> System.out.print(e + ", "))
                .map(String::toUpperCase)
                .peek(e -> System.out.print(e + ", "));
        list = stream.collect(Collectors.toList());
        System.out.println("\n" + list);
    }

    public static <T> Stream<T> print(Stream<T> stream) {
        System.out.println();
        return stream.peek(v -> System.out.print(v + ", "));
    }

    public static void testAsConveyor() {
        strings.stream().filter(s -> {
            System.out.println("filtering: " + s);
            return s.length() > 0;
        }).map(s -> {
            System.out.println("mapping toUpperCase: " + s);
            return s.toUpperCase();
        }).map(s -> {
            System.out.println("mapping toLowerCase: " + s);
            return s.toLowerCase();
        }).collect(Collectors.toList());
    }

    public static Collection<Transaction> generate(int n, int bound,
                                                   IntFunction<String> f, Supplier<Collection<Transaction>> factory) {

        SecureRandom sr = new SecureRandom();
        return sr.ints(n, 1, bound)//.parallel().unordered()
                .mapToObj(v -> new Transaction(v, f.apply(v)))
                .collect(Collectors.toCollection(factory));
    }

    public static void performance() throws InterruptedException, ExecutionException {
        final int count = 1_000_000;
        ForkJoinPool customThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        Map<String, List<Long>> times = new LinkedHashMap<String, List<Long>>();
        times.put("Sequental", new ArrayList<Long>());
        times.put("Parallel", new ArrayList<Long>());
        times.put("Parallel with pool", new ArrayList<Long>());

        long start = System.currentTimeMillis();
        final Collection<Transaction> ts = generate(count, 2000, Util.f, ArrayList<Transaction>::new);
//		final Collection<Transaction> ts = Util.createRandomTransactions(count, 2000, Util.f, HashSet<Transaction>::new);
        long time = System.currentTimeMillis() - start;
        System.out.println("Init " + time);

        for (int i = 0; i < STEPS; i++) {
            System.out.println("Sequental");
            start = System.currentTimeMillis();
            double avg = ts.stream().mapToDouble(Transaction::getPrice).average().getAsDouble();
            time = System.currentTimeMillis() - start;
            System.out.println("Time " + time + " Value: " + avg);
            times.merge("Sequental", List.of(time), (l, v) -> {
                l.addAll(v);
                return l;
            });

            System.out.println("Parallel");
            start = System.currentTimeMillis();
            avg = ts.parallelStream().mapToDouble(Transaction::getPrice).average().getAsDouble();
            time = System.currentTimeMillis() - start;
            System.out.println("Time " + time + " Value: " + avg);
            times.merge("Parallel", List.of(time), (l, v) -> {
                l.addAll(v);
                return l;
            });

            System.out.println("Parallel whith pool");
            start = System.currentTimeMillis();
            avg = customThreadPool.submit(() -> ts.parallelStream().mapToDouble(Transaction::getPrice).average().getAsDouble()).get();
            time = System.currentTimeMillis() - start;
            System.out.println("Time " + time + " Value: " + avg);
            times.merge("Parallel with pool", List.of(time), (l, v) -> {
                l.addAll(v);
                return l;
            });
        }
        customThreadPool.shutdown();
        for (Map.Entry<String, List<Long>> entry : times.entrySet()) {
            System.out.printf("Avarage: %s -> %s%n", entry.getKey(), entry.getValue().stream().collect(Collectors.averagingLong(l -> l)));
        }
    }

    public static void performance2() throws InterruptedException, ExecutionException {
        final int count = 1_000_000;
        long start = System.currentTimeMillis();
        final Collection<Transaction> ts = Util.createRandomTransactions(count, 2000, Util.f, ArrayList::new);
//		final Collection<Transaction> ts = Util.createTransactions(count, Util.f, ArrayList::new);
        long time = System.currentTimeMillis() - start;
        System.out.println("Init " + time);
        start = System.currentTimeMillis();
        Double sum = ts.stream().collect(Collectors.summarizingDouble(Transaction::getPrice)).getAverage();
        time = System.currentTimeMillis() - start;
        System.out.println("Reduce time " + time);
        System.out.println("Reduce: " + sum);

        start = System.currentTimeMillis();
        sum = ts.parallelStream().reduce(0D, (x, t) -> x + t.getPrice(), (x, y) -> x + y);
        time = System.currentTimeMillis() - start;
        System.out.println("Parallel Reduce time " + time);
        System.out.println("Reduce: " + sum / ts.size());

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        start = System.currentTimeMillis();
        sum = pool.submit(() -> ts.parallelStream().reduce(0D, (x, t) -> x + t.getPrice(), (x, y) -> x + y)).get();
        time = System.currentTimeMillis() - start;
        System.out.println("ForkJoin time " + time);
        System.out.println("Reduce: " + sum / ts.size());
        System.out.println(Collections.synchronizedCollection(ts).spliterator().characteristics());
    }
}
