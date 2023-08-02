package com.gbazuev;

import java.util.Scanner;

public class Main {
    static final Calculator calculator = new Calculator();
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
