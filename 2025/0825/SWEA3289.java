import java.util.*;
import java.io.*;

public class SWEA3289 {
	static int parents[];

	public static int findParent(int node) {
		if (parents[node] == node) {
			return node;
		}
		return parents[node] = findParent(parents[node]);
	}

	public static void union(int node1, int node2) {
		int p1 = findParent(node1);
		int p2 = findParent(node2);
		if (p1 != p2) {
			parents[p2] = p1;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			System.out.print("#"+tc+" ");
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			parents = new int[n + 1];
			for (int i = 0; i < n + 1; i++) {
				parents[i] = i;
			}
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int type = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (type == 0) {
					union(a, b);
				} else {
					if (findParent(a) == findParent(b)) {
						System.out.print("1");
					} else {
						System.out.print("0");
					}
				}
			}
			System.out.println();
		}
	}

}
