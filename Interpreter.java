import java.util.ArrayList;
/**
 * <h1>Interpreter</h1>
 * Class that interprets Lisp language
 * <p>
 *
 * @author Joonho Kim (jkmolina) Alejandro Alvarez (Alejandroav93) Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-03-20
 **/
public class Interpreter implements InterpreterInterface{
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
                System.out.println(lineToCalculate);

            // Logic calculations
            } else if (isLogic(lineToCalculate)) {
                Logic logicOperations = new Logic();
                boolean result = logicOperations.calculateLogic(lineToCalculate);
                results.add(result + "");

            // Structural definitions
            } else if (isStructural(lineToCalculate)) {
                Structures structuralDefinitions = new Structures();
                String structureResult = structuralDefinitions.operateStructure(lineToCalculate);
                results.add(structureResult);

            // Setq and defun calculations
            } else if (lineToCalculate.get(1).equals("setq") || lineToCalculate.get(1).equals("defun")) {
                LispMemory memorySet = new LispMemory();
                String result = "NO RESULT";
                if(lineToCalculate.get(1).equals("setq")) {
                    result = memorySet.setq(lineToCalculate);
                } else {
                    result = memorySet.defun(lineToCalculate);
                }
                results.add(result);

            // Defun calculations
            }

            // If its a function
            else {
                lineToCalculate.remove(lineToCalculate.size() - 1);
                lineToCalculate.remove(0);
                String result = LispMemory.runFunction(lineToCalculate) + "";
                results.add(result);
            }
        }

        System.out.println("The results of the program are:");
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
