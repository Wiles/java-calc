package dev.wiles.calc.tokenizer;

public class EndToken implements Token {

    @Override
    public Object getValue() {
        return "END";
    }
}
