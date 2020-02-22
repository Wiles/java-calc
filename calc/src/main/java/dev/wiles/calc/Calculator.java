package dev.wiles.calc;

import java.math.BigDecimal;

import dev.wiles.calc.exceptions.FormatException;
import dev.wiles.calc.exceptions.ParenthesisException;
import dev.wiles.calc.tokenizer.EndToken;
import dev.wiles.calc.tokenizer.Token;
import dev.wiles.calc.tokenizer.Tokenizer;

public class Calculator {

    private BigDecimal primary(final Tokenizer tokenizer) throws FormatException, ParenthesisException {

        Token token = tokenizer.get();

        final Object value = token.getValue();

        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        } else if (value.equals("-")) {
            // Unary minus sign
            return this.primary(tokenizer).negate();
        } else if (value.equals("(")) {
            final BigDecimal left = this.expression(tokenizer);
            token = tokenizer.get();
            if (token.getValue().equals(")")) {
                return left;
            } else {
                throw new ParenthesisException("Unmatched parenthesis found.");
            }
        }

        throw new FormatException("Unknown primary: " + value);
    }

    private BigDecimal term(final Tokenizer tokenizer) throws FormatException, ParenthesisException {
        BigDecimal left = this.primary(tokenizer);
        Token token = tokenizer.get();
        while (true) {
            if (token.getValue().equals("*")) {
                left = left.multiply(this.primary(tokenizer));
                token = tokenizer.get();
            } else if (token.getValue().equals("/")) {
                left = left.divide(this.primary(tokenizer));
                token = tokenizer.get();
            } else if (token.getValue().equals("(")) {
                // Open parenthesis after a number is an implied multiplication IE. 2(10) = 20
                tokenizer.putBack(token);
                left = left.multiply(this.primary(tokenizer));
                return left;
            } else {
                tokenizer.putBack(token);
                return left;
            }
        }
    }

    private BigDecimal expression(final Tokenizer tokenizer) throws FormatException, ParenthesisException {
        BigDecimal left = this.term(tokenizer);
        Token token = tokenizer.get();

        while (!(token instanceof EndToken)) {
            if (token.getValue().equals("+")) {
                left = left.add(this.term(tokenizer));
                token = tokenizer.get();
            } else if (token.getValue().equals("-")) {
                left = left.subtract(this.term(tokenizer));
                token = tokenizer.get();
            } else if (token.getValue().equals(")")) {
                tokenizer.putBack(token);
                return left;
            } else {
                throw new FormatException("Unknown expression: " + token.getValue());
            }
        }
        return left;
    }

    /**
     * Takes in a mathematical expression and attempts to solves it with BEDMAS.
     * 
     * Supports parenthesis, addition, subtraction, multiplication and division.
     * 
     * @param expression
     * @return the solution to the expression
     * @throws FormatException
     * @throws ParenthesisException
     */
    public BigDecimal solve(final String expression) throws FormatException, ParenthesisException {
        final Tokenizer tokenizer = new Tokenizer(expression);

        final BigDecimal solution = this.expression(tokenizer);

        if (tokenizer.get().getValue().equals(")")) {
            throw new ParenthesisException("Unmatch parenthesis found.");
        }

        return solution;
    }
}