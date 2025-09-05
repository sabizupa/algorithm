import java.io.*;
import java.util.*;

public class SWEA5643 {

	public static ArrayList<ArrayList<Integer>> graph;
	public static ArrayList<ArrayList<Integer>> reversedGraph;
	public static int T;
	public static int N;
	public static int M;
	public static boolean visited[];

	public static int bfs(int start, ArrayList<ArrayList<Integer>> graph) {

		Deque<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = true;
		int result = 0;

		while (!q.isEmpty()) {
			int pos = q.poll();
			for (int nextPos : graph.get(pos)) {
				if (visited[nextPos]) {
					continue;
				}
				visited[nextPos] = true;
				result++;
				q.offer(nextPos);
			}

		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			graph = new ArrayList<>();
			reversedGraph = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			visited = new boolean[N + 1];

			// node 0 처리
			graph.add(new ArrayList<>());
			reversedGraph.add(new ArrayList<>());
			for (int i = 1; i <= N; i++) {
				graph.add(new ArrayList<>());
				reversedGraph.add(new ArrayList<>());
			}

			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph.get(a).add(b);
				reversedGraph.get(b).add(a);
			}
			int result = 0;

			for (int i = 1; i <= N; i++) {
				int in = bfs(i, graph);
				Arrays.fill(visited, false);
				int out = bfs(i, reversedGraph);
				Arrays.fill(visited, false);
				int canKnow = in + out;
				if (in + out == N - 1) {
					result++;
				}

			}
			System.out.println("#" + tc + " " + result);
		}

		// 순방향그래프 : 자기보다 큰 학생 찾을때.
		// 역방향 그래프: 자기보다 작은 학생 찾을때.
		// 순방향 bfs 돌려서 방문한 노드 수 찾고. visited 초기화
		// 역방향 bfs 돌려서 방문한 노드 수 찾고. visited 초기화
		// 1노드수+2노드수=학생수-1이면 키를 알 수 있다?

	}
}
