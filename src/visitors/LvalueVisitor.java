package visitors;

import ast.Program;
import ast.RecordField;
import ast.definitions.Definition;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import errorhandler.ErrorHandler;

public class LvalueVisitor extends AbstractVisitor<Void,Void>{

    public Void visit(ArithmeticOperation node, Void param) {
        super.visit(node,param);
        node.setLvalue(false);
        return null;
    }


    public Void visit(ArrayAccess node, Void param) {
        super.visit(node,param);
        node.setLvalue(true);
        return null;
    }


    public Void visit(Cast node, Void param) {
        super.visit(node,param);
        node.setLvalue(false);
        return null;
    }


    public Void visit(CharLiteral node, Void param) {
        super.visit(node,param);
        node.setLvalue(false);
        return null;
    }


    public Void visit(ComparisonOperation node, Void param) {
        super.visit(node,param);
        node.setLvalue(false);
        return null;
    }


    public Void visit(DoubleValue node, Void param) {
        super.visit(node,param);
        node.setLvalue(false);
        return null;
    }


    public Void visit(FieldAccess node, Void param) {
        super.visit(node,param);
        node.setLvalue(true);
        return null;
    }


    public Void visit(LogicOperation node, Void param) {
        super.visit(node,param);
        node.setLvalue(false);
        return null;
    }


    public Void visit(UnaryMinus node, Void param) {
        super.visit(node,param);
        node.setLvalue(false);
        return null;
    }


    public Void visit(UnaryNot node, Void param) {
        super.visit(node,param);
        node.setLvalue(false);
        return null;
    }


    public Void visit(IntLiteral node, Void param) {
        super.visit(node,param);
        node.setLvalue(false);
        return null;
    }


    public Void visit(Variable node, Void param) {
        super.visit(node,param);
        node.setLvalue(true);
        return null;
    }

    public Void visit(Assignment node, Void param) {
        super.visit(node,param);
        if(!node.getLeft().getLvalue()){
            ErrorHandler.getInstance().addError(new ErrorType(
                    "Error: Left expression of assignment in position [" +
                                node.getLine()+ ","+node.getColumn()+"] must have true lvalue",node.getLeft()));
        }
        return null;
    }

    public Void visit(Input node, Void param) {
        super.visit(node,param);
        for(Expression exp : node.getExpressions()){
            if(!exp.getLvalue()) {
                ErrorHandler.getInstance().addError(new ErrorType(
                        "Error: Expression in position [" +
                                node.getLine()+ ","+node.getColumn()+"] must have true lvalue",exp));
            }
        }
        return null;
    }

}
