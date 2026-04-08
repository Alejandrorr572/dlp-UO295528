package ast.types;

import ast.Locatable;
import errorhandler.ErrorHandler;
import visitors.Visitor;

public class VoidType extends AbstractType {

    private static VoidType instance;

    private VoidType() {}

    public static VoidType getInstance(){
        if(instance == null)
            instance = new VoidType();
        return instance;
    }

    @Override
    public void mustBePromotedTo(Type other, Locatable locatable) {
        if (other instanceof VoidType) {
            return;
        }
        if (other instanceof ErrorType) {
            return;
        }
        ErrorHandler.getInstance().addError(new ErrorType(
                "Void cannot be promoted to " + other.getClass().getSimpleName(), locatable));
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }
}
