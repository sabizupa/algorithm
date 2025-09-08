import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7579 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N + 1][10001];
		int[] memory = new int[N + 1];
		int[] cost = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= 10000; j++) {
				int case1 = dp[i - 1][j];
				int case2 = 0;
				if (j - cost[i] >= 0) {
					case2 = dp[i - 1][j - cost[i]] + memory[i];
				}
				dp[i][j] = Math.max(case1, case2);
			}
		}

		for (int i = 0; i <= 10000; i++) {
			if (dp[N][i] >= M) {
				System.out.println(i);
				return;
			}
		}

	}
}
