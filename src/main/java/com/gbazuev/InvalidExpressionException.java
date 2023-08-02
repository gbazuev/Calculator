package com.gbazuev;

public class InvalidExpressionException extends RuntimeException {
    InvalidExpressionException(String expression)   {
        super("\"" + expression + "\" - invalid expression!");
    }
}
