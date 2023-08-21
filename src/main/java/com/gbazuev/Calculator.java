package com.gbazuev;

import java.math.BigDecimal;

public class Calculator {
    private static final TokenStorage tokenStorage = new TokenStorage();
    private static final Parser parser = new Parser();

    public BigDecimal calculate(StringBuilder expression) {
        int bracketStart = expression.lastIndexOf("(");
        int bracketEnd = expression.indexOf(")", bracketStart);

        //If the expression does not have brackets
        if (bracketStart == -1 && bracketEnd == -1) {
            tokenStorage.setOperandsList(parser.getOperands(expression));
            tokenStorage.setOperatorsList(parser.getOperators(expression));

            while (!tokenStorage.getOperatorsList().isEmpty()) {
                char currentOperator = tokenStorage.getOperator();
                BigDecimal[] operands = tokenStorage.getOperands();

                BigDecimal result = action(operands[0], operands[1], currentOperator);
                tokenStorage.replaceOperands(result);
                tokenStorage.deleteOperator();
            }

            return tokenStorage.getOperandsList().get(0);
        } else {
            StringBuilder bracketExpression = new StringBuilder(expression.substring(bracketStart + 1, bracketEnd) + " ");
            tokenStorage.setOperandsList(parser.getOperands(bracketExpression));
            tokenStorage.setOperatorsList(parser.getOperators(bracketExpression));

            while (!tokenStorage.getOperatorsList().isEmpty()) {
                char operator = tokenStorage.getOperator();
                BigDecimal[] operands = tokenStorage.getOperands();

                BigDecimal result = action(operands[0], operands[1], operator);
                tokenStorage.replaceOperands(result);
                tokenStorage.deleteOperator();
            }

            return calculate(expression.replace(bracketStart, bracketEnd + 1, String.valueOf(tokenStorage.getOperandsList().get(0))));
        }
    }

    private BigDecimal action(BigDecimal first, BigDecimal second, char operator) {
        if (operator == '+') return add(first, second);
        if (operator == '-') return substract(first, second);
        if (operator == '*') return multiply(first, second);
        if (operator == '/') return divide(first, second);
        return new BigDecimal("0");
    }

    private BigDecimal add(BigDecimal first, BigDecimal second) {
        return new BigDecimal(String.valueOf(first.add(second)));
    }

    private BigDecimal substract(BigDecimal first, BigDecimal second) {
        return new BigDecimal(String.valueOf(first.subtract(second)));
    }

    private BigDecimal multiply(BigDecimal first, BigDecimal second) {
        return new BigDecimal(String.valueOf(first.multiply(second)));
    }

    private BigDecimal divide(BigDecimal first, BigDecimal second) {
        return new BigDecimal(String.valueOf(first.divide(second)));
    }
}
