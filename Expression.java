public class Expression {
	private Operator operator;
	private Expression right;
	private Expression left;

	public Expression() {
		// a child constructor was called
	}
	public Expression(Expression _left, Operator _operator, Expression _right) {
		this.operator = _operator;
		this.left = _left;
		this.right = _right;
	}

	public boolean eval(boolean[] dolocilo) {
		return this.operator.eval(this.left.eval(dolocilo), this.right.eval(dolocilo));
	}

	public String toStr() {
		return "("+this.left.toStr()+" "+this.operator.toStr()+" "+this.right.toStr()+")";
	}
}
