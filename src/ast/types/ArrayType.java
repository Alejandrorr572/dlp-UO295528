package ast.types;

import ast.Locatable;
import errorhandler.ErrorHandler;
import visitors.Visitor;

public class ArrayType extends AbstractType {

    private int size;
    private Type elementsType;

    public ArrayType(int size, Type elementsType) {
        this.size = size;
        this.elementsType = elementsType;
    }

    public int getSize() {
        return size;
    }

    public Type getElementsType() {
        return elementsType;
    }

    public int numberOfBytes(){
        return elementsType.numberOfBytes()*size;
    }
    @Override
    public Type squareBrackets(Type other, Locatable locatable) {
        if (other instanceof Int) {
            return elementsType;
        }

        if (other instanceof ErrorType) {
            return elementsType;
        }

        ErrorType error = new ErrorType(
                "Array index should be an int, but was " + other.getClass().getSimpleName(), locatable);
        ErrorHandler.getInstance().addError(error);
        return error;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }
}
