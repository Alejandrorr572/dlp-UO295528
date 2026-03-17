package ast;

import visitors.Visitor;

public interface ASTNode {
    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param);
}
