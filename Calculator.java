package HWm4t6n2.src.java;

public class Calculator {
    static TokenStorage tokenStorage = new TokenStorage();
    static Parser parser = new Parser();

    public int calculate(StringBuilder expression) {
        int bracketStart = expression.lastIndexOf("(");
        int bracketEnd = expression.indexOf(")", bracketStart);

        if (bracketStart == -1 && bracketEnd == -1) {

            tokenStorage.operands = parser.getOperands(expression);
            tokenStorage.operators = parser.getOperators(expression);

            while (tokenStorage.operators.size() != 0)   {
                char currentOperator = tokenStorage.getOperator();
                int[] operands = tokenStorage.getOperands();

                int result = action(operands[0], operands[1], currentOperator);
                tokenStorage.replaceOperands(result);
                tokenStorage.deleteOperator();
            }

            return tokenStorage.operands.get(0);
        }

        tokenStorage.operands = parser.getOperands(new StringBuilder(expression.substring(bracketStart + 1, bracketEnd)).append(" "));
        tokenStorage.operators = parser.getOperators(new StringBuilder(expression.substring(bracketStart + 1, bracketEnd)).append(" "));

        while (tokenStorage.operators.size() != 0) {
            char currentOperator = tokenStorage.getOperator();
            int[] operands = tokenStorage.getOperands();

            int result = action(operands[0], operands[1], currentOperator);
            tokenStorage.replaceOperands(result);
            tokenStorage.deleteOperator();
        }

        return calculate(expression.replace(bracketStart, bracketEnd + 1, String.valueOf(tokenStorage.operands.get(0))));
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
