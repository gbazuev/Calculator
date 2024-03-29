package com.gbazuev;

import java.math.BigDecimal;
import java.util.ArrayList;

public class TokenStorage {
    private ArrayList<Character> operators = new ArrayList<>();
    private ArrayList<BigDecimal> operands = new ArrayList<>();

    public void setOperatorsList(ArrayList<Character> operators) {
        this.operators = operators;
    }

    public void setOperandsList(ArrayList<BigDecimal> operands) {
        this.operands = operands;
    }

    public ArrayList<Character> getOperatorsList() {
        return operators;
    }

    public ArrayList<BigDecimal> getOperandsList() {
        return operands;
    }

    public BigDecimal[] getOperands() {
        int currentOperatorIndex = getOperatorIndex();
        return new BigDecimal[]{operands.get(currentOperatorIndex), operands.get(currentOperatorIndex + 1)};
    }

    public char getOperator() {
        return operators.get(getOperatorIndex());
    }

    public void replaceOperands(BigDecimal result) {
        int currentOperatorIndex = getOperatorIndex();
        operands.remove(currentOperatorIndex);
        operands.remove(currentOperatorIndex);
        operands.add(currentOperatorIndex, result);
    }

    public void deleteOperator() {
        operators.remove(getOperatorIndex());
    }

    private int getOperatorIndex() {
        for (int index = 0; index < operators.size(); index++) {
            if (operators.get(index) == '*' || operators.get(index) == '/') {
                return index;
            }
        }
        return 0;
    }
}
