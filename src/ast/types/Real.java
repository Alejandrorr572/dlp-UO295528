package ast.types;

public class Real implements Type {

    private static Real instance;

    private Real() {}

    public static Real getInstance(){
        if(instance == null)
            instance = new Real();
        return instance;
    }
}
