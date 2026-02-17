package ast.expressions;

import ast.AbstractLocatable;

public class ArrayAccess extends AbstractLocatable implements Expression {

    private Expression access;
    private Expression value;

    public ArrayAccess(Expression access, Expression value, int line, int column) {
        super(line, column);
        this.access = access;
        this.value = value;
    }

    public Expression getAccess() {
        return access;
    }

    public Expression getValue() {
        return value;
    }
}
