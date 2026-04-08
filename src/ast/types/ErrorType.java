package ast.types;

import ast.Locatable;
import errorhandler.ErrorHandler;
import visitors.Visitor;

import java.util.List;

public class ErrorType extends AbstractType {

    String cause;
    Locatable locatable;

    public ErrorType(String cause, Locatable locatable) {
        this.cause = cause;
        this.locatable = locatable;
    }

    public String toString(){
        return "Error: "+ cause + "in " + locatable;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }

    @Override
    public void mustBeLogic(Locatable locatable) {

    }

    @Override
    public void mustBePromotedTo(Type other, Locatable locatable) {

    }

    @Override
    public void mustBeBuiltIn(Locatable locatable) {

    }

    @Override
    public Type arithmetic(Type other, Locatable locatable) {
        return this;
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        return this;
    }

    @Override
    public Type squareBrackets(Type other, Locatable array) {
        return this;
    }

    @Override
    public Type dot(String field, Locatable locatable) {
        return this;
    }

    @Override
    public Type parenthesis(List<Type> types, Locatable locatable) {
        return this;
    }

    @Override
    public Type comparison(Type other, Locatable locatable) {
        return this;
    }

    @Override
    public Type logic(Type other, Locatable locatable) {
        return this;
    }

    @Override
    public Type logic(Locatable locatable) {
        return this;
    }

    @Override
    public Type cast(Type targetType, Locatable locatable) {
        return this;
    }
}
