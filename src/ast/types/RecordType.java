package ast.types;

import ast.Locatable;
import ast.RecordField;
import errorhandler.ErrorHandler;
import visitors.Visitor;

import java.util.List;

public class RecordType extends AbstractType {

    private List<RecordField> fields;

    public RecordType(List<RecordField> fields) {
        this.fields = fields;
    }

    public List<RecordField> getFields() {
        return fields;
    }

    @Override
    public Type dot(String fieldName, Locatable locatable) {
        for (RecordField field : fields) {
            if (field.getIdentifier().equals(fieldName)) {
                return field.getType();
            }
        }
        ErrorType error = new ErrorType(
                "Field " + fieldName + " does not exist in Record", locatable
        );
        ErrorHandler.getInstance().addError(error);
        return error;
    }

    public <PT,RT> RT accept(Visitor<PT,RT> v, PT param) {
        return v.visit(this, param);
    }
}
