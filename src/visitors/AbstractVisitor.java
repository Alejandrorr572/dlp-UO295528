package visitors;

import ast.FunctionCall;
import ast.Program;
import ast.RecordField;
import ast.definitions.Definition;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import errorhandler.ErrorHandler;

public abstract class AbstractVisitor<PT, RT> implements Visitor<PT, RT> {

    public RT visit(Program node, PT param) {
        for(Definition def: node.getDefinitions()){
            def.accept(this,param);
        }
        return null;
    }

    
    public RT visit(ArithmeticOperation node, PT param) {
        node.getLeft().accept(this,param);
        node.getRight().accept(this,param);
        return null;
    }

    
    public RT visit(ArrayAccess node, PT param) {
        node.getAccess().accept(this,param);
        node.getValue().accept(this,param);
        return null;
    }

    
    public RT visit(Cast node, PT param) {
        node.getExpression().accept(this,param);
        return null;
    }

    
    public RT visit(CharLiteral node, PT param) {
        return null;
    }

    
    public RT visit(ComparisonOperation node, PT param) {
        node.getLeft().accept(this,param);
        node.getRight().accept(this,param);
        return null;
    }

    
    public RT visit(DoubleValue node, PT param) {
        return null;
    }

    
    public RT visit(FieldAccess node, PT param) {
        node.getName().accept(this,param);
        return null;
    }

    
    public RT visit(LogicOperation node, PT param) {
        node.getLeft().accept(this,param);
        node.getRight().accept(this,param);
        return null;
    }

    
    public RT visit(UnaryMinus node, PT param) {
        node.getRight().accept(this,param);
        return null;
    }

    
    public RT visit(UnaryNot node, PT param) {
        node.getRight().accept(this,param);
        return null;
    }

    
    public RT visit(IntLiteral node, PT param) {
        return null;
    }

    
    public RT visit(Variable node, PT param) {
        return null;
    }

    
    public RT visit(Assignment node, PT param) {
        node.getLeft().accept(this,param);
        node.getRight().accept(this,param);
        return null;
    }

    
    public RT visit(IfElse node, PT param) {
        node.getCondition().accept(this, param);
        for(Statement st : node.getBody()){
            st.accept(this,param);
        }
        for(Statement st : node.getElseBody()){
            st.accept(this,param);
        }
        return null;
    }

    
    public RT visit(Log node, PT param) {
        node.getExpression().accept(this, param);
        return null;
    }

    
    public RT visit(Input node, PT param) {
        for(Expression exp : node.getExpressions()){
            exp.accept(this, param);
        }
        return null;
    }

    
    public RT visit(Return node, PT param) {
        node.getReturnValue().accept(this, param);
        return null;
    }

    
    public RT visit(While node, PT param) {
        node.getCondition().accept(this,param);
        for(Statement st : node.getBody()){
            st.accept(this,param);
        }
        return null;
    }

    
    public RT visit(FunctionDefinition node, PT param) {
        for(Statement st : node.getBody()){
            st.accept(this,param);
        }
        node.getType().accept(this,param);
        return null;
    }

    
    public RT visit(VarDefinition node, PT param) {
        node.getType().accept(this,param);
        return null;
    }

    
    public RT visit(ArrayType node, PT param) {
        node.getElementsType().accept(this,param);
        return null;
    }

    
    public RT visit(Char node, PT param) {
        return null;
    }

    
    public RT visit(ErrorType node, PT param) {
        return null;
    }

    
    public RT visit(FunctionType node, PT param) {
        for(Definition def : node.getParameters()){
            def.accept(this,param);
        }
        node.getReturnType().accept(this,param);
        return null;
    }

    
    public RT visit(Int node, PT param) {
        return null;
    }

    
    public RT visit(Real node, PT param) {
        return null;
    }

    
    public RT visit(RecordType node, PT param) {
        for(RecordField rec : node.getFields()){
            rec.accept(this,param);
        }
        return null;
    }

    
    public RT visit(VoidType node, PT param) {
        return null;
    }

    
    public RT visit(RecordField node, PT param) {
        return null;
    }

    public RT visit(FunctionCall node, PT param) {
        node.getName().accept(this,param);
        for(Expression exp : node.getExpressions()){
            exp.accept(this,param);
        }
        return null;
    }
}
