package ast.expressions;

import ast.AbstractLocatable;

public class FieldAccess extends AbstractLocatable implements Expression {

    private Expression left;
    private String field;

    public FieldAccess(Expression left, String field, int line, int column) {
        super(line, column);
        this.left = left;
        this.field = field;
    }

    public Expression getName() {
        return left;
    }

    public String getField() {
        return field;
    }
}
