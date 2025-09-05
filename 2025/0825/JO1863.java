import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO1863 {
	static int parent[];
	static int count;

	public static int findParent(int n) {
		if (parent[n] == n) {
			return n;
		}
		return parent[n] = findParent(parent[n]);
	}

	public static void union(int n1, int n2) {
		int p1 = findParent(n1);
		int p2 = findParent(n2);
		if (p1 != p2) {
			parent[p2] = p1;
			count--;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 사람수
		int m = Integer.parseInt(st.nextToken()); // 에지수
		count = n;
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a,b);
		}
		System.out.println(count);
	}
}
