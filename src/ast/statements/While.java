package ast.statements;

import ast.expressions.Expression;
import visitors.Visitor;

import java.util.List;

public class While extends ConditionalSentence {

    public While(Expression condition, List<Statement> body, int line, int column) {
        super(condition, body, line, column);
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "While [bodySize=" + getBody().size() +
                ", line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
