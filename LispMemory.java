import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class LispMemory {
    public static LinkedHashMap<String, ArrayList<String>> variableMemory = new LinkedHashMap<>();
    public static LinkedHashMap<String, ArrayList<String>> functionMemory = new LinkedHashMap<>();

    public LispMemory() {
        ArrayList<String> arithmetic = new ArrayList<>();
        arithmetic.add("arithmetic");
        functionMemory.put("setq", null);
        functionMemory.put("equal", null);
        functionMemory.put("defun", null);
        functionMemory.put("cond", null);
        functionMemory.put("print", null);
        functionMemory.put("+" , arithmetic);
        functionMemory.put("-", arithmetic);
        functionMemory.put("*", arithmetic);
        functionMemory.put("/", arithmetic);
    }


    // (defun fact (n) (cond((equal n 1) 1) ((* n (fact (- n 1))))))
    public String defun(ArrayList<String> line) {
        ArrayList<String> setqArray = new ArrayList<>();
        setqArray.add("(");
        setqArray.add("setq");
        setqArray.add("variableName");
        setqArray.add("null");
        setqArray.add(")");
        String setFunction = "";
        String functionName = line.get(2);
        ArrayList<String> functionDefinition = new ArrayList<>();
        // Function parameters
        int endOfParameters = 0;
        for(int i = 4; i < line.size(); i ++) {
            String parameter = line.get(i);
            if(parameter.equals(")")) {
                endOfParameters = i;
                break;
            } else {
                setqArray.set(2,parameter);
                functionDefinition.add(parameter);
                System.out.println("CallingSETQ");
                setq(setqArray);
            }
        }
        line.remove(line.size() - 1);
        for(int i = endOfParameters + 1; i < line.size(); i++) {
            functionDefinition.add(line.get(i));
        }
        functionMemory.put(functionName,functionDefinition);
        System.out.println(functionMemory);
        System.out.println(functionMemory.get("areacirc"));
        System.out.println(functionDefinition);
        return "Defined function " + functionName;
    }

    // areacirc 2 3.1415
    public static String runFunction(ArrayList<String> line) {
        System.out.println("RUNNING FUNCTION");
        System.out.println(line);
        System.out.println("IN HERE");
        String result = "";
        ArrayList<String> setqArray = new ArrayList<>();
        setqArray.add("(");
        setqArray.add("setq");
        setqArray.add("variableName");
        setqArray.add("0");
        setqArray.add(")");

        Interpreter interpreter = new Interpreter();
        ArrayList<ArrayList<String>> functionCalculations = new ArrayList<>();
        ArrayList<String> lineToCalculate = functionMemory.get(line.get(0));
        System.out.println("FIRST LINE" + lineToCalculate);
        line.remove(0);
        int occurrencesToRemove = 0;
        for(int i = 0; i < lineToCalculate.size(); i++) {
            System.out.println("INHERE WHAT");
            System.out.println("LINE TO CALCuLATE Is" + lineToCalculate);
            if(lineToCalculate.get(i).equals("(")) {
                break;
            } else {
                occurrencesToRemove ++;
                System.out.println("SETTING VARIABLES");
                String variableName = lineToCalculate.get(i);
                String variableValue = line.remove(0);
                Collections.replaceAll(lineToCalculate,variableName,variableValue);
            }
        }
        for(int i = 0; i <occurrencesToRemove; i++) {
            lineToCalculate.remove(0);
        }

        for(int i = 0; i < lineToCalculate.size(); i ++ ) {
            try {
                String currentValue = lineToCalculate.get(i);
                double variableValue = Double.parseDouble(variableMemory.get(currentValue).get(1));
                Collections.replaceAll(lineToCalculate,currentValue,variableValue + "");

            } catch (Exception E) {
                continue;
            }
        }
        System.out.println("THE LINE TO CALCULATE IS " + lineToCalculate);
        functionCalculations.add(lineToCalculate);
        ArrayList<String> functionResult = interpreter.calculate(functionCalculations);
        System.out.println(functionResult);
        System.out.println(variableMemory);


        return  functionResult + "";
    }

    // (setq x ( + 3 3))
    public String setq(ArrayList<String> line) {
        String variableValue = "";
        String variableName = line.get(2);
        String variableType = "";
        ArrayList<String> variableInfo = new ArrayList<>();
        double result = 1.0d;
        if (line.get(3).equals("(")) {
            if(line.get(4).equals("list")) {
                Structures structures = new Structures();
                for(int i = 0; i < 3; i++) {
                    line.remove(i);
                }
                ArrayList<String> listValues = structures.list(line);
                variableMemory.put(variableName,listValues);
                System.out.println("LIST");
                return "Set " + variableName + " to " + listValues;
            } else if (line.get(4).equals("+") || line.get(4).equals("-") || line.get(4).equals("*")
                    || line.get(4).equals("/")) {
                System.out.println("ARITHMETIC");
                variableType = "arithmetic";

                line.remove(line.size() - 1);
                for(int i = 0; i < 3; i++) {
                    line.remove(0);
                }
                Arithmetic arithmetic = new Arithmetic();
                result = arithmetic.calculateArithmetic(line);
                variableValue = String.valueOf(result);
            } else {
                // (setq resultado (areacirc 2 3.1415))
                ArrayList<String> functionToRun = new ArrayList<>();
                for(int i = 4; i < line.size(); i++) {
                    if(line.get(i).equals(")")) {
                        break;
                    } else {
                        functionToRun.add(line.get(i));
                    }
                }
                variableValue = runFunction(functionToRun);
            }
        } else if (line.get(3).charAt(0) == '"') {
            variableType = "string";
            variableValue = line.get(3);
        } else {
            variableType = "arithmetic";
            variableValue = line.get(3);
        }


        variableInfo.add(variableType);
        variableInfo.add(variableValue);
        variableMemory.put(variableName,variableInfo);

        return "Set " + variableName + " to " + variableValue;
    }

    public boolean arithmeticOperator(String key) {
        if(functionMemory.containsKey(key) && functionMemory.get(key) != null && functionMemory.get(key).get(0).equals("arithmetic")) {
            return true;
        }
        return false;
    }

    public int getVariableValue(String variable) {
        try {
            return Integer.parseInt(variableMemory.get(variable).get(1));
        } catch (Exception E) {
            System.err.println("Variable was not numeric");
            return -1;
        }
    }
}
