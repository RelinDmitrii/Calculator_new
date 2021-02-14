package com.example.calculator;

import java.util.HashMap;
import java.util.Map;

public class Calculator {

    HashMap<String, String> inputValue;
    static final String KEY_title = "title";
    static final String KEY_ScreenWithSigns = "ScreenWithSigns";
    static final String KEY_ScreenWithNumbers = "ScreenWithNumbers";

    boolean startOperation = false;
    boolean startOperationSin = false;

    /*
    Через конструктор можно заполнить любое количество View, которые будут использоваться в проекту
     */

    public Calculator() {
        inputValue = new HashMap<>();
        inputValue.put(KEY_title, "0");
        inputValue.put(KEY_ScreenWithSigns, "");
        inputValue.put(KEY_ScreenWithNumbers, "");
    }

    /*
    Метод отвечающий за арифмитические действия
     */
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


    public Map<String, String> input(String buttonText) {
        switch (buttonText) {
            case ("+"): {
                calculateAndSet(inputValue.get(KEY_ScreenWithSigns), inputValue.get(KEY_ScreenWithNumbers), inputValue.get(KEY_title), "+");
                break;
            }
            case ("-"): {
                calculateAndSet(inputValue.get(KEY_ScreenWithSigns), inputValue.get(KEY_ScreenWithNumbers), inputValue.get(KEY_title), "-");
                break;
            }
            case ("/"): {
                calculateAndSet(inputValue.get(KEY_ScreenWithSigns), inputValue.get(KEY_ScreenWithNumbers), inputValue.get(KEY_title), "/");
                break;
            }
            case ("*"): {
                calculateAndSet(inputValue.get(KEY_ScreenWithSigns), inputValue.get(KEY_ScreenWithNumbers), inputValue.get(KEY_title), "*");
                break;
            }

            case ("+/-"): {
                if (inputValue.get(KEY_title).charAt(0) != '-') {
                    inputValue.put(KEY_title, "-" + inputValue.get(KEY_title));
                } else {
                    inputValue.put(KEY_title, inputValue.get(KEY_title).toString().replace("-", ""));
                }
                break;

            }
            case ("<-"): {
                if (!inputValue.get(KEY_title).equals("0")) {
                    if (inputValue.get(KEY_title).length() == 1) {
                        inputValue.put(KEY_title, "0");
                    } else {
                        inputValue.put(KEY_title, inputValue.get(KEY_title).substring(0, inputValue.get(KEY_title).length() - 1));
                    }
                }
                break;
            }
            case ("С"): {
                inputValue.put(KEY_ScreenWithSigns, "");
                inputValue.put(KEY_ScreenWithNumbers, "");
                inputValue.put(KEY_title, "0");
                break;
            }
            case ("="): {
                ButtonEqually(inputValue.get(KEY_ScreenWithSigns), inputValue.get(KEY_ScreenWithNumbers), inputValue.get(KEY_title));
                break;
            }
            default: {
                if (inputValue.get(KEY_title).equals("0")) {
                    inputValue.put(KEY_title, buttonText);
                } else {
                    if (startOperation) {
                        inputValue.put(KEY_title, "");
                        startOperation = false;
                    }
                    startOperationSin = true;
                    inputValue.put(KEY_title, inputValue.get(KEY_title) + buttonText);
                }
            }
        }
        return inputValue;
    }

    public void calculateAndSet(String screenSigns, String screenNumbers, String screenTitle, String operator) {

        inputValue.put(KEY_ScreenWithSigns, operator);
        startOperation = true;

        if (inputValue.get(KEY_ScreenWithNumbers).length() > 0 && startOperationSin) {
            String result = String.valueOf(Calculate(inputValue.get(KEY_ScreenWithNumbers), inputValue.get(KEY_title), inputValue.get(KEY_ScreenWithSigns)));
            inputValue.put(KEY_ScreenWithNumbers, result);
            inputValue.put(KEY_title, result);

        }
        startOperationSin = false;
        inputValue.put(KEY_ScreenWithNumbers, inputValue.get(KEY_title));
    }

    public void ButtonEqually(String screenSigns, String screenNumbers, String screenTitle) {
        String result = String.valueOf(Calculate(inputValue.get(KEY_ScreenWithNumbers), inputValue.get(KEY_title), inputValue.get(KEY_ScreenWithSigns)));
        inputValue.put(KEY_ScreenWithNumbers, result);
        inputValue.put(KEY_title, result);
        startOperationSin = false;
    }


}
