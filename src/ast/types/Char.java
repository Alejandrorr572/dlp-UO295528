package ast.types;

import visitors.Visitor;

public class Char implements Type {

    private static Char instance;

    private Char() {}

    public static Char getInstance(){
        if(instance == null)
            instance = new Char();
        return instance;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }
}
