package ast.types;

import ast.Locatable;
import errorhandler.ErrorHandler;
import visitors.Visitor;

public class Char extends AbstractType {

    private static Char instance;

    private Char() {}

    public static Char getInstance(){
        if(instance == null)
            instance = new Char();
        return instance;
    }

    @Override
    public void mustBeBuiltIn(Locatable locatable) {
    }

    @Override
    public void mustBePromotedTo(Type other, Locatable locatable) {
        if (other instanceof Char || other instanceof Int) {
            return;
        }
        if (other instanceof ErrorType) {
            return;
        }
        ErrorHandler.getInstance().addError(new ErrorType(
                "Cannot promote Char to " + other.getClass().getSimpleName(), locatable));
    }

    @Override
    public Type arithmetic(Type other, Locatable locatable) {
        if (other instanceof Char || other instanceof Int) {
            return Int.getInstance();
        }
        if (other instanceof ErrorType) {
            return other;
        }
        return super.arithmetic(other, locatable);
    }

    @Override
    public Type comparison(Type other, Locatable locatable) {
        if (other instanceof Char) {
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

    public int numberOfBytes(){
        return 1;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }
}
