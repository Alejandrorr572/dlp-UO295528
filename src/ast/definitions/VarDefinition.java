package ast.definitions;

import ast.statements.Statement;
import ast.types.Type;
import visitors.Visitor;

public class VarDefinition extends AbstractDefinition implements Statement {

    public VarDefinition(Type type, String identifier, int line, int column) {
        super(line,column,identifier,type);
    }

    public VarDefinition(int line, int column, String identifier, Type type){
        super(line,column,identifier,type);
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "VarDefinition [name=" + getName() + ", type=" + getType() +
                ", scope=" + scope + ", line=" + getLine() + ", column=" + getColumn() + "]";
    }
}
