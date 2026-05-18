package visitors;

import ast.FunctionCall;
import ast.Program;
import ast.definitions.Definition;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.Expression;
import ast.statements.*;
import ast.types.Int;
import ast.types.VoidType;
import codegen.CodeGenerator;

public class ExecuteCGVisitor extends AbstractCGVisitor<FunctionDefinition,Void>{
    private AddressCGVisitor av;
    private ValueCGVisitor vv;

    public ExecuteCGVisitor(CodeGenerator codeGenerator) {
        super(codeGenerator);
        this.av = new AddressCGVisitor(codeGenerator);
        this.vv = new ValueCGVisitor(codeGenerator);
        vv.setAddressCGVisitor(av);
        av.setValueCgVisitor(vv);
    }

    /**
     * execute[[Program: program -> definition1* definition2*]]
     *
     * for (VarDefinition v : definition1*) {
     *
     *       execute[[v]]()
     *
     * }
     *
     * for (FunctionDefinition v : definition2*) {
     *
     *       execute[[v]]()
     *
     * }
     */
    @Override
    public Void visit(Program node, FunctionDefinition param) {

        cg.source(cg.getInput());

        cg.comment("Global Variables");
        for (Definition def : node.getDefinitions()) {
            if (def instanceof VarDefinition) {
                def.accept(this, param);
            }
        }

        cg.invocationToMain();

        for (Definition def : node.getDefinitions()) {
            if (def instanceof FunctionDefinition) {
                def.accept(this, param);
            }
        }

        cg.close();
        return null;
    }

    /**
     * execute[[VarDefinition: definition -> type ID]]() =
     * * ' * type.toString() ID (offset definition.offset)
     */
    @Override
    public Void visit(VarDefinition node, FunctionDefinition param) {
        cg.comment(node.getType().toString() + " " + node.getName() + " (offset " + node.getOffset() + ")");
        return null;
    }

    /**
     * execute[[FuncDef: definition -> ID type definition* stmt*]]() =
     *
     *     ID <:>
     *
     *     <'parameters>
     *
     *     for (vardef v : type.params()) {
     *
     *         execute[[v]]()
     *
     *     }
     *
     *
     *0
     *     //Locales
     *
     *     for (VarDefinition v : definition*) {
     *
     *         execute[[v]]()
     *
     *     }
     *
     *     <enter> definition.bytesLocalSum
     *
     *     for (Statement st : stmt*) {
     *
     *         if (!st instanceof VarDefinition) {
     *
     *             execute[[st]]()
     *
     *         }
     *
     *          if (type.returnType == VoidType)
     *
     *         <ret> 0, def.bytesLocalSum, type.parameters.map(p -> p.type.numberOfBytes()).sum()
     *
     */
    @Override
    public Void visit(FunctionDefinition node, FunctionDefinition param) {
        cg.line(node.getLine());
        cg.writeLabel(node.getName());

        cg.comment("Parameters");
        for (VarDefinition funcParam : node.getType().getParameters()) {
            funcParam.accept(this, node);
        }

        cg.enter(node.getLocalBytes());

        cg.comment("Rest");
        for (Statement stmt : node.getBody()) {
            cg.line(stmt.getLine());
            stmt.accept(this, node);
        }

        if (node.getType().getReturnType() instanceof VoidType) {
            int bytesLocals = node.getLocalBytes();
            int bytesParams = node.getType().getParamBytes();
            cg.ret(0, bytesLocals, bytesParams);
        }

        return null;
    }

    /**
     execute[[ReturnStmt : stmt -> exp]](FuncDef def)=

     value[[exp]]()

     cg.convertTo(exp1.type, def.type.returnType)

     <ret> def.type.returnType.numberOfBytes(), def.bytesLocalSum, def.type.parameters)
     */
    @Override
    public Void visit(Return node, FunctionDefinition param) {
        cg.comment("Return");

        node.getReturnValue().accept(vv, null);

        cg.convertTo(node.getReturnValue().getType(), param.getType().getReturnType());

        int bytesReturn = param.getType().getReturnType().numberOfBytes();
        int bytesLocals = param.getLocalBytes();
        int bytesParams = param.getType().getParamBytes();

        cg.ret(bytesReturn, bytesLocals, bytesParams);

        return null;
    }

