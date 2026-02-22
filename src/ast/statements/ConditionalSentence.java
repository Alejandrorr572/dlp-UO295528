package ast.statements;

import ast.AbstractLocatable;
import ast.expressions.Expression;

public abstract class ConditionalSentence extends AbstractLocatable implements Statement {

    private Expression condition;
    private Statement body;

    public ConditionalSentence(Expression condition, Statement body, int line, int column) {
        super(line, column);
        this.condition = condition;
        this.body = body;
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getBody() {
        return body;
    }
}
