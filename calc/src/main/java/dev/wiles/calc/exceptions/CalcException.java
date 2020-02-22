package dev.wiles.calc.exceptions;

public abstract class CalcException extends Exception {

    private static final long serialVersionUID = -8107448293843373621L;

    public CalcException(final String message) {
        super(message);
    }
}
