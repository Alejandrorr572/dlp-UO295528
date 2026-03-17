package ast.expressions;

import ast.AbstractLocatable;
import visitors.Visitor;

public class FieldAccess extends AbstractExpression {

    private Expression left;
    private String field;

    public FieldAccess(Expression left, String field, int line, int column) {
        super(line, column);
        this.left = left;
        this.field = field;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }

    public Expression getName() {
        return left;
    }

    public String getField() {
        return field;
    }
}
