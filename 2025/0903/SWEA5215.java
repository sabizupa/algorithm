import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5215{
	// N개의 재료
	// 제한 칼로리 L
	// 값어치
	// dp[N+1][L+1] = 값
	static int T;
	static int N;
	static int L;
	static int cal[];
	static int value[];
	static int dp[][];

	public static void solve() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= L; j++) {
				int case1 = dp[i - 1][j];
				int case2 = 0;
				if (j - cal[i] >= 0) {
					case2 = dp[i - 1][j - cal[i]] + value[i];
				}
				dp[i][j] = Math.max(case1, case2);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			cal = new int[N + 1];
			value = new int[N + 1];
			dp = new int[N + 1][L + 1];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				value[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}

			solve();
			System.out.println("#" + tc + " " + dp[N][L]);

		}

	}
}
