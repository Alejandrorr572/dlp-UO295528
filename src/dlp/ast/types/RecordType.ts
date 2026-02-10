
import {VarDefinition} from "../definitions/VarDefinition"
import {Type} from "./Type";

class RecordType implements Type {

    definitions: VarDefinition[];

    constructor(definitions: VarDefinition[]) {
        this.definitions = definitions;
    }
}