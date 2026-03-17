package ast.expressions;

import ast.AbstractLocatable;
import visitors.Visitor;

public class Variable extends AbstractExpression {

    private String name;

    public Variable(String name, int line, int column) {
        super(line, column);
        this.name = name;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }

    public String getName() {
        return name;
    }
}
