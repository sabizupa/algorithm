import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17069 {

	static long dp[][][];
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		dp = new long[3][N][N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0][1] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j - 1 >= 0 && map[i][j] != 1) {
					dp[0][i][j] += dp[0][i][j - 1] + dp[1][i][j - 1];
				}

				if (i - 1 >= 0 && j - 1 >= 0 && map[i][j] != 1 && map[i - 1][j] != 1 && map[i][j - 1] != 1) {
					dp[1][i][j] += dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1];
				}

				if (i - 1 >= 0 && map[i][j] != 1) {
					dp[2][i][j] += dp[2][i - 1][j] + dp[1][i - 1][j];
				}
			}
		}
		
		System.out.println(dp[0][N-1][N-1]+dp[1][N-1][N-1]+dp[2][N-1][N-1]);
	}
}
