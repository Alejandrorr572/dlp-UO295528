package ast.types;

import ast.Locatable;
import errorhandler.ErrorHandler;
import visitors.Visitor;

public class Int extends AbstractType {

    private static Int instance;

    private Int() {}

    public static Int getInstance(){
        if(instance == null)
            instance = new Int();
        return instance;
    }

    @Override
    public void mustBeBuiltIn(Locatable locatable) {
    }

    @Override
    public void mustBeLogic(Locatable locatable) {
    }

    @Override
    public void mustBePromotedTo(Type other, Locatable locatable) {
        if (other instanceof Int || other instanceof Real) {
            return;
        }
        if (other instanceof ErrorType) {
            return;
        }
        ErrorHandler.getInstance().addError(new ErrorType(
                "Cannot promote Int to " + other.getClass().getSimpleName(), locatable));
    }

    @Override
    public Type arithmetic(Type other, Locatable locatable) {
        if (other instanceof Int) {
            return this;
        }
        if (other instanceof Real || other instanceof ErrorType) {
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
        if (other instanceof Int || other instanceof Real) {
            return this;
        }
        if (other instanceof ErrorType) {
            return other;
        }
        return super.comparison(other, locatable);
    }

    @Override
    public Type logic(Type other, Locatable locatable) {
        if (other instanceof Int) {
            return this;
        }
        if (other instanceof ErrorType) {
            return other;
        }
        return super.logic(other, locatable);
    }

    @Override
    public Type logic(Locatable locatable) {
        return this;
    }

    @Override
    public Type cast(Type other, Locatable locatable) {
        if (other instanceof Int || other instanceof Real || other instanceof Char) {
            return other;
        }
        if (other instanceof ErrorType) {
            return other;
        }
        return super.cast(other, locatable);
    }

    public int numberOfBytes(){
        return 2;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }
}
