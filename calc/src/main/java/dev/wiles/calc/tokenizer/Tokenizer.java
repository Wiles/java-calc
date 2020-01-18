package dev.wiles.calc.tokenizer;

import java.math.BigDecimal;

import dev.wiles.calc.exceptions.FormatException;

public class Tokenizer {

    private final String expression;

    private int position = 0;
    private Token buffer;

    /**
     * @param expression math expression to tokenize
     */
    public Tokenizer(final String expression) {
        this.expression = expression;
    }

    /**
     * Gets the next token from the stream
     * 
     * @return a token
     * @throws FormatException thrown if an unknown character is found in the stream
     */
    public Token get() throws FormatException {
        if (this.buffer != null) {
            final Token token = this.buffer;
            this.buffer = null;
            return token;
        }

        if (this.position == this.expression.length()) {
            return new EndToken();
        }

        final String character = this.expression.substring(this.position, this.position + 1);

        if (".0123456789".contains(character)) {
            String number = this.expression.substring(this.position);
            number = number.split("[^0-9.]", 2)[0];
            this.position += number.length();
            return new NumberToken(new BigDecimal(number));
        } else if ("+*-/()^".contains(character)) {
            this.position++;
            return new OpToken(character);
        } else {
            throw new FormatException("Unknown character: " + character);
        }
    }

    /**
     * Places a token back into the steam. Only a single token be put back between
     * calls to get
     * 
     * @param token the token to be put back into the stream
     * @throws FormatException throw if this function is called more than once
     *                         before calling get again
     */
    public void putBack(final Token token) throws FormatException {
        if (this.buffer != null) {
            throw new FormatException("Token buffer already full");
        }
        this.buffer = token;
    }
}