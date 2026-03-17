package visitors;

import ast.FunctionCall;
import ast.Program;
import ast.RecordField;
import ast.expressions.*;
import ast.statements.*;
import ast.definitions.*;
import ast.types.*;

public interface Visitor <PT, RT> {

    //Program
    RT visit(Program node, PT param);

    //Expressions
    RT visit(ArithmeticOperation node, PT param);
    RT visit(ArrayAccess node, PT param);
    RT visit(Cast node, PT param);
    RT visit(CharLiteral node, PT param);
    RT visit(ComparisonOperation node, PT param);
    RT visit(DoubleValue node, PT param);
    RT visit(FieldAccess node, PT param);
    RT visit(LogicOperation node, PT param);
    RT visit(UnaryMinus node, PT param);
    RT visit(UnaryNot node, PT param);
    RT visit(IntLiteral node, PT param);
    RT visit(Variable node, PT param);

    //Statements
    RT visit(Assignment node, PT param);
    RT visit(IfElse node, PT param);
    RT visit(Log node, PT param);
    RT visit(Input node, PT param);
    RT visit(Return node, PT param);
    RT visit(While node, PT param);

    //Definitions
    RT visit(FunctionDefinition node, PT param);
    RT visit(VarDefinition node, PT param);

    //Types
    RT visit(ArrayType node, PT param);
    RT visit(Char node, PT param);
    RT visit(ErrorType node, PT param);
    RT visit(FunctionType node, PT param);
    RT visit(Int node, PT param);
    RT visit(Real node, PT param);
    RT visit(RecordType node, PT param);
    RT visit(VoidType node, PT param);

    //Los que faltan
    RT visit(RecordField node, PT param);
    RT visit(FunctionCall node, PT param);
}
