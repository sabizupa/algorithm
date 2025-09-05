import java.util.Arrays;
import java.util.Scanner;

public class SWEA2806 {
	static int N;
	static int[] candidates;
	static int result;
    // 참고함: https://wikidocs.net/206359
	public static void dfs(int row) {
		if (row == N) {
			result++;
			return;
		}

		for (int i = 0; i < N; i++) {
			candidates[row] = i;
			if (isAvailable(row)) {
				dfs(row + 1);
			}
		}
	}

	public static boolean isAvailable(int row) {
		for (int i = 0; i < row; i++) {
			if (candidates[row] == candidates[i] ||Math.abs(candidates[row] - candidates[i]) ==  row - i) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			result = 0;
			N = sc.nextInt();
			candidates = new int[N];
			dfs(0);
			System.out.println("#"+tc+" "+result);
		}

	}

}
