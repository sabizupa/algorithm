import java.io.*;
import java.util.*;

public class SWEA7465 {
	static int parent[];
	static int count;
	static int T;
	static int N;
	static int M;

	static int findParent(int n) {
		if (parent[n] == n) {
			return n;
		}
		return parent[n] = findParent(parent[n]);
	}

	static void union(int a, int b) {
		int p1 = findParent(a);
		int p2 = findParent(b);

		if (p1 != p2) {
			parent[p2] = p1;
			count--;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parent = new int[N + 1];
			for (int i = 0; i <= N; i++) {
				parent[i] = i;
			}

			count = N;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
			System.out.println("#" + tc + " " + count);
		}

	}
}
