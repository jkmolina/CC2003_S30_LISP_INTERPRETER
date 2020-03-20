import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class LispStack implements  StackInterface{


    ArrayList<String> stack = new ArrayList<>();

    public ArrayList<ArrayList<String>> getStack(String document) {
        ArrayList<ArrayList<String>> stack = new ArrayList<>();

        // Reads the document
        try {
            BufferedReader reader = new BufferedReader(new FileReader(document));
            String line;
            int openParentheses = 0;
            int closedParantheses = 0;
            String elementToPush = "";
            ArrayList<String> lineToCalculate = new ArrayList<>();

            // Generates the stack
            while((line = reader.readLine()) != null) {

                boolean foundComment = false;

                // Runs through every character in the line
                for(int i = 0; i < line.length(); i ++) {
                    char currentCharacter = line.charAt(i);
                    char nextCharacter = ' ';
                    if(i < line.length() - 1){
                        nextCharacter = line.charAt(i+1);
                    } else if (i == line.length() - 1) {
                        nextCharacter = ' ';
                    }
                    // Takes action according to the character

                    if(currentCharacter == ';') {
                        foundComment = true;
                    }

                    if(!foundComment) {
                        switch (currentCharacter){
                            // Characters that define division or actions
                            case '(': case '<': case ' ': case '>': case '+': case '-': case '*': case ')': case '"':
                            case '/':
                                if(currentCharacter == '(') {
                                    openParentheses ++;
                                } else if(currentCharacter == ')') {
                                    closedParantheses ++;
                                }

                                if(currentCharacter == '<' || currentCharacter == '>' || currentCharacter == '=') {
                                    if(nextCharacter == '=') {
                                        System.out.println("INNN");
                                        elementToPush += currentCharacter;
                                        break;
                                    }
                                } else if(currentCharacter == '-') {
                                    if(nextCharacter != ' ') {
                                        elementToPush += currentCharacter;
                                        break;
                                    }
                                }

                                if(currentCharacter == '=') {
                                    if(nextCharacter =='=') {
                                        elementToPush += currentCharacter;
                                    }
                                }

                                if(elementToPush.length() > 0 && elementToPush.charAt(0) != '"' && currentCharacter != '"') {
                                    lineToCalculate.add(elementToPush);
                                    elementToPush = "";
                                }

                                if(currentCharacter != ' ' && currentCharacter != '"' && elementToPush.length() == 0){
                                    elementToPush += currentCharacter;
                                    lineToCalculate.add(elementToPush);
                                    elementToPush = "";
                                }

                                if(currentCharacter == '"'){
                                    if(elementToPush.length() > 0) {
                                        elementToPush += currentCharacter;
                                        lineToCalculate.add(elementToPush);
                                        elementToPush = "";
                                    } else {
                                        elementToPush += currentCharacter;
                                    }
                                }

                                if(elementToPush.length() > 0 && elementToPush.charAt(0) == '"') {
                                    if(elementToPush.length() > 1) {
                                        elementToPush += currentCharacter;
                                    }
                                }
                                break;

                            default:    // Gets any character that doesn't define an operation
                                elementToPush += currentCharacter;
                                if(i == line.length() - 1) {
                                    lineToCalculate.add(elementToPush);
                                }
                                break;
                        }
                        if(openParentheses == closedParantheses && lineToCalculate.size() > 0) {
                            ArrayList<String> lineToAdd = new ArrayList<>(lineToCalculate);
                            stack.add(lineToAdd);
                            lineToCalculate.clear();
                            openParentheses = 0;
                            closedParantheses = 0;
                        }

                    }

                }

            }
        } catch (Exception E) {
            System.err.println("There was an error while converting the file to a stack");
        }

        System.out.println("MY STACK IS");
        System.out.println(stack);
        return stack;
    }

    public String pop() {
        return stack.remove(0);
    }

    public int size() {
        return stack.size();
    }

    public String peek(int index) {
        return stack.get(index);
    }

    public void add(String element) {
        stack.add(element);
    }

}
