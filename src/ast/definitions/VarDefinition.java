package ast.definitions;

import ast.AbstractLocatable;
import ast.statements.Statement;
import ast.types.Type;
import java.util.List;

public class VarDefinition extends AbstractDefinition implements Statement {

    public VarDefinition(Type type, String identifier, int line, int column) {
        super(line,column,identifier,type);
    }
}
