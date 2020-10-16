public class Expression {
	private String name; // name of this primitive Dolocilo. 0 if not primitive
	private int index; // index of this primitive in Dolocilo. 0 if not primitive
	private Operator operator;
	private Expression right;
	private Expression left;

	public Expression(String _name, int _index) { // is primitive
		this.name = _name;
		this.index = _index;
	}
	public Expression(Expression _left, Operator _operator, Expression _right) {
		// is not primitive
		this.name = "";
		this.index = 0;
		this.operator = _operator;
		this.left = _left;
		this.right = _right;
	}

	public boolean eval(boolean[] dolocilo) {
		if (this.index != 0)
			return dolocilo[this.index];
		else
			return this.operator.eval(this.left.eval(dolocilo), this.right.eval(dolocilo));
	}

	public String toStr() {
		if (this.index != 0)
			return this.name;
		else
			return "("+this.left.toStr()+this.operator.toStr()+this.right.toStr()+")";
	}
}
