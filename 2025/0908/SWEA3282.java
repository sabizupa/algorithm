import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3282 {
	static int T;
	static int N; // 몇가지
	static int K; // 제한부피
	static int v[];
	static int c[];
	static int dp[][]; // dp[몇가지][제한부피] = 최대가치
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			dp = new int[N+1][K+1];
			v = new int[N+1];
			c = new int[N+1];
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				v[i] = Integer.parseInt(st.nextToken());
				c[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=K; j++) {
					int case1 = dp[i-1][j];
					int case2 = 0;
					if(j - v[i] >= 0) {
						case2 = dp[i-1][j-v[i]] + c[i];
					}
					dp[i][j] = Math.max(case1, case2);
				}
			}
			
			System.out.println("#"+tc+" "+dp[N][K]);
		}
	}
}
