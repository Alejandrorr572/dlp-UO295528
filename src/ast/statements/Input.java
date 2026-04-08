package ast.statements;

import ast.AbstractLocatable;
import ast.expressions.Expression;
import visitors.Visitor;

import java.util.List;

public class Input extends AbstractLocatable implements Statement {

    private List<Expression> expressions;

    public Input(List<Expression> expressions, int line, int column) {
        super(line, column);
        this.expressions = expressions;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "Input [numExpressions=" + expressions.size() +
                ", line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
