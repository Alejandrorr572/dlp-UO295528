package ast;

import ast.expressions.Expression;
import ast.expressions.Variable;
import ast.statements.Statement;
import visitors.Visitor;

import java.util.List;

public class FunctionCall extends AbstractLocatable implements Statement, Expression {

    private Variable name;
    private List<Expression> expressions;
    private boolean lvalue;

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

    @Override
    public void setLvalue(boolean value) {
        this.lvalue = lvalue;
    }

    @Override
    public boolean getLvalue() {
        return lvalue;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT param) {
        return (RT) v.visit(this, param);
    }
}
