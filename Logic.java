import java.util.ArrayList;

public class Logic {

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
                    System.out.println("IN HEREEEEEEEEEEEEEEEEEEEEEEEEEE");
                    System.out.println(line.get(2));
                    String comparison = "\"" + LispMemory.variableMemory.get(line.get(2)).get(1) +"\""; // Extend this idea to the others
                    System.out.println(line.get(3));
                    if(LispMemory.variableMemory.get(line.get(2)).get(1).equals(line.get(3)) || comparison.equals(line.get(3))) {
                        System.out.println("IT IS TRUEEEEEEEEE YEAH");
                        return true;
                    } else {
                        System.out.println("FALSE");
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
