package ast.sentences;

import ast.expressions.Expression;

public class While extends ConditionalSentence {

    public While(Expression condition, Sentence body, int line, int column) {
        super(condition, body, line, column);
    }
}
