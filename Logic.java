import java.io.IOException;
import java.util.Scanner;

public class Logic {
	public static void main(String[] args) {
		System.out.print("Enter logical expression to evaluate: ");
		Scanner scanner = new Scanner(System.in);
		String expression = scanner.nextLine();
		Parser parser = new Parser(expression);
		drawTruthTable(parser.primitives, parser.primitiveCount, parser.exp);
	}

	private static void drawTruthTable(String[] vars, int varCount, Expression exp) {
		int dolocilaCount = (int)Math.pow(2, varCount);
		boolean[][] dolocila = new boolean[dolocilaCount][varCount];
		for (int i = 0; i < dolocilaCount; i++) {
			for (int j = 0; j < varCount; j++) {
				dolocila[i][j] = getBit(i, (varCount-1)-j); // j-th bit of i
			}
		}

		for (int i = 0; i < varCount; i++) {
			System.out.print(" "+vars[i]+" |");
		}
		System.out.println(" "+exp.toStr());
		for (int i = 0; i < varCount*4+exp.toStr().length()+1; i++)
			System.out.print("-");
		System.out.println();
		for (int i = 0; i < dolocilaCount; i++) {
			System.out.print(" ");
			for (int j = 0; j < varCount; j++) {
				System.out.print((dolocila[i][j] ? "1":"0")+" | ");
			}
			System.out.println(exp.eval(dolocila[i]) ? "1":"0");
		}
	}

	private static boolean getBit(int n, int i) {
		// returns i-th bit of n as a bool
		return ((n >> i) % 2) == 1;
	}
}
