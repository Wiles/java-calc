package dev.wiles.calc.tokenizer;

public class OpToken implements Token {
    private final String value;

    public OpToken(final String op) {
        this.value = op;
    }

    @Override
    public Object getValue() {
        return this.value;
    }
}