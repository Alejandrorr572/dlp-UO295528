package visitors;

import ast.FunctionCall;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import examples.ast.Read;
import examples.ast.Write;

import java.util.ArrayList;
import java.util.List;

public class TypeCheckingVisitor extends AbstractVisitor<Type, Void> {

    @Override
    public Void visit(IntLiteral node, Type param) {
        node.setType(Int.getInstance());
        return null;
    }

    @Override
    public Void visit(CharLiteral node, Type param) {
        node.setType(Char.getInstance());
        return null;
    }

    @Override
    public Void visit(DoubleValue node, Type param) {
        node.setType(Real.getInstance());
        return null;
    }

    @Override
    public Void visit(Variable node, Type param) {
        node.setType(node.getDefinition().getType());
        return null;
    }

    @Override
    public Void visit(ArithmeticOperation node, Type param) {
        node.getLeft().accept(this, param);
        node.getRight().accept(this, param);

        Type result = node.getLeft().getType().arithmetic(node.getRight().getType(), node);
        node.setType(result);
        return null;
    }

    @Override
    public Void visit(ArrayAccess node, Type param) {
        node.getValue().accept(this, param);
        node.getAccess().accept(this, param);

        Type result = node.getAccess().getType().squareBrackets(node.getValue().getType(), node);
        node.setType(result);
        return null;
    }

    @Override
    public Void visit(FunctionDefinition node, Type param) {
        FunctionType funcType = (FunctionType) node.getType();

        for (VarDefinition p : funcType.getParameters()) {
            p.getType().mustBeBuiltIn(p);
        }

        Type expected = funcType.getReturnType();
        for (Statement stmt : node.getBody()) {
            stmt.accept(this, expected);
        }
        return null;
    }

    @Override
    public Void visit(Return node, Type expectedReturnType) {
        node.getReturnValue().accept(this, null);

        node.getReturnValue().getType().mustBePromotedTo(expectedReturnType, node);
        return null;
    }

    @Override
    public Void visit(Assignment node, Type param) {
        node.getLeft().accept(this, param);
        node.getRight().accept(this, param);

        node.getRight().getType().mustBePromotedTo(node.getLeft().getType(), node);
        return null;
    }

    @Override
    public Void visit(IfElse node, Type expectedReturnType) {
        node.getCondition().accept(this, null);
        node.getCondition().getType().mustBeLogic(node.getCondition());

        for (Statement stmt : node.getBody()) {
            stmt.accept(this, expectedReturnType);
        }
        for (Statement stmt : node.getElseBody()) {
            stmt.accept(this, expectedReturnType);
        }
        return null;
    }

    @Override
    public Void visit(While node, Type expectedReturnType) {
        node.getCondition().accept(this, null);
        node.getCondition().getType().mustBeLogic(node.getCondition());

        for (Statement stmt : node.getBody()) {
            stmt.accept(this, expectedReturnType);
        }
        return null;
    }

    @Override
    public Void visit(FieldAccess node, Type param) {
        node.getName().accept(this, param);

        Type result = node.getName().getType().dot(node.getField(), node);
        node.setType(result);
        return null;
    }

    @Override
    public Void visit(ComparisonOperation node, Type param) {
        node.getLeft().accept(this, param);
        node.getRight().accept(this, param);

        Type result = node.getLeft().getType().comparison(node.getRight().getType(), node);
        node.setType(result);
        return null;
    }

    @Override
    public Void visit(LogicOperation node, Type param) {
        node.getLeft().accept(this, param);
        node.getRight().accept(this, param);

        Type result = node.getLeft().getType().logic(node.getRight().getType(), node);
        node.setType(result);
        return null;
    }

    @Override
    public Void visit(UnaryNot node, Type param) {
        node.getRight().accept(this, param);

        Type result = node.getRight().getType().logic(node);
        node.setType(result);
        return null;
    }

    @Override
    public Void visit(FunctionCall node, Type param) {
        node.getName().accept(this, param);

        List<Type> argTypes = new ArrayList<>();
        for (Expression arg : node.getExpressions()) {
            arg.accept(this, param);
            argTypes.add(arg.getType());
        }

        Type result = node.getName().getType().parenthesis(argTypes, node);
        node.setType(result);
        return null;
    }

    @Override
    public Void visit(Cast node, Type param) {
        node.getExpression().accept(this, param);

        node.getType().mustBeBuiltIn(node);

        Type result = node.getExpression().getType().cast(node.getType(), node);
        node.setType(result);
        return null;
    }

    @Override
    public Void visit(Log node, Type param) {
        node.getExpression().accept(this, param);
        node.getExpression().getType().mustBeBuiltIn(node);
        return null;
    }

    @Override
    public Void visit(Input node, Type param) {
        for(Expression exp: node.getExpressions()){
            exp.accept(this,param);
            exp.getType().mustBeBuiltIn(node);
        }
        return null;
    }
}