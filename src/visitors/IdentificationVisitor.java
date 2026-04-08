package visitors;

import ast.RecordField;
import ast.definitions.Definition;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.Variable;
import ast.types.ErrorType;
import ast.types.RecordType;
import errorhandler.ErrorHandler;
import symboltable.SymbolTable;

import java.util.*;

public class IdentificationVisitor extends AbstractVisitor<Void,Void> {

    SymbolTable symbolTable = new SymbolTable();

    public Void visit(FunctionDefinition node, Void param){

        if(!symbolTable.insert(node)){
            ErrorHandler.getInstance().addError(new ErrorType(
                    "Error: The function " +node.getName()+ " in position" +"["+
                            node.getLine()+","+node.getColumn()+"]"+ " is already defined",node));
        }
        symbolTable.set();
        super.visit(node,null);
        symbolTable.reset();
        return null;
    }

    public Void visit(VarDefinition node, Void param){
        super.visit(node,null);
        if(!symbolTable.insert(node)){
            ErrorHandler.getInstance().addError(new ErrorType(
                    "Error: The variable " +node.getName()+ " in position" +"["+
            node.getLine()+","+node.getColumn()+"]"+ " is already defined",node));
        }
        return null;
    }

    public Void visit(Variable node, Void param){
        super.visit(node,null);
        Definition def = symbolTable.find(node.getName());
        if(symbolTable.find(node.getName()) == null){
            ErrorType error = new ErrorType(
                    "Error: The variable " +node.getName()+ " in position" +"["+
                            node.getLine()+","+node.getColumn()+"]"+ " has not been defined",node);
            ErrorHandler.getInstance().addError(error);
            VarDefinition var = new VarDefinition(error, node.getName(), node.getLine(), node.getColumn());
            node.setDefinition(var);
        }else{
            node.setDefinition(def);
        }

        return null;
    }

    public Void visit(RecordType node, Void param){
        super.visit(node, null);

        Set<String> seenIds = new HashSet<>();

        for(RecordField field : node.getFields()){
            String id = field.getIdentifier();

            if(!seenIds.add(id)) {
                ErrorHandler.getInstance().addError(new ErrorType(
                        "Error: The field " + id + " in position [" +
                                field.getLine() + "," + field.getColumn() + "] has been defined multiple times", field));
            }
        }

        return null;
    }
}
