import java.util.HashMap;
import java.util.Map;

public class Operator {
	private String name;
	private boolean[] truthTable; // 4 element array for values 00 01 10 11

	public static Map<String, boolean[]> commonTables = new HashMap<>();

	static {
		//					   00     01     10     11
		boolean[] tt_and = {false, false, false,  true};
		boolean[] tt_or  = {false,  true,  true,  true};
		boolean[] tt_xor = {false,  true,  true, false};
		boolean[] tt_up  = { true,  true,  true, false};
		boolean[] tt_down= { true, false, false,  true};
		boolean[] tt_to  = { true,  true, false,  true};
		boolean[] tt_eq  = { true, false, false,  true};
		//boolean[] tt_and = {false, false, false, false};

		commonTables.put("and", tt_and);
		commonTables.put("in", tt_and);
		commonTables.put("&", tt_and);
		commonTables.put("&&", tt_and);
		commonTables.put("*", tt_and);

		commonTables.put("or", tt_or);
		commonTables.put("ali", tt_or);
		commonTables.put("v", tt_or);
		commonTables.put("|", tt_or);
		commonTables.put("||", tt_or);
		commonTables.put("x", tt_or);

		commonTables.put("xor", tt_xor);
		commonTables.put("differs", tt_xor);
		commonTables.put("different", tt_xor);
		commonTables.put("inequivalent", tt_xor);
		commonTables.put("razlicen", tt_xor);
		commonTables.put("razlicna", tt_xor);
		commonTables.put("razlicno", tt_xor);
		commonTables.put("drugacen", tt_xor);
		commonTables.put("drugacna", tt_xor);
		commonTables.put("drugacno", tt_xor);
		commonTables.put("+", tt_xor);

		commonTables.put("to", tt_to);
		commonTables.put("=>", tt_to);
		commonTables.put("implies", tt_to);
		commonTables.put("implicira", tt_to);
		commonTables.put("sledi", tt_to);
		commonTables.put("posledicno", tt_to);

		commonTables.put("equivalent", tt_eq);
		commonTables.put("equals", tt_eq);
		commonTables.put("equal", tt_eq);
		commonTables.put("enako", tt_eq);
		commonTables.put("ekvivalentno", tt_eq);
		commonTables.put("ekvivalenca", tt_eq);
		commonTables.put("enakovreden", tt_eq);
		commonTables.put("enakovredna", tt_eq);
		commonTables.put("enakovredno", tt_eq);
		commonTables.put("eq", tt_eq);
		commonTables.put("equ", tt_eq);
		commonTables.put("<=>", tt_eq);
		commonTables.put("=", tt_eq);
		commonTables.put("==", tt_eq);

		commonTables.put("up", tt_up);

		commonTables.put("down", tt_down);
	}

	public Operator(String _name) {
		this.name = _name;
		if (! commonTables.containsKey(this.name))
			throw new IllegalArgumentException("Undefined operator: "+this.name);
		this.truthTable = commonTables.get(this.name);
	}

	public Operator(String _name, boolean[] _tt) {
		this.name = _name;
		this.truthTable = _tt.clone();
	}

	public String toStr() {
		return this.name;
	}
	public boolean eval(boolean a, boolean b) {
		int x = a ? 1 : 0;
		int y = b ? 1 : 0;
		return this.truthTable[2*x + y];
	}
	public String ttStr() {
		return (this.truthTable[0]?"1":"0")+(this.truthTable[1]?"1":"0")+(this.truthTable[2]?"1":"0")+(this.truthTable[3]?"1":"0");
	}
}
