package dev.wiles.calc.tokenizer;

public class OpToken implements Token {
    private final String value;

    public OpToken(String op) {
        this.value = op;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}