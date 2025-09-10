import java.io.*;
import java.util.*;

public class BOJ1613 {
	static int n;
	static int k;
	static int distance[][]; // a->b

	public static void fw() {
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
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
		n = Integer.parseInt(st.nextToken()); // 사건
		k = Integer.parseInt(st.nextToken()); // 사건의 개수
		distance = new int[n + 1][n + 1];

		for (int i = 0; i <= n; i++) {
			distance[i][i] = 1;
		}

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			distance[a][b] = 1;
		}

		fw();
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		for (int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (distance[a][b] == 1) {
				System.out.println(-1);
			} else if (distance[b][a] == 1) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}

		}

	}
}
