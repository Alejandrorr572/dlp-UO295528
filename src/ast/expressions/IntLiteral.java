package ast.expressions;

import ast.AbstractLocatable;
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
}
