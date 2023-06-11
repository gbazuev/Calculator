package HWm4t6n2.src.java;

public class InvalidExpressionException extends RuntimeException {
    InvalidExpressionException(String expression)   {
        super("\"" + expression + "\" - invalid expression!");
    }
}
