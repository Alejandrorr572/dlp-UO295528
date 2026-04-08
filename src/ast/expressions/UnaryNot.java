package ast.expressions;

import visitors.Visitor;

public class UnaryNot extends AbstractExpression {

    private Expression right;

    public UnaryNot(Expression right, int line, int column) {
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
        return "UnaryNot [line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
