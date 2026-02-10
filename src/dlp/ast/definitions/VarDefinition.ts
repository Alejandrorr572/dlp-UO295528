import {Definition} from "./Definition";
import {Type} from "../types/Type";


export class VarDefinition implements Definition{
    type: Type
    identifiers: String[]

    constructor(type: Type, identifiers: String[]) {
        this.type = type;
        this.identifiers = identifiers;
    }

}