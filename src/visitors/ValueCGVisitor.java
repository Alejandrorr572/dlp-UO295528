package visitors;

import ast.FunctionCall;
import ast.expressions.*;
import ast.types.FunctionType;
import ast.types.Type;
import codegen.CodeGenerator;

public class ValueCGVisitor extends AbstractCGVisitor<Void,Void>{

    private AddressCGVisitor av;

    public ValueCGVisitor(CodeGenerator codeGenerator) {
        super(codeGenerator);
    }

    public void setAddressCGVisitor(AddressCGVisitor av){
        this.av=av;
    }

    /**
     * value[[Variable: expression -> ID]]() =
     * * address[[expression]]()
     * load expression.type.suffix()
     */
    @Override
    public Void visit(Variable node, Void param) {
        node.accept(av, null);
        cg.load(node.getType());
        return null;
    }

    /**
     * value[[FieldAccess: expression1 -> expression2 ID]]() =
     * * address[[expression1]]()
     * load expression1.type.suffix()
     */
    @Override
    public Void visit(FieldAccess node, Void param) {
        node.accept(av, null);
        cg.load(node.getType());
        return null;
    }

    /**
     * value[[ArrayAccess: expression1 -> expression2 expression3]]() =
     * * address[[expression1]]()
     * load expression1.type.suffix()
     */
    @Override
    public Void visit(ArrayAccess node, Void param) {
        node.accept(av, null);
        cg.load(node.getType());
        return null;
    }

    /**
     * value[[IntLiteral: expression -> INT_CONSTANT]]() =
     * * pushi INT_CONSTANT
     */
    @Override
    public Void visit(IntLiteral node, Void param) {
        cg.push(node.getValue());
        return null;
    }

    /**
     * value[[DoubleValue: expression -> REAL_CONSTANT]]() =
     * * pushf REAL_CONSTANT
     */
    @Override
    public Void visit(DoubleValue node, Void param) {
        cg.push(node.getValue());
        return null;
    }

    /**
     * value[[CharLiteral: expression -> CHAR_CONSTANT]]() =
     * * pushb CHAR_CONSTANT
     */
    @Override
    public Void visit(CharLiteral node, Void param) {
        cg.push( node.getValue());
        return null;
    }

    /**
     * value[[ArithmeticOperation: expression1 -> expression2 expression3]]() =
     * * value[[expression2]]()
     * convertTo(expression2.type, expression1.type)
     * value[[expression3]]()
     * convertTo(expression3.type, expression1.type)
     * arithmeticOperator expression1.type.suffix()
     */
    @Override
    public Void visit(ArithmeticOperation node, Void param) {
        node.getLeft().accept(this, null);
        node.getRight().accept(this, null);
        cg.arithmetic(node.getOperator(), node.getType());

        return null;
    }

    /**
     * value[[ComparisonOperation: expression1 -> expression2 expression3]]() =
     * * value[[expression2]]()
     * convertTo(expression2.type, highestCommonType)
     * value[[expression3]]()
     * convertTo(expression3.type, highestCommonType)
     * comparisonOperator highestCommonType.suffix()
     */
    @Override
    public Void visit(ComparisonOperation node, Void param) {
        node.getLeft().accept(this, null);
        node.getRight().accept(this, null);
        cg.comparison(node.getOperator(), node.getLeft().getType());

        return null;
    }

    /**
     * value[[LogicOperation: expression1 -> expression2 expression3]]() =
     * * value[[expression2]]()
     * value[[expression3]]()
     * logicOperator
     */
    @Override
    public Void visit(LogicOperation node, Void param) {
        node.getLeft().accept(this, null);
        node.getRight().accept(this, null);
        cg.logic(node.getOperator());

        return null;
    }

    /**
     * value[[UnaryMinus: expression1 -> expression2]]() =
     * * value[[expression2]]()
     * push -1
     * mul expression1.type.suffix()
     */
    @Override
    public Void visit(UnaryMinus node, Void param) {
        node.getRight().accept(this, null);
        cg.minus();

        return null;
    }

    /**
     * value[[UnaryNot: expression1 -> expression2]]() =
     * * value[[expression2]]()
     * not
     */
    @Override
    public Void visit(UnaryNot node, Void param) {
        node.getRight().accept(this, null);
        cg.not();

        return null;
    }

    /**
     * value[[Cast: expression1 -> type expression2]]() =
     * * value[[expression2]]()
     * convertTo(expression2.type, type)
     */
    @Override
    public Void visit(Cast node, Void param) {
        node.getExpression().accept(this, null);
        cg.convertTo(node.getExpression().getType(), node.getType());

        return null;
    }

    /**
     * value[[FunctionCall: expression1 -> ID expression2*]]() =
     * * for (int i = 0; i < expression2*.size(); i++) {
     * value[[expression2*.get(i)]]()
     * convertTo(expression2*.get(i).type, ID.definition.type.parameters.get(i).type)
     * }
     * call ID.name
     */
    @Override
    public Void visit(FunctionCall node, Void param) {
        FunctionType expectedType = (FunctionType) node.getName().getDefinition().getType();

        for (int i = 0; i < node.getExpressions().size(); i++) {
            Expression arg = node.getExpressions().get(i);

            arg.accept(this, null);

            Type expectedParamType = expectedType.getParameters().get(i).getType();
            cg.convertTo(arg.getType(), expectedParamType);
        }

        cg.call(node.getName().getName());

        return null;
    }
}

