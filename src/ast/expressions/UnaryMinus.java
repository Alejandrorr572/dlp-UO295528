package ast.expressions;

import ast.AbstractLocatable;

public class UnaryMinus extends AbstractLocatable implements Expression {

    private Expression right;

    public UnaryMinus(Expression right, int line, int column) {
        super(line, column);
        this.right = right;
    }

    public Expression getRight() {
        return right;
    }
}
