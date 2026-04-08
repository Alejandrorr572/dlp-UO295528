package ast.definitions;

import ast.types.FunctionType;
import ast.statements.Statement;
import visitors.Visitor;

import java.util.List;

public class FunctionDefinition extends AbstractDefinition {

    private List<Statement> body;

    public FunctionDefinition(String name, FunctionType type, List<Statement> body, int line, int column) {
        super(line,column, name, type);
        this.body = body;
    }

    public List<Statement> getBody() {
        return body;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "FunctionDefinition [name=" + getName() + ", type=" + getType() +
                ", line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
