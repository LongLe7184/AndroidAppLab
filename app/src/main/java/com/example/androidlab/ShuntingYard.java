package com.example.androidlab;

import java.util.*;

public class ShuntingYard {

    String expression = "";

    public ShuntingYard(String EXP) {
        this.expression = EXP;
    }

    double calculateEquation() {
        List<String> postfix = infixToPostfix(expression);
        return evaluatePostfix(postfix);
    }

    public static List<String> infixToPostfix(String expression) {
        Map<Character, Integer> precedence = Map.of('+', 1, '-', 1, '*', 2, '/', 2);
        Stack<Character> operators = new Stack<>();
        List<String> output = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            // Include digits and decimal points in the number
            if (Character.isDigit(ch) || ch == '.') {
                number.append(ch); // Accumulate multi-digit numbers with decimal points
            } else if (ch == '%') {
                // Handle '%' by dividing the number by 100
                if (number.length() > 0) {
                    double percentValue = Double.parseDouble(number.toString()) / 100.0;
                    output.add(String.valueOf(percentValue));
                    number.setLength(0); // Reset the number builder
                }
            } else {
                if (number.length() > 0) {
                    output.add(number.toString());
                    number.setLength(0);
                }

                if (precedence.containsKey(ch)) {
                    while (!operators.isEmpty() && precedence.getOrDefault(operators.peek(), 0) >= precedence.get(ch)) {
                        output.add(operators.pop().toString());
                    }
                    operators.push(ch);
                }
            }
        }

        // Handle any remaining number or '%' at the end of the expression
        if (number.length() > 0) {
            output.add(number.toString());
        }
        if (expression.endsWith("%")) {
            double percentValue = Double.parseDouble(output.remove(output.size() - 1)) / 100.0;
            output.add(String.valueOf(percentValue));
        }

        while (!operators.isEmpty()) {
            output.add(operators.pop().toString());
        }

        return output;
    }




    // Evaluate postfix notation
    public static double evaluatePostfix(List<String> postfix) {
        Stack<Double> stack = new Stack<>();
        for (String token : postfix) {
            // Check if the token is a number (supports integers and decimals)
            if (token.matches("\\d+(\\.\\d+)?") || token.matches("\\d\\.\\d+E-\\d+")) {
                stack.push(Double.parseDouble(token));
            } else {
                // Pop two operands from the stack for the operator
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": stack.push(a / b); break;
                }
            }
        }
        return stack.pop();
    }

}