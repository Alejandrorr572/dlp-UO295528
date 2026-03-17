package ast.expressions;

import ast.AbstractLocatable;
import visitors.Visitor;

public class ArithmeticOperation extends AbstractExpression {

    private Expression left;
    private String operator;
    private Expression right;

    public ArithmeticOperation(Expression left, String operator, Expression right, int line, int column) {
        super(line, column);
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
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
