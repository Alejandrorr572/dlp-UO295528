package ast.statements;

import ast.AbstractLocatable;
import ast.expressions.Expression;
import visitors.Visitor;

import java.util.List;

public abstract class ConditionalSentence extends AbstractLocatable implements Statement {

    private Expression condition;
    private List<Statement> body;

    public ConditionalSentence(Expression condition, List<Statement> body, int line, int column) {
        super(line, column);
        this.condition = condition;
        this.body = body;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getBody() {
        return body;
    }
}
