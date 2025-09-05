import java.util.*;
import java.io.*;

public class SWEA1767 {
	static int[][] map;
	static List<Core> cores;
	static boolean visited[];
	static int T;
	static int N;
	static int K;
	static int min;
	static int maxCore;
	static int dx[] = { 0, 1, 0, -1 }; // 북동남서
	static int dy[] = { -1, 0, 1, 0 };
	static int trans[] = { 2, 3, 0, 1 };

	static class Core {
		int x;
		int y;

		Core(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void map() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("----");
	}

	public static void choose(int depth, int coreCount, int length) {

		if (coreCount + K - depth < maxCore) {
			return;
		}

		if (depth == K) {
			if (coreCount > maxCore) {
				maxCore = coreCount;
				min = length;

			} else {
				maxCore = Math.max(maxCore, coreCount);
				min = Math.min(min, length);

			}
			return;

		}

		choose(depth + 1, coreCount, length);

		if (visited[depth] == true) {
			return;
		}
		Core core = cores.get(depth);
		int x = core.x;
		int y = core.y;
		// map();
		// 4방향 전선검사
		for (int j = 0; j < 4; j++) {
			// 검사
			boolean canGo = true;
			int nextX = x;
			int nextY = y;
			while (true) {
				nextX += dx[j];
				nextY += dy[j];
				if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
					break;
				}
				if (map[nextY][nextX] != 0) {
					canGo = false;
					break;
				}
			}
			// 갈수있으면 전선깐다
			if (canGo) {
				nextX = x;
				nextY = y;
				while (true) {
					nextX += dx[j];
					nextY += dy[j];
					if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
						int tempLength = Math.abs(x - nextX) + Math.abs(y - nextY) - 1;
						choose(depth + 1, coreCount + 1, length + tempLength);
						break;
					}
					map[nextY][nextX] = 2;
				}
				// 복구
				nextX = x;
				nextY = y;
				while (true) {
					nextX += dx[j];
					nextY += dy[j];
					if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
						break;
					}
					map[nextY][nextX] = 0;
				}
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
			min = Integer.MAX_VALUE;
			maxCore = 0;
			map = new int[N][N];

			cores = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int now = Integer.parseInt(st.nextToken());
					map[i][j] = now;
					if (now == 1) {
						cores.add(new Core(j, i));
					}
				}
			}
			K = cores.size();
			visited = new boolean[K];
			int count = -1;
			int coreCount = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					if (map[i][j] == 1) {
						count++;
						if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
							visited[count] = true;
							coreCount++;
						}
					}

				}
			}
			choose(0, coreCount, 0);
			System.out.println("#" + tc + " " + min);
		}
		//
	}
}
