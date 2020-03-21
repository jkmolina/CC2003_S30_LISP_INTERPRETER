import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
/**
 * <h1>LispStack</h1>
 * Class that generates the stack to be used
 * <p>
 *
 * @author Joonho Kim (jkmolina) Alejandro Alvarez (Alejandroav93) Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-03-20
 **/
public class LispStack implements  StackInterface{

    /**
     * Gets a stack that can be read by lisp interpreter from a lisp file
     * @param line line to be
     * @return
     */
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

        return stack;
    }
}
