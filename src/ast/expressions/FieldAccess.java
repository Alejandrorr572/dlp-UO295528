package ast.expressions;

import ast.AbstractLocatable;

public class FieldAccess extends AbstractLocatable implements Expression {

    private Expression name;
    private String field;

    public FieldAccess(Expression name, String field, int line, int column) {
        super(line, column);
        this.name = name;
        this.field = field;
    }

    public Expression getName() {
        return name;
    }

    public String getField() {
        return field;
    }
}
