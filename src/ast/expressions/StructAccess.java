package ast.expressions;

import ast.AbstractLocatable;

public class StructAccess extends AbstractLocatable implements Expression {

    private String name;
    private Expression value;

    public StructAccess(String name, Expression value, int line, int column) {
        super(line, column);
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Expression getValue() {
        return value;
    }
}
