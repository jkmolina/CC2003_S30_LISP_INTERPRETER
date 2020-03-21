import java.util.ArrayList;
/**
 * <h1>Arithmetic</h1>
 * Class in charge of the arithmetic operations of the interpreter
 * <p>
 *
 * @author Joonho Kim (jkmolina) Alejandro Alvarez (Alejandroav93) Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-03-20
 **/
public class Arithmetic {

    /**
     * Calculates the arithmetic value of a lisp expression
     * @param line line to be calculated
     * @return double with the calculated value
     */
    public double calculateArithmetic(ArrayList<String> line) {
        LispMemory variables = new LispMemory();
        double result = 1;
        if(line.size() == 1) {
            try {
                return Double.parseDouble(line.get(0));
            } catch (Exception E) {
                return variables.getVariableValue(line.get(0));
            }
        }

        int index = 0;
        while(line.size() > 1) {
            int openParentheses = 0;
            int closedParentheses = 0;

            for(int i = 0; i < line.size(); i ++) {
                String currentWord = line.get(i);
                if(currentWord.equals("(")) {
                    openParentheses = i;
                } else if (currentWord.equals(")")) {
                    closedParentheses = i + 1;
                    break;
                }
            }
            calculateParenthesis(openParentheses,closedParentheses,line);
            index++;
        }

        result = Double.parseDouble(line.get(0));
        return result;
    }

    /**
     * Calculates sub-parentheses inside an arithmetic expression
     * @param line line to be
     * @return
     */
    public void calculateParenthesis(int open, int closed, ArrayList<String> line) {
        double result = 0;
        String operator = line.get(open + 1);
        switch (operator) {
            case "+":

                for(int i = open + 2; i < closed - 1; i ++) {
                    double number;
                    try {
                        number = Double.parseDouble(line.get(i));
                    } catch (Exception E) {
                        number = Double.parseDouble(LispMemory.variableMemory.get(line.get(i)).get(1));
                    }
                    result += number;
                }
                break;

            case "-":

                result = Double.parseDouble(line.get(open + 2));
                for(int i = open + 3; i < closed - 1; i ++) {
                    double number;
                    try {
                        number = Double.parseDouble(line.get(i));
                    } catch (Exception E) {
                        number = Double.parseDouble(LispMemory.variableMemory.get(line.get(i)).get(1));
                    }
                    result -= number;
                }
                break;

            case "*":
                System.out.println("IN MULT");
                result = 1;
                for(int i = open + 2; i < closed - 1; i ++) {
                    double number;
                    try {
                        number = Double.parseDouble(line.get(i));
                    } catch (Exception E) {
                        number = Double.parseDouble(LispMemory.variableMemory.get(line.get(i)).get(1));
                    }
                    result *= number;
                }
                break;

            case "/":
                result = Double.parseDouble(line.get(open + 2));
                for(int i = open + 3; i < closed - 1; i ++) {
                    double number;
                    try {
                        number = Double.parseDouble(line.get(i));
                    } catch (Exception E) {
                        number = Double.parseDouble(LispMemory.variableMemory.get(line.get(i)).get(1));
                    }
                    result /= number;
                }
                break;
        }

        for(int i = closed - 1; i > open; i --) {
            try {
                line.remove(i);
            } catch (Exception E) {
                System.err.println("An error occurred during the calculation");
            }
        }
        line.set(open, String.valueOf(result));
    }
}
