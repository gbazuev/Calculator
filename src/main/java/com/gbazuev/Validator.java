package com.gbazuev;

public class Validator {
    private final int[] NUMBERS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private final char[] OPERATORS = {'+', '-', '/', '*'};

    public boolean isNumber(char symbol) {
        for (int NUM : NUMBERS) {
            if (symbol - 48 == NUM) return true;
        }

        return false;
    }

    public boolean isOperator(char symbol) {
        for (char SYM : OPERATORS) {
            if (SYM == symbol) return true;
        }
        return false;
    }

    public boolean isDot(char symbol)   {
        return symbol == '.';
    }

    public StringBuilder removeNegativeOperators(StringBuilder expression)  {
        for (int index = 0; index < expression.length() - 1; index++)   {
            if (expression.charAt(index) == '-' && isNumber(expression.charAt(index + 1))) {
                if (index > 0)  {
                    if (isOperator(expression.charAt(index - 1)))   {
                        expression.delete(index, index + 1);
                        continue;
                    }
                }

                if (index == 0) {
                    expression.delete(index, index + 1);
                }
            }
        }
        return expression;
    }
}
