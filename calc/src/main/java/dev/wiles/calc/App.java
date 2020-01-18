package dev.wiles.calc;

import java.math.BigDecimal;

public class App {
    static Calculator calculator = new Calculator();

    public static void main(final String[] args) {
        try {
            for (final String arg : args) {
                final String expression = arg.replaceAll("\\s", "");
                final BigDecimal solution = calculator.solve(expression);
                System.out.println(expression + " = " + solution);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
