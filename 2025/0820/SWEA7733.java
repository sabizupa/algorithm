import java.io.*;
import java.util.*;

public class SWEA7733 {
	static int T;
	static int N;
	static int[][] map;
	static boolean visited[][];
	static boolean visited2[][];
	static int[] dx = { 0, 1, 0, -1 }; // 북동남서
	static int[] dy = { -1, 0, 1, 0 };

	public static void bfs(int startX, int startY) {
		Deque<Integer> q = new ArrayDeque<>();
		q.offer(startX);
		q.offer(startY);
		visited2[startY][startX] = true;
		while (!q.isEmpty()) {
			int posX = q.poll();
			int posY = q.poll();
			for (int i = 0; i < 4; i++) {
				int nextX = posX + dx[i];
				int nextY = posY + dy[i];
				if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || visited2[nextY][nextX]==true) {
					continue;
				}
				visited2[nextY][nextX] = true;
				q.offer(nextX);
				q.offer(nextY);
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
			visited = new boolean[N][N];
			visited2 = new boolean[N][];
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int result = -1;
			for (int day = 0; day <= 100; day++) {
				int sum = 0;
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j] == day) {
							visited[i][j] = true;
						}
					}
				}
				for (int i = 0; i < N; i++) {
					visited2[i]=visited[i].clone();
				}

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (!visited2[i][j]) {
							sum++;
							bfs(j, i);
						}
					}
				}
				result = Math.max(result, sum);
			}
			System.out.println("#"+tc+" "+result);
		}
	}
}
