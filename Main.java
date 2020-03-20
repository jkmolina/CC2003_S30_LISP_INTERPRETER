import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
        a.add("Test");
        LispStack lispStack = new LispStack();
        ArrayList<ArrayList<String>> stack = lispStack.getStack("pruebas.lisp");
        Interpreter interpreter = new Interpreter();
        interpreter.calculate(stack);

        ArrayList<String> test = new ArrayList<>();
        test.add("(");
        test.add("+");
        test.add("5");
        test.add("10");
        test.add(")");
        Arithmetic arithmetic = new Arithmetic();
        arithmetic.calculateParenthesis(0, test.size(), test);
        System.out.println(test);

        System.out.println(a.remove(0));
        System.out.println(a);

    }
}
