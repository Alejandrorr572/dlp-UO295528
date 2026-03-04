package ast.types;

import ast.Locatable;

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
}
