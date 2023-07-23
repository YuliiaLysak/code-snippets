package edu.lysak.functional;

import java.util.function.Function;
import java.util.function.Predicate;

public class ExplainTernary {
    public static void main(String[] args) {
        Function<CharSequence, CharSequence> f = ternaryOperatorString(
                s -> s.length() > 3,
                s -> s.subSequence(0, 3),
                s -> s
        );

        CharSequence result = f.apply(new StringBuffer("1234"));
        System.out.println(result);
    }

    public static Function<CharSequence, CharSequence> ternaryOperatorString(
            Predicate<CharSequence> condition,
            Function<CharSequence, CharSequence> ifTrue,
            Function<CharSequence, CharSequence> ifFalse) {

        return value ->
                condition.test(value)
                        ? ifTrue.apply(value)
                        : ifFalse.apply(value);
    }

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {

        return value ->
                condition.test(value)
                        ? ifTrue.apply(value)
                        : ifFalse.apply(value);
    }
}
