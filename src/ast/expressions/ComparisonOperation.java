package ast.expressions;

import visitors.Visitor;

public class ComparisonOperation extends AbstractExpression {

    private Expression left;
    private String operator;
    private Expression right;

    public ComparisonOperation(Expression left, String operator, Expression right, int line, int column) {
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

    @Override
    public String toString() {
        return "ComparisonOperation [operator=" + operator + ", line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
