package ast.definitions;

import ast.AbstractLocatable;
import ast.types.FunctionType;
import ast.types.Type;
import ast.statements.Statement;
import java.util.List;

public class FunctionDefinition extends AbstractLocatable implements Definition {

    private FunctionType type;
    private List<Statement> body;

    public FunctionDefinition(FunctionType type, List<Statement> body, int line, int column) {
        super(line,column);
        this.type = type;
        this.body = body;
    }

    @Override
    public Type getType() {
        return type;
    }

    public List<Statement> getBody() {
        return body;
    }
}
