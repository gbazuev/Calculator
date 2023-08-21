package com.gbazuev;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Parser {
    private static final Validator validator = new Validator();

    public ArrayList<Character> getOperators(StringBuilder expression) {
        ArrayList<Character> localOperatorsStorage = new ArrayList<>();
        expression = validator.removeNegativeOperators(expression);

        for (int index = 0; index < expression.length(); index++) {
            if (validator.isOperator(expression.charAt(index))) {
                localOperatorsStorage.add(expression.charAt(index));
            }
        }

        return localOperatorsStorage;
    }

    public ArrayList<BigDecimal> getOperands(StringBuilder expression) {
        ArrayList<BigDecimal> localOperandsStorage = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        for (int index = 0; index < expression.length(); index++) {
            if (validator.isNumber(expression.charAt(index)) || validator.isDot(expression.charAt(index))) {
                token.append(expression.charAt(index));
                continue;
            }

            if (expression.charAt(index) == '-') {
                if (index > 0) {
                    if (validator.isOperator(expression.charAt(index - 1)) &&
                            validator.isNumber(expression.charAt(index + 1))) {
                        token.append(expression.charAt(index));
                        continue;
                    }
                }

                if (index == 0) {
                    token.append(expression.charAt(index));
                    continue;
                }
            }

            localOperandsStorage.add(new BigDecimal(token.toString()));
            token.delete(0, token.length());
        }

        return localOperandsStorage;
    }
}
