package ast;

import ast.expressions.Expression;
import ast.expressions.Variable;
import ast.statements.Statement;
import ast.types.Type;
import visitors.Visitor;

import java.util.List;

public class FunctionCall extends AbstractLocatable implements Statement, Expression {

    private Variable name;
    private List<Expression> expressions;
    private boolean lvalue;
    Type type;

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
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT param) {
        return (RT) v.visit(this, param);
    }

    @Override
    public String toString() {
        return "FunctionCall [name=" + (name != null ? name.getName() : "null") +
                ", args=" + expressions.size() +
                ", line=" + getLine() + ", column=" + getColumn() + "]";
    }
}