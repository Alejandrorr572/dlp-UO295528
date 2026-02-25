package ast.types;

public class Int implements Type {

    private static Int instance;

    private Int() {}

    public static Int getInstance(){
        if(instance == null)
            instance = new Int();
        return instance;
    }
}
