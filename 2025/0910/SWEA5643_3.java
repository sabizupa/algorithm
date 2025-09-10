import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA5643_3 {
	static final int INF = Integer.MAX_VALUE;
	static int T;
	static int N; // 전체학생
	static int M; // 에지
	static int distance[][];

	static void fw() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (distance[i][k] != 0 && distance[k][j] != 0) {
						distance[i][j] = 1;
					}
				}
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
			distance = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				distance[i][i] = 1;
			}
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				distance[a][b] = 1;
			}

			fw();
			int result = 0;
			for (int i = 1; i <= N; i++) {
				int num = 0;
				for (int j = 1; j <= N; j++) {
					if (distance[i][j] == 1) {
						num++;
					}
					if(distance[j][i]==1) {
						num++;
					}
				}
				if(num == N+1) {
					result++;
				}
			}

			System.out.println("#"+tc+" "+result);

		}

	}
}
