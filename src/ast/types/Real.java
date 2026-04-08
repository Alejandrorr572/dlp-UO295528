package ast.types;

import ast.Locatable;
import errorhandler.ErrorHandler;
import visitors.Visitor;

public class Real extends AbstractType {

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

    @Override
    public void mustBeBuiltIn(Locatable locatable) {
    }

    @Override
    public void mustBePromotedTo(Type other, Locatable locatable) {
        if (other instanceof Real) {
            return;
        }
        if (other instanceof ErrorType) {
            return;
        }
        ErrorHandler.getInstance().addError(new ErrorType(
                "Cannot promote Real to " + other.getClass().getSimpleName(), locatable));
    }

    @Override
    public Type arithmetic(Type other, Locatable locatable) {
        if (other instanceof Real || other instanceof Int) {
            return this;
        }
        if (other instanceof ErrorType) {
            return other;
        }
        return super.arithmetic(other, locatable);
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        return this;
    }

    @Override
    public Type comparison(Type other, Locatable locatable) {
        if (other instanceof Real || other instanceof Int) {
            return Int.getInstance();
        }
        if (other instanceof ErrorType) {
            return other;
        }
        return super.comparison(other, locatable);
    }

    @Override
    public Type cast(Type targetType, Locatable locatable) {
        if (targetType instanceof Int || targetType instanceof Real || targetType instanceof Char) {
            return targetType;
        }
        if (targetType instanceof ErrorType) {
            return targetType;
        }
        return super.cast(targetType, locatable);
    }
}
