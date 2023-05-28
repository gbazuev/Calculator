//2. Доработайте калькулятор: при неверном вводе выбрасывайте исключение
package HWm4t6n2;

import java.util.Scanner;

public class Main {
    static final Calculator calculator = new Calculator();
    static Parser parser = new Parser();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            String expression = scanner.nextLine().replaceAll("\\s", "").trim() + " ";
            if (expression.equals("--exit ")) break;

            try {
                System.out.println(calculator.calculate(new StringBuilder(expression)));
            } catch (Exception e)   {
                throw new InvalidExpressionException(expression);
            }
        }
    }
}
