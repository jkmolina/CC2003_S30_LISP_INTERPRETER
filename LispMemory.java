import java.util.ArrayList;
import java.util.LinkedHashMap;

public class LispMemory {
    public static LinkedHashMap<String, ArrayList<String>> variableMemory = new LinkedHashMap<>();
    LinkedHashMap<String, ArrayList<String>> functionMemory = new LinkedHashMap<>();

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
            } else {
                System.out.println("ARITHMETIC");
                variableType = "arithmetic";

                line.remove(line.size() - 1);
                for(int i = 0; i < 3; i++) {
                    line.remove(0);
                }
                Arithmetic arithmetic = new Arithmetic();
                result = arithmetic.calculateArithmetic(line);
                variableValue = String.valueOf(result);
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
