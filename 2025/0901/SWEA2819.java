import java.util.*;
import java.io.*;

public class SWEA2819 {
	static int T;
	static String[][] map;
	static HashSet<String> set;
	static int[] dx = { 0, 1, 0, -1 }; // 북동남서
	static int[] dy = { -1, 0, 1, 0 };

	static class Pack {
		int x;
		int y;
		String str;

		Pack(int x, int y, String str) {
			this.x = x;
			this.y = y;
			this.str = str;
		}
	}

	public static void bfs(int startX, int startY) {
		Deque<Pack> q = new ArrayDeque<>();
		q.offer(new Pack(startX, startY, map[startY][startX]));

		while (!q.isEmpty()) {
			Pack p = q.poll();
			int x = p.x;
			int y = p.y;
			String str = p.str;

			if (str.length() == 7) {
				set.add(str);
				continue;
			}


			for (int i = 0; i < 4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				if (nextX < 0 || nextX >= 4 || nextY < 0 || nextY >= 4) {
					continue;
				}
				q.offer(new Pack(nextX, nextY, str+map[nextY][nextX]));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			map = new String[4][4];
			set = new HashSet<String>();
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					map[i][j] = st.nextToken();
				}
			}

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					bfs(j, i);
				}
			}
			System.out.println("#" + tc + " " + set.size());
		}

	}
}