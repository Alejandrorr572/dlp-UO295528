package visitors;

import ast.RecordField;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.statements.Statement;
import ast.types.FunctionType;
import ast.types.RecordType;

import java.util.List;

public class OffsetVisitor extends AbstractVisitor<Boolean,Void> {

    private int globalOffset = 0;
    private int localOffset = 0;

    @Override
    public Void visit(VarDefinition node, Boolean param) {
        node.getType().accept(this, param);

        if (node.getScope() == 0) {
            node.setOffset(globalOffset);
            globalOffset += node.getType().numberOfBytes();
        }
        else {
            if (param != null && !param) {
                localOffset -= node.getType().numberOfBytes();
                node.setOffset(localOffset);
            }
        }
        return null;
    }

    @Override
    public Void visit(FunctionDefinition node, Boolean param) {
        node.getType().accept(this, null);

        localOffset = 0; //Lo que dijo Oscar

        for (Statement stmt : node.getBody()) {
            stmt.accept(this, false);
        }

        node.setLocalBytes(-localOffset); // y lo pongo en positvi
        return null;
    }

    @Override
    public Void visit(FunctionType node, Boolean param) {
        int current = 4;

        List<VarDefinition> reversedParams = node.getParameters().reversed();
        for (VarDefinition funcParam : reversedParams) {
            funcParam.setOffset(current);
            current += funcParam.getType().numberOfBytes();
        }

        node.setParamBytes(current - 4); //Params - BP supongo palo de 0 a menos tanto

        /**
         * Si en el examen nos hacen poner mas q tipo simple descomento y gg ez
        for (VarDefinition funcParam : node.getParameters()) {
            funcParam.accept(this, true);
        }
         */
        return null;
    }

    @Override
    public Void visit(RecordType node, Boolean param) {
        int bytes = 0;

        for (RecordField field : node.getFields()) {
            field.getType().accept(this, param);
            field.setOffset(bytes);
            bytes += field.getType().numberOfBytes();
        }

        return null;
    }
}
