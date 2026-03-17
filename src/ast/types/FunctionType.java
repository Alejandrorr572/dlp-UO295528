package ast.types;

import ast.definitions.VarDefinition;
import ast.statements.Statement;
import visitors.Visitor;

import java.util.List;

public class FunctionType implements Type {

    private List<VarDefinition> parameters;
    private Type returnType;

    public FunctionType(List<VarDefinition> parameters, Type returnType) {
        this.parameters = parameters;
        this.returnType = returnType;
    }

    public List<VarDefinition> getParameters() {
        return parameters;
    }

    public Type getReturnType() {
        return returnType;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }
}
