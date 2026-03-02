package ast.definitions;

import ast.Locatable;
import ast.types.Type;

public interface Definition extends Locatable {
    String getName();
    Type getType();
}
