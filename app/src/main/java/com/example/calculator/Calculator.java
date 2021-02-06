package com.example.calculator;

public class Calculator {

    public long Calculate (String first, String second, String operator){

        long a = Long.parseLong(first);
        long b = Long.parseLong(second);

        switch (operator){
            case"+":
                return a+b;
            case"-":
                return a-b;
            case "*":
                return a*b;
            case "/":
                if (b == 0){
                    return 0;
                }
                return a/b;
        }
        return 0;
    }

}
