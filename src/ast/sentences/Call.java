package ast.sentences;

import ast.AbstractLocatable;
import ast.expressions.Expression;
import java.util.List;

public class Call extends AbstractLocatable implements Sentence, Expression {

    private String name;
    private List<Expression> expressions;

    public Call(String name, List<Expression> expressions, int line, int column) {
        super(line, column);
        this.name = name;
        this.expressions = expressions;
    }

    public String getName() {
        return name;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }
}
