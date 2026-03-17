package errorhandler;

import ast.types.ErrorType;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {

    private static ErrorHandler instance = null;
    private List<ErrorType> errors = new ArrayList<>();

    private ErrorHandler(){}

    public static ErrorHandler getInstance(){
        if(instance == null) {
            instance = new ErrorHandler();
            return instance;
        }
        return instance;
    }

    public void addError(ErrorType error){
        errors.add(error);
    }

    public boolean anyError(){
        return !errors.isEmpty();
    }

    public void showErrors(PrintStream err){

        for(ErrorType error: errors){
           err.println(error);
        }
    }

}
