package ast.statements;

import ast.expressions.Expression;

import java.util.List;

public class While extends ConditionalSentence {

    public While(Expression condition, List<Statement> body, int line, int column) {
        super(condition, body, line, column);
    }
}
