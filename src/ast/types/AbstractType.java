package ast.types;

import ast.Locatable;
import ast.expressions.Expression;
import errorhandler.ErrorHandler;
import visitors.Visitor;

import java.util.List;

public abstract class AbstractType implements Type {

    @Override
    public int numberOfBytes(){
        return 0;
    }

    @Override
    public void mustBeLogic(Locatable locatable) {
        ErrorHandler.getInstance().addError(new ErrorType("Type must be logic", locatable));
    }

    @Override
    public void mustBePromotedTo(Type other, Locatable locatable) {
        ErrorHandler.getInstance().addError(new ErrorType("Type must be promotable", locatable));
    }

    @Override
    public void mustBeBuiltIn(Locatable locatable) {
        ErrorHandler.getInstance().addError(new ErrorType("Type must be built in", locatable));
    }

    @Override
    public Type arithmetic(Type other, Locatable locatable) {
        ErrorType error = new ErrorType(
                "Type " +this.getClass().getSimpleName()+ " can't support this operation", locatable);
        ErrorHandler.getInstance().addError(error);
        return error;
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        ErrorType error = new ErrorType(
                "Type " +this.getClass().getSimpleName()+ " can't support this operation", locatable);
        ErrorHandler.getInstance().addError(error);
        return error;
    }

    @Override
    public Type squareBrackets(Type other, Locatable locatable) {
        ErrorType error = new ErrorType(
                "Type " +this.getClass().getSimpleName()+ " can't support this operation", locatable);
        ErrorHandler.getInstance().addError(error);
        return error;
    }

    @Override
    public Type dot(String field, Locatable locatable) {
        ErrorType error = new ErrorType(
                "Type " +this.getClass().getSimpleName()+ " can't support this operation", locatable);
        ErrorHandler.getInstance().addError(error);
        return error;
    }

    @Override
    public Type parenthesis(List<Type> types, Locatable locatable) {
        ErrorType error = new ErrorType(
                "Type " +this.getClass().getSimpleName()+ " can't support this operation", locatable);
        ErrorHandler.getInstance().addError(error);
        return error;
    }

    @Override
    public Type comparison(Type other, Locatable locatable) {
        ErrorType error = new ErrorType(
                "Type " +this.getClass().getSimpleName()+ " can't support this operation", locatable);
        ErrorHandler.getInstance().addError(error);
        return error;
    }

    @Override
    public Type logic(Type other, Locatable locatable) {
        ErrorType error = new ErrorType(
                "Type " +this.getClass().getSimpleName()+ " can't support this operation", locatable);
        ErrorHandler.getInstance().addError(error);
        return error;
    }

    @Override
    public Type logic(Locatable locatable) {
        ErrorType error = new ErrorType(
                "Type " +this.getClass().getSimpleName()+ " can't support this operation", locatable);
        ErrorHandler.getInstance().addError(error);
        return error;
    }

    @Override
    public Type cast(Type targetType, Locatable locatable) {
        ErrorType error = new ErrorType(
                "Type " +this.getClass().getSimpleName()+ " can't support this operation", locatable);
        ErrorHandler.getInstance().addError(error);
        return error;
    }
}
