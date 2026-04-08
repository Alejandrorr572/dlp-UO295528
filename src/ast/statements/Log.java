package ast.statements;

import ast.AbstractLocatable;
import ast.expressions.Expression;
import visitors.Visitor;

public class Log extends AbstractLocatable implements Statement {

    private Expression expression;

    public Log(Expression expression, int line, int column) {
        super(line, column);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "Log [line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
