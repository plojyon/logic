public class Parser {
	final static int MAX_PRIMITIVE_LEN = 10; // is also the max operator len
	final static int MAX_PRIMITIVE_COUNT = 20;
	final static int MAX_EXPRESSION_LEN = 100;

	private int pos;
	private String str;
	public  Expression exp;
	private Expression left;
	private Operator operator;
	private Expression right;

	public String[] primitives;
	public int primitiveCount;

	public String toStr() {
		//return this.str;
		return this.exp.toStr();
	}

	/*
	I AM VERY VOLATILE AND WILL BREAK IF ANYTHING IS NOT PRECISELY TO SPEC
	i accept expressions of the form: "(a operator b)", where
	a and b may be either of:
	 * expressions of the given form
	 * a string, starting with a letter, without spaces
	 * 1
	 * 0
	operator must be a string without spaces.
	Example: ((((p v q) & (q => r)) & p) => (q & 1))
	Example: ((a and (b or c)) implies a)
	Please watch the parentheses and proper spacing.
	*/
	public Parser(String _str) {
		// made a fresh top-ever parser (no primitives list exists yet)
		this(_str, new String[MAX_PRIMITIVE_COUNT], 0);
	}
	public Parser(String _str, String[] _primitives, int _primitiveCount) {
		// made a recursive parser as a part of a larger parse job
		// requires a preexisting primitives list, so the indices are correct
		this.str = _str;
		this.pos = 0;
		this.primitives = _primitives.clone();
		this.primitiveCount = _primitiveCount;

		verify(current(), '(');
		next();
		if (current() == '(')
			this.left = parseCombined();
		else if (Character.isDigit(current()))
			this.left = parseConstant();
		else
			this.left = parsePrimitive(' ');

		verify(current(), ' ');
		next();

		String operator = bufferUntil(' ', MAX_PRIMITIVE_LEN);
		this.operator = new Operator(operator);

		verify(current(), ' ');
		next();

		if (current() == '(')
			this.right = parseCombined();
		else if (Character.isDigit(current()))
			this.right = parseConstant();
		else
			this.right = parsePrimitive(')');

		verify(current(), ')');

		this.exp = new Expression(this.left, this.operator, this.right);
	}

	private String bufferUntil(char stop, int bufferSize) {
		int i = 0;
		char[] buffer = new char[bufferSize];
		while (current() != stop) {
			buffer[i++] = current();
			next();
		}
		String str = new String(buffer, 0, i);
		return str;
	}

	private Expression parseCombined() {
		// buffer until the matching ), then make a Parser to deal with it
		char[] buffer = new char[MAX_EXPRESSION_LEN];
		buffer[0] = '(';
		int i = 1;
		int p = 1;
		while (i != 0) {
			next();
			if (current() == '(') i++;
			if (current() == ')') i--;
			buffer[p++] = current();
		}
		// done buffering; make a child parser
		String bufferStr = new String(buffer, 0, p);
		Parser parser = new Parser(bufferStr, this.primitives, this.primitiveCount);
		// merge the newfound primitives
		for (int j = 0; j < parser.primitiveCount; j++) {
			addPrimitive(parser.primitives[j]);
		}
		next();
		return parser.exp;
	}

	private ConstantExpression parseConstant() {
		// parse for 1 character
		if (current() != '1' && current() != '0')
			throw new IllegalArgumentException("Parsing of "+this.str+" failed at ch"+this.pos+". Expected constant expression, got >"+current()+"<");
		ConstantExpression c = new ConstantExpression(current() == '1');
		next();
		return c;
	}

	private PrimitiveExpression parsePrimitive(char stop) {
		// parse until `stop` (either ' ' or ')', depending on location)
		String name = bufferUntil(stop, MAX_PRIMITIVE_LEN);
		addPrimitive(name);
		return new PrimitiveExpression(name, indexOf(this.primitives, name));
	}

	private void addPrimitive(String name) {
		if (indexOf(this.primitives, name) == -1)
			this.primitives[this.primitiveCount++] = name;
	}

	private char current() {
		return this.str.charAt(this.pos);
	}
	private char next() {
		return this.str.charAt(++this.pos);
	}

	private void verify(char v, char expected) {
		// alternative to assert, which might be disabled
		if (v != expected)
			throw new IllegalArgumentException("Parsing of "+this.str+" failed at ch"+this.pos+". Expected >"+expected+"<, but got >"+v+"<");
	}

	private static int indexOf(String[] arr, String target) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null) break;
			if (target.equals(arr[i]))
				return i;
		}
		return -1;
	}
}
