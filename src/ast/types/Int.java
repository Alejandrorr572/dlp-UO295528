package ast.types;

import visitors.Visitor;

public class Int implements Type {

    private static Int instance;

    private Int() {}

    public static Int getInstance(){
        if(instance == null)
            instance = new Int();
        return instance;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }
}
