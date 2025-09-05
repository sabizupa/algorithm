import java.io.*;
import java.util.*;

public class BOJ13023 {
	static boolean visited[];
	static ArrayList<Integer>[] graph;
	static int exist;

	public static void bfs(int pos, int depth) {
		if (depth == 5) {
			exist = 1;
			return;
		}
		for (int i = 0; i < graph[pos].size(); i++) {
			int nextNode = graph[pos].get(i);
			if (!visited[nextNode]) {
				visited[nextNode] = true;
				bfs(nextNode, depth + 1);
				visited[nextNode] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 사람수
		int M = Integer.parseInt(st.nextToken()); // 에지수
		visited = new boolean[N];
		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		for (int i = 0; i < N; i++) {
			visited[i] = true;
			bfs(i, 1);
			Arrays.fill(visited, false);
			if (exist == 1) {
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);

	}
}
