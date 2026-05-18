package visitors;

import ast.RecordField;
import ast.expressions.ArrayAccess;
import ast.expressions.FieldAccess;
import ast.expressions.Variable;
import ast.statements.Log;
import ast.types.Int;
import ast.types.RecordType;
import codegen.CodeGenerator;

public class AddressCGVisitor extends AbstractCGVisitor<Void,Void>{

    private ValueCGVisitor vv;

    public AddressCGVisitor(CodeGenerator codeGenerator) {
        super(codeGenerator);
    }

    public void setValueCgVisitor(ValueCGVisitor vv){
        this.vv=vv;
    }

    /**
     * address[[Variable: expression -> ID]]() =
     *
     * if (expression.definition.scope == 0) {
     * pusha expression.definition.offset
     * } else {
     * push bp
     * pushi expression.definition.offset
     * addi
     * }
     */
    @Override
    public Void visit(Variable node, Void param) {
        int scope = node.getDefinition().getScope();
        int offset = node.getDefinition().getOffset();

        if (scope == 0) {
            cg.pusha(offset);
        } else {
            cg.pushBp();
            cg.push(offset);
            cg.arithmetic("+", Int.getInstance());
        }

        return null;
    }

    /**
     *
     * Address[[FieldAccess: expr1 -> expr2 ID]]() =
     *
     * Address[[expr2]]()
     *
     * Expr2.type.getField(ID).offset
     *
     * Pushi
     *
     * Addi
     */
    @Override
    public Void visit(FieldAccess node, Void param){
        node.getName().accept(this,null);
        RecordType type = (RecordType) node.getName().getType();
        cg.push(type.getField(node.getField()).getOffset());
        cg.arithmetic("+", Int.getInstance());

        return null;
    }

    /**
     * Address[[ArrayAccess: expr1 -> expr2 expr3]]()=
     *
     * Address[[expr2]]()
     *
     * Value[[expr3]]() //te da el indice
     *
     * ConvertTo(expr3.type, int.getInstance())
     *
     * Pushi Expr1.type.bytes
     *
     * Muli
     *
     * Addi
     */
    @Override
    public Void visit(ArrayAccess node, Void param){
        node.getAccess().accept(this,null);
        node.getValue().accept(vv,null);

        cg.convertTo(node.getValue().getType(), Int.getInstance());
        cg.push(node.getType().numberOfBytes());
        cg.arithmetic("*", Int.getInstance());
        cg.arithmetic("+", Int.getInstance());

        return null;
    }
}
