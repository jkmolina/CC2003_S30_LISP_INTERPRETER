import java.util.ArrayList;
/**
 * <h1>Logic</h1>
 * Class that operates >, <, and equal
 * <p>
 *
 * @author Joonho Kim (jkmolina) Alejandro Alvarez (Alejandroav93) Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-03-20
 **/
public class Logic {
    /**
     * Calculates the logic value of a lisp expression
     * @param line line to be calculated
     * @return boolean with truth value
     */
    public boolean calculateLogic(ArrayList<String> line) {
        boolean result = false;
        String typeOfOperation = line.get(1);

        switch (typeOfOperation) {

            case ">":
                double nextNumber;
                for(int i = 2; i < line.size() - 1; i ++) {
                    double number = Double.parseDouble(line.get(i));
                    if(i < line.size() - 2) {
                        nextNumber = Double.parseDouble(line.get(i + 1));
                    } else {
                        nextNumber = number - 10;
                    }
                    if(number > nextNumber) {
                        continue;
                    } else {
                        return false;
                    }
                }
                return true;

            case "<":
                for(int i = 2; i < line.size() - 1; i ++) {
                    double number = Double.parseDouble(line.get(i));
                    if(i < line.size() - 2) {
                        nextNumber = Double.parseDouble(line.get(i + 1));
                    } else {
                        nextNumber = number + 10;
                    }
                    if(number < nextNumber) {
                        continue;
                    } else {
                        return false;
                    }
                }
                return true;

            case "equal":

                if(LispMemory.variableMemory.containsKey(line.get(2)) &&
                        LispMemory.variableMemory.containsKey(line.get(3))) {
                    if(LispMemory.variableMemory.get(line.get(2)).equals(LispMemory.variableMemory.get(line.get(3)))) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (LispMemory.variableMemory.containsKey((line.get(2)))) {
                    String comparison = "\"" + LispMemory.variableMemory.get(line.get(2)).get(1) +"\""; // Extend this idea to the others
                    if(LispMemory.variableMemory.get(line.get(2)).get(1).equals(line.get(3)) || comparison.equals(line.get(3))) {
                        return true;
                    } else {
                        return false;
                    }
                } else if  (LispMemory.variableMemory.containsKey((line.get(3)))) {
                    if(LispMemory.variableMemory.get(line.get(3)).equals(line.get(2))) {
                        return true;
                    } else {
                        return false;
                    }
                }
                if(line.get(2).equals(line.get(3))) {
                    return true;
                } else {
                    return false;
                }
        }
        return true;
    }
}
