package ast.types;

import visitors.Visitor;

public class ArrayType implements Type {

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

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }
}
