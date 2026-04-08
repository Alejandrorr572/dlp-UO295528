package ast.expressions;

import ast.definitions.Definition;
import visitors.Visitor;

public class Variable extends AbstractExpression {

    private String name;
    private Definition definition;

    public Variable(String name, int line, int column) {
        super(line, column);
        this.name = name;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }

    public Definition getDefinition(){
        return definition;
    }
    public void setDefinition(Definition definition) {this.definition = definition;}

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Variable [name=" + name + ", line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
