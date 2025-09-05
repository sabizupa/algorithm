import java.util.*;
import java.io.*;

public class SWEA1949 {
	static int T;
	static int N;
	static int K;
	static int max;
	static boolean visited[][];
	static int map[][];
	static int dx[] = { 0, 1, 0, -1 }; // 북동남서
	static int dy[] = { -1, 0, 1, 0 };

	static void bfs(int x, int y, boolean hasPickaxe, int length) {
		max = Math.max(max, length);

		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];

			if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || visited[nextY][nextX]) {
				continue;
			}

			if (map[nextY][nextX] < map[y][x]) {
				visited[nextY][nextX] = true;
				bfs(nextX, nextY, hasPickaxe, length + 1);
				visited[nextY][nextX] = false;
			} else if (hasPickaxe && map[nextY][nextX] - K < map[y][x]) {
				visited[nextY][nextX] = true;
				int temp = map[nextY][nextX];
				map[nextY][nextX] = map[y][x] - 1;
				bfs(nextX, nextY, false, length + 1);
				visited[nextY][nextX] = false;
				map[nextY][nextX] = temp;
			}
		}
		// 갈수있음->visited, 감, visited해제
		// 갈수없음-> 깎아서 갈수있는경우->visited, 가는데  hasPickaxe false상태로, visited해제

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			max = -1;
			visited = new boolean[N][N];
			map = new int[N][N];

			int startNum = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
					if (num > startNum) {
						startNum = num;
					}
				}
			}
			List<int[]> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == startNum) {
						list.add(new int[] {j, i});
					}
				}
			}

			for (int i = 0; i < list.size(); i++) {
				int[] s = list.get(i);
				int x = s[0];
				int y = s[1];
				visited[y][x] = true;
				bfs(x, y, true, 1);
				visited[y][x] = false;
			}
			
			System.out.println("#"+tc+" "+max);
		}
	}
}
