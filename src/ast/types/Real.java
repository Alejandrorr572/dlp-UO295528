package ast.types;

import visitors.Visitor;

public class Real implements Type {

    private static Real instance;

    private Real() {}

    public static Real getInstance(){
        if(instance == null)
            instance = new Real();
        return instance;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }
}
