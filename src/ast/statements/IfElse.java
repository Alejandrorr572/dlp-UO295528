package ast.statements;

import ast.expressions.Expression;

import java.util.List;

public class IfElse extends ConditionalSentence {

    private List<Statement> elseBody;

    public IfElse(Expression condition, List<Statement> body, int line, int column) {
        super(condition, body, line, column);
    }

    public List<Statement> getElseBody() {
        return elseBody;
    }

    public void addElseBody(List<Statement> elseBody){
        this.elseBody = elseBody;
    }
}
