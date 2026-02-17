package ast.definitions;

import ast.types.FunctionType;
import ast.types.Type;
import ast.sentences.Sentence;
import java.util.List;

public class FunctionDefinition implements Definition {

    private FunctionType type;
    private List<Sentence> body;

    public FunctionDefinition(FunctionType type, List<Sentence> body) {
        this.type = type;
        this.body = body;
    }

    @Override
    public Type getType() {
        return type;
    }

    public List<Sentence> getBody() {
        return body;
    }
}
