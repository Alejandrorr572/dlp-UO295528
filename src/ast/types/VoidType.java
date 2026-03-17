package ast.types;

import visitors.Visitor;

public class VoidType implements Type {

    private static VoidType instance;

    private VoidType() {}

    public static VoidType getInstance(){
        if(instance == null)
            instance = new VoidType();
        return instance;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }
}
