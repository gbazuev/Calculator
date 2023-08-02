package com.gbazuev;

public class Calculator {
    private static final TokenStorage tokenStorage = new TokenStorage();
    private static final Parser parser = new Parser();

    public int calculate(StringBuilder expression) {
        int bracketStart = expression.lastIndexOf("(");
        int bracketEnd = expression.indexOf(")", bracketStart);

        //If the expression does not have brackets
        if (bracketStart == -1 && bracketEnd == -1) {
            tokenStorage.setOperandsList(parser.getOperands(expression));
            tokenStorage.setOperatorsList(parser.getOperators(expression));

            while (!tokenStorage.getOperatorsList().isEmpty()) {
                char currentOperator = tokenStorage.getOperator();
                int[] operands = tokenStorage.getOperands();

                int result = action(operands[0], operands[1], currentOperator);
                tokenStorage.replaceOperands(result);
                tokenStorage.deleteOperator();
            }

            return tokenStorage.getOperandsList().get(0);
        }

        else {
            StringBuilder bracketExpression = new StringBuilder(expression.substring(bracketStart + 1, bracketEnd) + " ");
            tokenStorage.setOperandsList(parser.getOperands(bracketExpression));
            tokenStorage.setOperatorsList(parser.getOperators(bracketExpression));

            while (!tokenStorage.getOperatorsList().isEmpty()) {
                char operator = tokenStorage.getOperator();
                int[] operands = tokenStorage.getOperands();

                int result = action(operands[0], operands[1], operator);
                tokenStorage.replaceOperands(result);
                tokenStorage.deleteOperator();
            }

            return calculate(expression.replace(bracketStart, bracketEnd + 1, String.valueOf(tokenStorage.getOperandsList().get(0))));
        }
    }

    private int action(int first, int second, char operator) {
        if (operator == '+') return add(first, second);
        if (operator == '-') return substract(first, second);
        if (operator == '*') return multiply(first, second);
        if (operator == '/') return divide(first, second);
        return 0;
    }

    private int add(int first, int second) {
        return first + second;
    }

    private int substract(int first, int second) {
        return first - second;
    }

    private int multiply(int first, int second) {
        return first * second;
    }

    private int divide(int first, int second) {
        return first / second;
    }
}
