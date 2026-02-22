package ast.statements;

import ast.AbstractLocatable;
import ast.expressions.Expression;

public class Log extends AbstractLocatable implements Statement {

    private Expression expression;

    public Log(Expression expression, int line, int column) {
        super(line, column);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
