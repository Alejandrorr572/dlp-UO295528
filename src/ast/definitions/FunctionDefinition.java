package ast.definitions;

import ast.types.FunctionType;
import ast.types.Type;
import ast.statements.Statement;
import java.util.List;

public class FunctionDefinition implements Definition {

    private FunctionType type;
    private List<Statement> body;

    public FunctionDefinition(FunctionType type, List<Statement> body) {
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
