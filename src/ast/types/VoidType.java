package ast.types;

public class VoidType implements Type {

    private static VoidType instance;

    private VoidType() {}

    public static VoidType getInstance(){
        if(instance == null)
            instance = new VoidType();
        return instance;
    }
}
