public class ConstantExpression extends Expression {
	private boolean constant; // the constant it represents

	public ConstantExpression(boolean _b) {
		this.constant = _b;
	}

	public boolean eval(boolean[] dolocilo) {
		return this.constant;
	}

	public String toStr() {
		return this.constant ? "1" : "0";
	}
}
