package com.example.calculator;

public class Calculator {

    public long Calculate(String first, String second, String operator) {

        long firstNumber = Long.parseLong(first);
        long secondNumber = Long.parseLong(second);

        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                if (secondNumber == 0) {
                    return 0;
                }
                return firstNumber / secondNumber;
        }
        return 0;
    }

}
