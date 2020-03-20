import java.util.ArrayList;

public class Interpreter {
    int i = 0;
    public ArrayList<String> calculate(ArrayList<ArrayList<String>> stack) {
        ArrayList<String> results = new ArrayList<>();

        for (int i = 0; i < stack.size(); i++) {
            ArrayList<String> lineToCalculate = stack.get(i);
            System.out.println(lineToCalculate);

            // Arithmetic calculations
            if (isArithmetic(lineToCalculate)) {
                Arithmetic arithmetic = new Arithmetic();
                double result = arithmetic.calculateArithmetic(lineToCalculate);
                results.add(result + "");
                System.out.println("RESULT =" + result);
                System.out.println(lineToCalculate);
                System.out.println("ITS ARITHMETIC BOYS");

            // Logic calculations
            } else if (isLogic(lineToCalculate)) {
                Logic logicOperations = new Logic();
                boolean result = logicOperations.calculateLogic(lineToCalculate);
                System.out.println("ITS LOGICAL BROOOOO");
                System.out.println("The result of this calculation is " + result);
                results.add(result + "");

            // Structural definitions
            } else if (isStructural(lineToCalculate)) {
                Structures structuralDefinitions = new Structures();
                String structureResult = structuralDefinitions.operateStructure(lineToCalculate);
                results.add(structureResult);
                System.out.println("ITS A TRUCTUUUUREEE YEAHH BOII");

            // Setq calculations
            } else if (lineToCalculate.get(1).equals("setq")) {
                LispMemory memorySet = new LispMemory();
                String result = memorySet.setq(lineToCalculate);
                results.add(result);
                System.out.println("The result is " + result);
                System.out.println(LispMemory.variableMemory);
                System.out.println("ITS A SETQ GUYSSSS");

            // Defun calculations
            } else if(lineToCalculate.get(1).equals("defun")) {

            }

            for(int j = 0; j < lineToCalculate.size(); j++) {
                String currentWord =  lineToCalculate.get(j);

            }
        }
        System.out.println(results);
        return results;
    }

    private boolean isLogic(ArrayList<String> line) {
        String logicalValue = line.get(1);

        if(logicalValue.equals(">") || logicalValue.equals("<") || logicalValue.equals("equal")) {
            return true;
        }
        return false;
    }

    private boolean isArithmetic(ArrayList<String> line) {
        LispMemory functions = new LispMemory();
        for(int i = 0; i < line.size(); i ++) {
            String currentWord = line.get(i);
            if(functions.arithmeticOperator(currentWord)) {
                continue;
            } else if(currentWord.equals("(") || currentWord.equals(")")) {
                continue;
            } else {
                try {
                    Double.parseDouble(currentWord);
                } catch (Exception E) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isStructural(ArrayList<String> line) {
        String structuralValue = line.get(1);
        if(structuralValue.equals("cond") || structuralValue.equals("list") || structuralValue.equals("quote")
                ||structuralValue.equals("'") || structuralValue.equals("first") || structuralValue.equals("second")
                || structuralValue.equals("third") || structuralValue.equals("fourth") || structuralValue.equals("fifth")
                || structuralValue.equals("sixth") || structuralValue.equals("seventh")|| structuralValue.equals("eighth")
                || structuralValue.equals("ninth") || structuralValue.equals("tenth") ) {
            return true;
        }
        return false;
    }




}
