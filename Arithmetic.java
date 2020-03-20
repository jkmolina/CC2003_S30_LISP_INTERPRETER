import java.util.ArrayList;

public class Arithmetic {

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
        while(line.size() > 1 && index <90) {
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
            System.out.println("LINE AFTER CALC IS");
            System.out.println(line);
            index++;
        }

        result = Double.parseDouble(line.get(0));
        return result;
    }

    public void calculateParenthesis(int open, int closed, ArrayList<String> line) {
        double result = 0;
        String operator = line.get(open + 1);
        System.out.println("MY OPERATOR IS " + operator);
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
                    double number = Double.parseDouble(line.get(i));
                    result -= number;
                }
                break;

            case "*":
                System.out.println("IN MULT");
                result = 1;
                for(int i = open + 2; i < closed - 1; i ++) {
                    double number = Double.parseDouble(line.get(i));
                    result *= number;
                }
                break;

            case "/":
                result = Double.parseDouble(line.get(open + 2));
                for(int i = open + 3; i < closed - 1; i ++) {
                    double number = Double.parseDouble(line.get(i));
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
