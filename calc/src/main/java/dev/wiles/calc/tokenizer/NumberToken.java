package dev.wiles.calc.tokenizer;

import java.math.BigDecimal;

public class NumberToken implements Token {
    private final BigDecimal value;

    public NumberToken(final BigDecimal number) {
        this.value = number;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}