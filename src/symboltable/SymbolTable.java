package symboltable;

import java.util.*;
import ast.definitions.Definition;

public class SymbolTable {
	
	private int scope=0;
	private List<Map<String,Definition>> table;
	public SymbolTable()  {
		table = new ArrayList<>();
		table.add(new HashMap<>());
	}

	public void set() {
		table.add(new HashMap<>());
		this.scope++;
	}
	public void reset() {
		this.scope--;
		table.removeLast();
	}
	
	public boolean insert(Definition definition) {
		if(findInCurrentScope(definition.getName()))
			return false;

		table.get(scope).put(definition.getName(),definition);
		definition.setScope(scope);
		return true;
	}
	
	public Definition find(String id) {
		for(int i = scope; i >= 0; i--){
			if(table.get(i).containsKey(id)) return table.get(i).get(id);
		}
		return null;
	}

	//package-protected for testing pourposes
	boolean findInCurrentScope(String id) {
		return table.get(scope).containsKey(id);
	}
}
