package dev.wiles.calc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import dev.wiles.calc.exceptions.FormatException;
import dev.wiles.calc.exceptions.ParenthesisException;

public class CalcTest {

    private final Calculator calculator = new Calculator();

    @ParameterizedTest(name = "{0}={1}")
    @CsvSource(value = {
            "1=1",
            "-2=-2",
            "1+1=2",
            "2+2=4",
            "11+1=12",
            "2+3*4=14",
            "10-3+2=9",
            "1+1+1=3",
            "2*3=6",
            "12/4=3",
            "12/3-1=3",
            "12/4-2=1",
            "1-12/3=-3",
            "0.5*2=1.0",
            "5*-2=-10",
            "10/5/2=1",
            "5(2)=10",
            "12/(1+2)=4",
            "(7*5)/5=7",
            "7*(5/5)=7",
            "7*5/5=7",
            ".5*4=2.0" }, delimiter = '=')

    public void baseTest(final String expression, final String solution) throws ParenthesisException, FormatException {
        final BigDecimal expected = new BigDecimal(solution);

        final BigDecimal actual = this.calculator.solve(expression);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1+(2",
            "1+((2)",
            "1+(2+4))" })
    public void badParenthesisTest(final String expression) {
        assertThrows(ParenthesisException.class, () -> {
            this.calculator.solve(expression);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1**2",
            "1*/2" })
    public void formatTest(final String expression) {
        assertThrows(FormatException.class, () -> {
            this.calculator.solve(expression);
        });
    }
}