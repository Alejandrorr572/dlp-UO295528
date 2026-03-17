package ast.expressions;

import ast.AbstractLocatable;
import visitors.Visitor;

public class ArrayAccess extends AbstractExpression {

    private Expression access;
    private Expression value;

    public ArrayAccess(Expression access, Expression value, int line, int column) {
        super(line, column);
        this.access = access;
        this.value = value;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }

    public Expression getAccess() {
        return access;
    }

    public Expression getValue() {
        return value;
    }
}
