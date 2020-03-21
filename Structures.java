import java.util.ArrayList;
/**
 * <h1>Structures</h1>
 * Class that operates lists, quote, and cond
 * <p>
 *
 * @author Joonho Kim (jkmolina) Alejandro Alvarez (Alejandroav93) Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-03-20
 **/
public class Structures {
    /**
     * Gets the structure type: list, quote, or cond according to the input line
     * @param line the line that is going to be analyzed
     * @return String with the result of the operation
     */
    public String operateStructure(ArrayList<String> line) {
        String result = "";
        String operatorType = line.get(1);
        switch (operatorType) {
            case "list":
                result = list(line) + "";
                break;
            case "quote":
                result = quoteResult(line);
                break;
            case "cond":
                result = conditional(line) + "";
                //(cond((equal n 1) 3) (2))

                break;
            case "first": case "second": case "third": case "fourth": case "fifth": case "sixth": case "seventh":
            case "eighth": case "ninth": case "tenth":
                result = accessListElement(line);
                break;
        }
        return result;
    }

    /**
     * Analyzes cond
     * @param line the line that is going to be analyzed
     * @return String with the result of the operation (true or false)
     */
    private ArrayList<String> conditional(ArrayList<String> line) {
        ArrayList<String> result = new ArrayList<>();

        int endOfConditional = 0;
        ArrayList<String> clonedLine = new ArrayList<>(line);   // Avoids messing up the original line
        ArrayList<String>  conditionalFragment = new ArrayList<>();
        for(int i = 3; i < line.size(); i++) {
            if(clonedLine.get(i).equals(")")) {
                endOfConditional = i;
                conditionalFragment.add(clonedLine.get(i));
                break;
            } else {
                conditionalFragment.add(clonedLine.get(i));
            }
        }
        System.out.println(conditionalFragment);
        boolean booleanResult = conditionalResult(conditionalFragment);
        System.out.println(line);
        System.out.println(clonedLine);

        if(booleanResult) {
            for(int i = endOfConditional; i < clonedLine.size(); i++) {
                int openParentheses = 0;
                int closedParentheses = 0;
                System.out.println(clonedLine.get(i));
                if (clonedLine.get(i).equals("(")) {
                    openParentheses ++;
                    result.add(clonedLine.get(i));
                } else if(clonedLine.get(i).equals(")")) {
                    if(openParentheses == closedParentheses) {
                        if (openParentheses == 0) {
                            break;
                        }
                        result.add(clonedLine.get(i));
                        break;
                    }
                    result.add(clonedLine.get(i));
                } else {
                    result.add(clonedLine.get(i));
                }
            }
        } else {
            int openingParenthesis = 0;
            int closingParenthesis = 0;
            int openingIndex = 0;
            clonedLine.remove(clonedLine.size() - 1);
            for(int i = clonedLine.size() - 1; i > 0; i--) {

                if(clonedLine.get(i).equals(")")) {
                    closingParenthesis ++;
                }
                if(clonedLine.get(i).equals("(")) {
                    openingParenthesis++;
                    if(openingParenthesis == closingParenthesis) {
                        openingIndex = i;
                        break;
                    }

                }
            }
            for(int i = openingIndex + 1; i < clonedLine.size() - 1; i++) {
                result.add(clonedLine.get(i));
            }

        }
        return result;
    }

    /**
     * Analyzes the truth value of the conditional fragment of cond
     * @param conditionalFragment part of cond that contains the comparison to be deemed true or false
     * @return boolean with the result
     */
    private boolean conditionalResult(ArrayList<String> conditionalFragment) {
        Logic logicCalculations = new Logic();
        boolean logicalResult = logicCalculations.calculateLogic(conditionalFragment);
        return logicalResult;
    }

    /**
     * In charge of cond operation
     * @param line line to be quoted
     * @return quoted elements
     */
    private String quoteResult(ArrayList<String> line) {
        //(quote (+ 1 2))
        String result = "";
        line.remove(0); // (
        line.remove(0); // quote
        line.remove(line.size() - 1);
        for(int i = 0; i < line.size(); i ++) {

            result += line.get(i) + " ";
        }
        return result;
    }

    /**
     * Accesses a list element that is saved on a variable
     * @param line line with the list
     * @return String with the element
     */
    private String accessListElement(ArrayList<String> line) {
        String element = "";
        String variableName = line.get(2);
        String indexToAccess = line.get(1);
        int index = 0;
        switch (indexToAccess) {
            case "first":
                break;
            case "second":
                index = 1;
                break;
            case "third":
                index = 2;
                break;
            case "fourth":
                index = 3;
                break;
            case "fifth":
                index = 4;
                break;
            case "sixth":
                index = 5;
                break;
            case "seventh":
                index = 6;
                break;
            case "eighth":
                index = 7;
                break;
            case "ninth":
                index = 8;
                break;
            case "tenth":
                index = 9;
                break;

        }
        try {
            element = LispMemory.variableMemory.get(variableName).get(index);
        } catch (Exception E) {
            System.err.println("Index out of bounds");
        }
        return element;
    }

    /**
     * In charge of defining lists
     * @param line line to be made into a list
     * @return ArrayList containing the list
     */
    public ArrayList<String> list(ArrayList<String> line) {
        System.out.println("IN LIST");
        ArrayList<String> listResult = new ArrayList<>();
        line.remove(0); // Removes (
        line.remove(0); // Removes "list"

        for(int i = 0; i < line.size(); i++) {
            char currentCharacter = line.get(i).charAt(0);
            if(currentCharacter == '\'') {
                line.set(i, line.get(i).replace("'",""));

                listResult.add(line.get(i));
            } else if (line.get(i).equals(")")) {
                break;
            } else {
                listResult.add(line.get(i));
            }
        }
        return listResult;
    }
}
