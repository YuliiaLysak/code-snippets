package edu.lysak.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    void add_ShouldAddTwoNumbers() {
        assertThat(calculator.add(1, 2)).isEqualTo(3);
    }

    @Test
    void subtract_ShouldSubtractSecondNumberFromFirst() {
        assertThat(calculator.subtract(5, 2)).isEqualTo(3);
    }

    @Test
    void multiply_ShouldMultiplyTwoNumbers() {
        assertThat(calculator.multiply(1, 1)).isEqualTo(1);
    }

    @Test
    void divide_ShouldDivideFirstNumberBySecond() {
        assertThat(calculator.divide(4, 2)).isEqualTo(2);
    }

    @ParameterizedTest(name = "{index} => maxOf({0}, {1}) == {2}")
    @CsvSource({"2, 1, 2", "1, 2, 2", "1, 1, 1"})
    @DisplayName("Should test maxOf 3 times with different cases")
    void maxOf_ShouldTestForMaxArg(int first, int second, int expected) {
        assertThat(calculator.maxOf(first, second)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{index} => isEven({0}) == true")
    @ValueSource(ints = { 0, 2, 4, 1000 })
//    @CsvSource({"0", "2", "4", "1000"})
    void isEven_ShouldTestForEvenArgs(int arg) {
        assertThat(calculator.isEven(arg)).isTrue();
    }


//  demonstrate passing an empty argument
    @ParameterizedTest
    @EmptySource
    void testEmpty(int[] arg) {
        assertThat(arg.length).isEqualTo(0);
    }

//  demonstrate passing an empty and then null arguments
    @ParameterizedTest
    @NullAndEmptySource
    void testNullAndEmpty(List<String> arg) {
        assertThat(arg == null || arg.isEmpty()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("stringFactory")
    void testStrings(String str) {
        assertThat(str.isEmpty()).isFalse();
    }

    static List<String> stringFactory() { // Each such method must be static, must not accept any arguments, and must return a stream, an array, or a collection of arguments.
        return List.of("apple", "banana", "lemon", "orange");
    }

    @ParameterizedTest
    @MethodSource("argFactory")
    void testStringLength(String str, int length) {
        assertThat(str.length()).isEqualTo(length);
    }

    static List<Arguments> argFactory() { // Each such method must be static, must not accept any arguments, and must return a stream, an array, or a collection of arguments.
        return List.of(arguments("apple", 5), arguments("watermelon", 10));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/dataset-for-testing.csv", numLinesToSkip = 1)
//    @CsvSource({ "apple, 5", "strawberry, 10", "cherry, 6" })
    void testStringLength2(String str, int length) {
        assertThat(str.length()).isEqualTo(length);
    }
}
