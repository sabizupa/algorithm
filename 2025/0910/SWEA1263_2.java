import java.util.*;
import java.io.*;

public class SWEA1263_2 {
	static final int INF = 2000000;
	static int T;
	static int N;
	static int[][] distance;

	static void fw() {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (distance[i][k] != INF && distance[k][j] != INF) {
						distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
					}
				}
			}
			// if(distance[k][k]<0) {//??}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			distance = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num == 0) {
						num = INF;
					}
					distance[i][j] = num;
				}
				distance[i][i] = 0;
			}

			fw();
			
			int min = INF;
			for (int i = 0; i < N; i++) {
				int localMin = 0;
				for (int j = 0; j < N; j++) {
					if (i == j) {
						continue;
					}
					localMin += distance[i][j];
				}
				min = Math.min(min, localMin);
			}
			
			System.out.println("#" + tc + " " + min);
		}
	}
}
