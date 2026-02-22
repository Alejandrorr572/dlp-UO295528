package ast.statements;

import ast.expressions.Expression;

public class IfElse extends ConditionalSentence {

    private Statement elseBody;

    public IfElse(Expression condition, Statement body, Statement elseBody, int line, int column) {
        super(condition, body, line, column);
        this.elseBody = elseBody;
    }

    public Statement getElseBody() {
        return elseBody;
    }
}
