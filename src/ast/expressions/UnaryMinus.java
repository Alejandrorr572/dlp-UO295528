package ast.expressions;

import visitors.Visitor;

public class UnaryMinus extends AbstractExpression {

    private Expression right;

    public UnaryMinus(Expression right, int line, int column) {
        super(line, column);
        this.right = right;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "UnaryMinus [line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
