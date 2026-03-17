package ast.expressions;

import ast.AbstractLocatable;
import visitors.Visitor;

public class CharLiteral extends AbstractExpression {

    private char value;

    public CharLiteral(char value, int line, int column) {
        super(line, column);
        this.value = value;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }

    public char getValue() {
        return value;
    }
}
