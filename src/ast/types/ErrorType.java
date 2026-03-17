package ast.types;

import ast.Locatable;
import visitors.Visitor;

public class ErrorType implements Type {

    String cause;
    Locatable locatable;

    public ErrorType(String cause, Locatable locatable) {
        this.cause = cause;
        this.locatable = locatable;
    }

    public String toString(){
        return cause;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }
}
