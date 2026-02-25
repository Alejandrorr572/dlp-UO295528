package ast;

import ast.expressions.Expression;
import ast.statements.Statement;

import java.util.List;

public class FunctionCall extends AbstractLocatable implements Statement, Expression {

    private String name;
    private List<Expression> expressions;

    public FunctionCall(String name, List<Expression> expressions, int line, int column) {
        super(line, column);
        this.name = name;
        this.expressions = expressions;
    }

    public String getName() {
        return name;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }
}