    /**
     * execute[[Log: stmt -> exp]]() =
     * value[[expr]]()
     * out expr.type.suffix()
     *
     */
    @Override
    public Void visit(Log node, FunctionDefinition param) {
        cg.comment("Log");
        node.getExpression().accept(vv, null);
        cg.out(node.getExpression().getType());

        return null;
    }

    /**
     * execute[[Input: stmt -> exp+]]() =
     * for(Expression expr : exp+) {
     * address[[expr]]()
     * in expr.type.suffix()
     * store expr.type.suffix()
     * }
     */
    @Override
    public Void visit(Input node, FunctionDefinition param) {
        cg.comment("Input");
        for (Expression expr : node.getExpressions()) {
            expr.accept(av, null);
            cg.in(expr.getType());
            cg.store(expr.getType());
        }

        return null;
    }

    /**
     * execute[[Assignment: stmt -> exp1 exp2]]() =
     * * address[[exp1]]()
     * value[[exp2]]()
     * convertTo(exp2.type, exp1.type)
     * store exp1.type.suffix()
     */
    public Void visit(Assignment a, FunctionDefinition param){
        cg.comment("Assignment");
        a.getLeft().accept(av,null);
        a.getRight().accept(vv,null);

        cg.convertTo(a.getRight().getType(), a.getLeft().getType());

        cg.store(a.getLeft().getType());

        return null;
    }

    /**
     * Execute[[While: stmt1 -> expr1 stmt2*]]()=
     *
     * #CONDITION
     *
     * Value[[expr1]]
     *
     * ConvertTo(exp1.type, int)
     *
     * Jz END
     *
     * Stmt2*.foreach(s-> execute[[s]])
     *
     * Jmp CONDITION
     *
     * #END
     */
    @Override
    public Void visit(While node, FunctionDefinition param){

        String whileLbl = cg.getLabel("While");
        String endLbl = cg.getLabel("End");

        cg.writeLabel(whileLbl);
        node.getCondition().accept(vv, null);
        cg.convertTo(node.getCondition().getType(), Int.getInstance());
        cg.jz(endLbl);
        node.getBody().forEach(s -> s.accept(this,param));
        cg.jmp(whileLbl);
        cg.writeLabel(endLbl);



        return null;
    }

    /**
     * Execute[[IfElse: stmt1 -> exp1 stmt2* stmt3*]]()=
     *
     * Value[[expr1]]
     *
     * ConvertTo(exp1.type, int)
     *
     * Jz Else
     *
     * Stmt2*.foreach(s-> execute[[s]])
     *
     * Jmp end
     *
     * ELSE
     *
     * Stmt3*.foreach(s-> execute[[s]])
     *
     * END
     */
    @Override
    public Void visit(IfElse node, FunctionDefinition param){

        String elseLbl = cg.getLabel("Else");
        String endLbl = cg.getLabel("End");

        node.getCondition().accept(vv, null);
        cg.convertTo(node.getCondition().getType(), Int.getInstance());
        cg.jz(elseLbl);

        //If
        node.getBody().forEach(s -> s.accept(this,param));
        cg.jmp(endLbl);

        //Else
        cg.writeLabel(elseLbl);
        node.getElseBody().forEach(s -> s.accept(this,param));

        cg.writeLabel(endLbl);

        return null;
    }

    /**
     * execute[[FuncInvocation: stmt -> exp1 exp2*]]()=
     *
     *     value[[(Expression) stmt]]()
     *
     *     if (exp1.type.returnType != VoidType)
     *         pop ex1.type.returnType.suffix
     */
    @Override
    public Void visit(FunctionCall node, FunctionDefinition param) {

        node.accept(vv, null);

        if (!(node.getType() instanceof VoidType)) {
            cg.pop(node.getType());
        }

        return null;
    }
}
