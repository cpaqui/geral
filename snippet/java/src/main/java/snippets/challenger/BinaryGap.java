package snippets.challenger;

public class BinaryGap {

	public static void main(String[] args) {

		// print the larger number between x and y
		Util.log("output: " + solution(51712));
	}

	public static int solution(int N) {
		Util.log("");
		int output = solution(N, 0, 0, 0);
		Util.log("output: " + output);

		return output;

	}

	private static int solution(int N, int max, int current, int countOne) {
		Util.log(String.format("N: %d, max: %d, curr: %d, countOne: %d", N, max, current, countOne));
		if (N == 1) {
			return countOne > 1 ? Math.max(max, current) : 0;
		} else if (N % 2 == 0) {
			return solution(N / 2, max, countOne >= 1 ? ++current : current, N == 2 && countOne >= 1 ? ++countOne : countOne);
		} else {
			return solution(N / 2, Math.max(max, current), 0, ++countOne);
		}
	}

}