package ast.expressions;

import ast.AbstractLocatable;

public class ArithmeticOperation extends AbstractLocatable implements Expression {

    private Expression left;
    private String operator;
    private Expression right;

    public ArithmeticOperation(Expression left, String operator, Expression right, int line, int column) {
        super(line, column);
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public String getOperator() {
        return operator;
    }

    public Expression getRight() {
        return right;
    }
}
