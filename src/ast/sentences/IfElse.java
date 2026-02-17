package ast.sentences;

import ast.expressions.Expression;

public class IfElse extends ConditionalSentence {

    private Sentence elseBody;

    public IfElse(Expression condition, Sentence body, Sentence elseBody, int line, int column) {
        super(condition, body, line, column);
        this.elseBody = elseBody;
    }

    public Sentence getElseBody() {
        return elseBody;
    }
}
