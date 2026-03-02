package ast;

import ast.expressions.Expression;
import ast.expressions.Variable;
import ast.statements.Statement;

import java.util.List;

public class FunctionCall extends AbstractLocatable implements Statement, Expression {

    private Variable name;
    private List<Expression> expressions;

    public FunctionCall(Variable name, List<Expression> expressions, int line, int column) {
        super(line, column);
        this.name = name;
        this.expressions = expressions;
    }

    public Variable getName() {
        return name;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }
}
