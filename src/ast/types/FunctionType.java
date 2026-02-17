package ast.types;

import ast.sentences.Sentence;
import java.util.List;

public class FunctionType implements Type {

    private List<Sentence> parameters;
    private Type returnType;

    public FunctionType(List<Sentence> parameters, Type returnType) {
        this.parameters = parameters;
        this.returnType = returnType;
    }

    public List<Sentence> getParameters() {
        return parameters;
    }

    public Type getReturnType() {
        return returnType;
    }
}
