public class PrimitiveExpression extends Expression {
	private String name;
	private int index; // index of this primitive in the dolocilo

	public PrimitiveExpression(String _name, int _index) {
		// is primitive
		this.name = _name;
		this.index = _index;
	}

	public boolean eval(boolean[] dolocilo) {
		return dolocilo[this.index];
	}

	public String toStr() {
		return this.name;
	}
}
