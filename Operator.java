public class Operator {
	private String name;
	private boolean[] truthTable; // 4 element array for values 00 01 10 11

	public Operator(String _name, boolean[] _tt) {
		this.name = _name;
		this.truthTable = _tt.clone();
	}

	public String toStr() {
		return " "+this.name+" ";
	}
	public boolean eval(boolean a, boolean b) {
		int x = a ? 1 : 0;
		int y = b ? 1 : 0;
		return this.truthTable[2*x + y];
	}
}
