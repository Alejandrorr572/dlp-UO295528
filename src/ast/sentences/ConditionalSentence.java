package ast.sentences;

import ast.AbstractLocatable;
import ast.expressions.Expression;

public abstract class ConditionalSentence extends AbstractLocatable implements Sentence {

    private Expression condition;
    private Sentence body;

    public ConditionalSentence(Expression condition, Sentence body, int line, int column) {
        super(line, column);
        this.condition = condition;
        this.body = body;
    }

    public Expression getCondition() {
        return condition;
    }

    public Sentence getBody() {
        return body;
    }
}
