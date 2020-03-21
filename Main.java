import java.util.ArrayList;
import java.util.Scanner;
/**
 * <h1>Main</h1>
 * Main class to run the Lisp interpreter
 * <p>
 *
 * @author Joonho Kim (jkmolina) Alejandro Alvarez (Alejandroav93) Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-03-20
 **/
public class Main {
    public static void main(String[] args) {
        // Input
        Scanner input = new Scanner(System.in);

        // Gets the stack to be used according to the document
        LispStack lispStack = new LispStack();
        ArrayList<ArrayList<String>> stack = lispStack.getStack("pruebas.lisp");

        // Interpreter calculates the stack
        Interpreter interpreter = new Interpreter();
        ArrayList<String> results = interpreter.calculate(stack);

        // Results can also be shown by line
        System.out.println("Presione enter para mostrar cada resultado en una linea");
        input.nextLine();
        for(int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i));
        }

        // One the program is finished
        System.out.println("Presione enter para salir");
        input.nextLine();
    }
}
