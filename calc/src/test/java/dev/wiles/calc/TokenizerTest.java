package dev.wiles.calc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.wiles.calc.exceptions.FormatException;
import dev.wiles.calc.tokenizer.EndToken;
import dev.wiles.calc.tokenizer.Token;
import dev.wiles.calc.tokenizer.Tokenizer;

public class TokenizerTest {

    @Test
    @DisplayName("1+1")
    public void baseTest() throws Exception {
        final Tokenizer tokenizer = new Tokenizer("1+1");
        assertEquals(BigDecimal.ONE, tokenizer.get().getValue());
        assertEquals("+", tokenizer.get().getValue());
        assertEquals(BigDecimal.ONE, tokenizer.get().getValue());
        assertTrue(tokenizer.get() instanceof EndToken);
    }

    @Test
    @DisplayName("1+1+1")
    public void twoOpTest() throws Exception {
        final Tokenizer tokenizer = new Tokenizer("1+1+1");
        assertEquals(BigDecimal.ONE, tokenizer.get().getValue());
        assertEquals("+", tokenizer.get().getValue());
        assertEquals(BigDecimal.ONE, tokenizer.get().getValue());
        assertEquals("+", tokenizer.get().getValue());
        assertEquals(BigDecimal.ONE, tokenizer.get().getValue());
        assertTrue(tokenizer.get() instanceof EndToken);
    }

    @Test
    @DisplayName("12+1")
    public void doubleDigitTest() throws Exception {
        final Tokenizer tokenizer = new Tokenizer("12+1");
        assertEquals(new BigDecimal(12), tokenizer.get().getValue());
        assertEquals("+", tokenizer.get().getValue());
        assertEquals(BigDecimal.ONE, tokenizer.get().getValue());
        assertTrue(tokenizer.get() instanceof EndToken);
    }

    @Test
    @DisplayName("1*1")
    public void multipleTest() throws Exception {
        final Tokenizer tokenizer = new Tokenizer("1*1");
        assertEquals(BigDecimal.ONE, tokenizer.get().getValue());
        assertEquals("*", tokenizer.get().getValue());
        assertEquals(BigDecimal.ONE, tokenizer.get().getValue());
        assertTrue(tokenizer.get() instanceof EndToken);
    }

    @Test
    @DisplayName("1?1")
    public void unknownCharacterTest() {
        assertThrows(FormatException.class, () -> {
            final Tokenizer tokenizer = new Tokenizer("1?1");
            tokenizer.get();
            tokenizer.get();
        });
    }

    @Test
    public void multiplePutBackTest() {
        assertThrows(FormatException.class, () -> {

            final Tokenizer tokenizer = new Tokenizer("1+1");
            final Token token = tokenizer.get();
            tokenizer.putBack(token);
            tokenizer.putBack(token);
        });
    }
}
