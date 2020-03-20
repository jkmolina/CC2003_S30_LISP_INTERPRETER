import java.util.ArrayList;

public class Structures {

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

    private ArrayList<String> conditional(ArrayList<String> line) {
        ArrayList<String> result = new ArrayList<>();

        int endOfConditional = 0;
        //(cond((equal n 1) 3) (2))
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
        System.out.println("THE CONDIIITOONAL FRAGMENTTTTTTTTTT");
        System.out.println(conditionalFragment);
        boolean booleanResult = conditionalResult(conditionalFragment);
        System.out.println(line);
        System.out.println(clonedLine);

        if(booleanResult) {
            for(int i = endOfConditional; i < clonedLine.size(); i++) {
                int openParentheses = 0;
                int closedParentheses = 0;
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
            clonedLine.remove(clonedLine.size() - 1);
            for(int i = clonedLine.size() - 1; i > 0; i--) {
                if(clonedLine.get(i).equals("(")) {
                    openingParenthesis = i;
                    break;
                }
            }
            for(int i = openingParenthesis + 1; i < clonedLine.size() - 1; i++) {
                result.add(clonedLine.get(i));
            }

        }

        return result;
    }

    private boolean conditionalResult(ArrayList<String> conditionalFragment) {
        Logic logicCalculations = new Logic();
        boolean logicalResult = logicCalculations.calculateLogic(conditionalFragment);
        return logicalResult;
    }

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
