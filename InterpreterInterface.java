import java.util.ArrayList;
/**
 * <h1>InterpreterInterface</h1>
 * Interface used to interpret the Lisp language
 * <p>
 *
 * @author Joonho Kim (jkmolina) Alejandro Alvarez (Alejandroav93) Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-03-20
 **/
public interface InterpreterInterface {
    ArrayList<String> calculate(ArrayList<ArrayList<String>> stack);
}
