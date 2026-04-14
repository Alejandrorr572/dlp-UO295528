package ast.types;

import ast.ASTNode;
import ast.Locatable;
import ast.expressions.ArrayAccess;
import ast.expressions.Expression;

import java.util.List;

public interface Type extends ASTNode {

    public int numberOfBytes();

    //Musts
    void mustBeLogic(Locatable locatable);
    void mustBePromotedTo(Type other, Locatable locatable);
    void mustBeBuiltIn(Locatable locatable);

    //Infieren
    Type arithmetic(Type other, Locatable locatable);
    Type arithmetic(Locatable locatable);
    Type squareBrackets(Type other, Locatable array);
    Type dot(String field, Locatable locatable );
    Type parenthesis(List<Type> types, Locatable locatable);
    Type comparison(Type other, Locatable locatable);
    Type logic(Type other, Locatable locatable);
    Type logic(Locatable locatable);
    Type cast(Type targetType, Locatable locatable);
}
