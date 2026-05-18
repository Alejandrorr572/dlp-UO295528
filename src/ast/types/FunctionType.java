package ast.types;

import ast.Locatable;
import ast.definitions.VarDefinition;
import ast.statements.Statement;
import errorhandler.ErrorHandler;
import visitors.Visitor;

import java.util.List;

public class FunctionType extends AbstractType {

    private List<VarDefinition> parameters;
    private Type returnType;

    private int paramBytes;

    public FunctionType(List<VarDefinition> parameters, Type returnType) {
        this.parameters = parameters;
        this.returnType = returnType;
    }

    public List<VarDefinition> getParameters() {
        return parameters;
    }

    public Type getReturnType() {
        return returnType;
    }

    @Override
    public Type parenthesis(List<Type> argumentTypes, Locatable locatable) {

        if (argumentTypes.size() != parameters.size()) {
            ErrorType error = new ErrorType(
                    "Function expects " + parameters.size() + " params", locatable);
            ErrorHandler.getInstance().addError(error);
            return returnType;
        }

        for (int i = 0; i < parameters.size(); i++) {
            Type auxType = argumentTypes.get(i);
            Type paramType = parameters.get(i).getType();
            auxType.mustBePromotedTo(paramType, locatable);
        }

        return returnType;
    }

    @Override
    public int numberOfBytes() {
        return returnType.numberOfBytes();
    }

    public void setParamBytes(int paramBytes){
        this.paramBytes = paramBytes;
    }

    public int getParamBytes(){
       return this.paramBytes;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }
}
