package ast.definitions;

import ast.AbstractLocatable;
import ast.statements.Statement;
import ast.types.Type;
import java.util.List;

public class VarDefinition extends AbstractLocatable implements Definition, Statement {

    private Type type;
    private String identifier;

    public VarDefinition(Type type, String identifier, int line, int column) {
        super(line,column);
        this.type = type;
        this.identifier = identifier;
    }

    @Override
    public Type getType() {
        return type;
    }

    public String getIdentifiers() {
        return identifier;
    }
}
