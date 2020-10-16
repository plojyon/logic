public class Logic {
	public static void main(String[] args) {
		boolean[] xor_tt = {false, true, true, false};
		Operator xor = new Operator("xor", xor_tt);

		boolean[] and_tt = {false, false, false, true};
		Operator and = new Operator("&", and_tt);

		boolean[] or_tt = {false, true, true, true};
		Operator or = new Operator("v", or_tt);

		boolean[] to_tt = {true, true, false, true};
		Operator implies = new Operator("=>", to_tt);

		//					0	1	2
		String[] vars = {"p", "q", "r"};
		Expression[] p = new Expression[vars.length]; // primitives (vars)
		for (int i = 0; i < vars.length; i++) {
			p[i] = new Expression(vars[i], i+1);
		}
		Expression porq = new Expression(p[0], or, p[1]);
		Expression qtor = new Expression(p[1], implies, p[2]);
		Expression porqandqtor = new Expression(porq, and, qtor);
		Expression left = new Expression(porqandqtor, and, p[0]);
		Expression exp = new Expression(left, implies, p[1]);

		drawTruthTable(vars, exp);
	}

	private static void drawTruthTable(String[] vars, Expression exp) {
		int dolocila_count = (int)Math.pow(2, vars.length);
		boolean[][] dolocila = new boolean[dolocila_count][vars.length+1];
		for (int i = 0; i < dolocila_count; i++) {
			dolocila[i][0] = false;
			for (int j = 0; j < vars.length; j++) {
				dolocila[i][vars.length-j] = getBit(i, j); // j-th bit of i
			}
		}

		for (int i = 0; i < vars.length; i++) {
			System.out.print(" "+vars[i]+" |");
		}
		System.out.println(" "+exp.toStr());
		System.out.println("------------------");
		for (int i = 0; i < dolocila_count; i++) {
			System.out.print(" ");
			for (int j = 1; j <= vars.length; j++) {
				System.out.print((dolocila[i][j] ? "1":"0")+" | ");
			}
			System.out.println(exp.eval(dolocila[i]) ? "1":"0");
		}
	}

	private static boolean getBit(int n, int i) {
		// returns i-th bit of n as a bool
		return ((n / (int)Math.pow(2, i)) % 2) == 1;
	}
}
