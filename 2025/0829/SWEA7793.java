import java.io.*;
import java.util.*;

public class SWEA7793 {
	static char map[][];
	static int cost[][];
	static int time;
	static int dx[] = { 0, 1, 0, -1 }; // 북동남서
	static int dy[] = { -1, 0, 1, 0 };
	static int N; // 행
	static int M; // 열
	static List<int[]> demonList;
	static List<int[]> tempList;
	static int T;
	static int startX;
	static int startY;
	static int goddessX;
	static int goddessY;

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("--------------");
	}

	static void bfs(int startX, int startY) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { startX, startY, 0 });

		while (!q.isEmpty()) {

			demon();
			//print();
			while (!q.isEmpty()) {
				int[] s = q.poll();
				int x = s[0];
				int y = s[1];
				int stime = s[2];

				// 한바퀴 돌았다
				if (stime != time) {
					q.offer(s);
					break;
				}

				// 아직 더 돌아야 함
				for (int i = 0; i < 4; i++) {
					int nextX = x + dx[i];
					int nextY = y + dy[i];
					if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N) {
						continue;
					}
					if (map[nextY][nextX] == 'X' || map[nextY][nextX] == '*') {
						continue;
					}
					if (cost[nextY][nextX] <= cost[y][x] + 1) {
						continue;
					}
					cost[nextY][nextX] = cost[y][x] + 1;
					q.offer(new int[] { nextX, nextY, stime + 1 });
				}
			}
			time++;
		}

	}

	static void demon() {
		for (int i = 0; i < demonList.size(); i++) {
			int[] demon = demonList.remove(i);
			for (int j = 0; j < 4; j++) {
				int nextX = demon[0] + dx[j];
				int nextY = demon[1] + dy[j];
				if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N) {
					continue;
				}
				if (map[nextY][nextX] == 'X' || map[nextY][nextX] == '*' || map[nextY][nextX] == 'D') {
					continue;
				}
				map[nextY][nextX] = '*';
				tempList.add(new int[] { nextX, nextY });
			}
			i--;
		}
		List<int[]> t = demonList;
		demonList.clear();
		demonList = tempList;
		tempList = t;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			time = 0;
			map = new char[N][M];
			cost = new int[N][M];
			for (int i = 0; i < N; i++) {
				Arrays.fill(cost[i], Integer.MAX_VALUE);
			}
			demonList = new ArrayList<>();
			tempList = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				for (int j = 0; j < M; j++) {
					char c = s.charAt(j);
					if (c == 'S') {
						startX = j;
						startY = i;
						cost[startY][startX] = 0;
						map[i][j] = '.';
						continue;
					} else if (c == '*') {
						demonList.add(new int[] { j, i });
					} else if (c == 'D') {
						goddessX = j;
						goddessY = i;
					}
					map[i][j] = c;
				}
			}
			bfs(startX, startY);
			if (cost[goddessY][goddessX] == Integer.MAX_VALUE) {
				System.out.println("#" + tc + " GAME OVER");
			} else {
				System.out.println("#" + tc + " "+cost[goddessY][goddessX]);
			}
		}
	}

}
