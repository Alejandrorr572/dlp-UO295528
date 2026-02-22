package ast.statements;

import ast.AbstractLocatable;
import ast.expressions.Expression;
import java.util.List;

public class Read extends AbstractLocatable implements Statement {

    private List<Expression> expressions;

    public Read(List<Expression> expressions, int line, int column) {
        super(line, column);
        this.expressions = expressions;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }
}
