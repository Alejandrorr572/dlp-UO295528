package ast.statements;

import ast.expressions.Expression;

public class While extends ConditionalSentence {

    public While(Expression condition, Statement body, int line, int column) {
        super(condition, body, line, column);
    }
}
