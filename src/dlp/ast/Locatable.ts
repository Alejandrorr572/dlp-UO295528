
import type {ASTNode} from "./ASTNode";

export interface Locatable extends ASTNode {
    getLine(): number;
    getColumn(): number;
}