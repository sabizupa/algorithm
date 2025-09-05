import java.io.*;
import java.util.*;

public class BOJ17471 {
	static int selected[];
	static int N; // 구역
	static int people[]; // 구역별 인구수
	static List<List<Integer>> graph;
	static int min;

	static void select(int depth) {
		//
		if (depth == N + 1) {
			int someParty1Node = -1;
			int someParty2Node = -1;

			for (int i = 1; i <= N; i++) {
				if (selected[i] == 1 && someParty1Node == -1) {
					someParty1Node = i;
				}
				if (selected[i] == 2 && someParty2Node == -1) {
					someParty2Node = i;
				}
			}

			// 적어도 1개 선택되었는지 확인
			if (someParty1Node == -1 || someParty2Node == -1) {
				return;
			}
			// 연결되었는지 확인
			int party1Count = bfs(someParty1Node, 1);
			int party2Count = bfs(someParty2Node, 2);
			if (party1Count + party2Count != N) {
				return;
			}

			int party1People = 0;
			int party2People = 0;
			for (int i = 1; i <= N; i++) {	
				if (selected[i] == 1) {
					party1People += people[i];
				} else if (selected[i] == 2) {
					party2People += people[i];
				}
			}
			min = Math.min(min, Math.abs(party1People - party2People));
			return;
		}
		///
		selected[depth] = 1;
		select(depth + 1);
		selected[depth] = 2;
		select(depth + 1);

	}

	// startNode에서 시작해 party당선된 선거구를 순회, 구역내선거구몇갠지
	static int bfs(int startNode, int party) {
		boolean visited[] = new boolean[N + 1];
		int count = 0;

		Deque<Integer> q = new ArrayDeque<>();
		q.offer(startNode);
		visited[startNode] = true;
		count++;
		while (!q.isEmpty()) {
			int pos = q.poll();
			for (int nextPos : graph.get(pos)) {
				if (visited[nextPos] || selected[nextPos] != party) {
					continue;
				}
				visited[nextPos] = true;
				count++;
				q.offer(nextPos);
			}
		}
		return count;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		selected = new int[N + 1];
		people = new int[N + 1];
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		min = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int nearCount = Integer.parseInt(st.nextToken());
			for (int j = 0; j < nearCount; j++) {
				int near = Integer.parseInt(st.nextToken());
				graph.get(i).add(near);
				graph.get(near).add(i);
			}
		}
		select(1);
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);

	}
}
