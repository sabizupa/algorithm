import java.io.*;
import java.util.*;

public class SWEA1267 {
	static int E;
	static int V;
	static int incoming[];
	static ArrayList<Integer> result;
	static ArrayList<ArrayList<Integer>> graph;

	public static void sort() {
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= V; i++) {
			if (incoming[i] == 0) {
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {
			int pos = q.poll();
			result.add(pos);
			for (int nextPos : graph.get(pos)) {
				incoming[nextPos]--;
				if (incoming[nextPos] == 0) {
					q.offer(nextPos);
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int tc = 1; tc <= 10; tc++) {

			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			graph = new ArrayList<>();
			graph.add(new ArrayList<>()); // node 0
			for (int i = 0; i < V; i++) {
				graph.add(new ArrayList<>());
			}
			incoming = new int[V + 1];
			result = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());
				graph.get(node1).add(node2);
				incoming[node2]++;
			}
			sort();
			if (result.size() != V) {
				return;
			} else {
				System.out.print("#"+tc+" ");
				for(int i=0; i<result.size(); i++) {
					System.out.print(result.get(i)+" ");
				}
				System.out.println();
				
			}

		}

	}
}
