package ast.types;

import ast.statements.Statement;
import java.util.List;

public class FunctionType implements Type {

    private List<Statement> parameters;
    private Type returnType;

    public FunctionType(List<Statement> parameters, Type returnType) {
        this.parameters = parameters;
        this.returnType = returnType;
    }

    public List<Statement> getParameters() {
        return parameters;
    }

    public Type getReturnType() {
        return returnType;
    }
}
