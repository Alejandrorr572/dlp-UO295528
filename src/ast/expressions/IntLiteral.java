package ast.expressions;

import ast.AbstractLocatable;

public class IntLiteral extends AbstractLocatable implements Expression {

    private int value;

    public IntLiteral(int value, int line, int column) {
        super(line, column);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
