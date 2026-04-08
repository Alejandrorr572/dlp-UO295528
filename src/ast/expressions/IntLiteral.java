package ast.expressions;

import visitors.Visitor;

public class IntLiteral extends AbstractExpression {

    private int value;

    public IntLiteral(int value, int line, int column) {
        super(line, column);
        this.value = value;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "IntLiteral [value=" + value + ", line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
