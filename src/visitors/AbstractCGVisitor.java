package visitors;

import ast.FunctionCall;
import ast.Program;
import ast.RecordField;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import codegen.CodeGenerator;

public class AbstractCGVisitor<PT,RT> implements Visitor<PT,RT> {

    CodeGenerator cg;

    public AbstractCGVisitor(CodeGenerator cg) {
        this.cg = cg;
    }

    @Override
    public RT visit(Program node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(ArithmeticOperation node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(ArrayAccess node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(Cast node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(CharLiteral node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(ComparisonOperation node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(DoubleValue node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(FieldAccess node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(LogicOperation node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(UnaryMinus node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(UnaryNot node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(IntLiteral node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(Variable node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(Assignment node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(IfElse node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(Log node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(Input node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(Return node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(While node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(FunctionDefinition node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(VarDefinition node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(ArrayType node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(Char node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(ErrorType node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(FunctionType node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(Int node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(Real node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(RecordType node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(VoidType node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(RecordField node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }

    @Override
    public RT visit(FunctionCall node, PT param) {
        throw new UnsupportedOperationException(this.toString() + " Doesn't support this operation.");
    }
}
