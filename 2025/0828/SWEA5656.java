import java.io.*;
import java.util.*;

// 참고: https://blackvill.tistory.com/21
public class SWEA5656 {
	static int N; // 공r갯수
	static int W; // 가로
	static int H; // 세로
	static int T;
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { -1, 0, 1, 0 };
	static int map[][];
	static int map2[][];
	static int choice[]; // n번째 선택에서 선택한 열
	static int min;

	
	public static void perm(int count) {
		if (count == N) {
			start();
			min = Math.min(min, getCount());
			
			mapReset();
			return;
		}
		for (int i = 0; i < W; i++) {
			choice[count] = i;
			perm(count + 1);
		}

	}

	public static void start() {
		int x = 0;
		int y = 0;
		for (int i = 0; i < N; i++) {
			int col = choice[i];
			for (int row = 0; row < H; row++) {
				if (map2[row][col] != 0) {
					x = col;
					y = row;
					break;
				}
			}
			boom(x, y);
			gravity();
			
		}
	}

	public static void boom(int startX, int startY) {
		// 폭발시킬 x,y,벽돌에쓰인숫자
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { startX, startY, map2[startY][startX] });
		map2[startY][startX] = 0;

		while (!q.isEmpty()) {
			int[] pos = q.poll();
			int x = pos[0];
			int y = pos[1];
			int bandLimit = pos[2];
			for (int band = 1; band < bandLimit; band++) {
				for (int i = 0; i < 4; i++) {
					int nextX = x + dx[i] * band;
					int nextY = y + dy[i] * band;
					if (nextX < 0 || nextX >= W || nextY < 0 || nextY >= H || map2[nextY][nextX] == 0) {
						continue;
					}
					
					q.offer(new int[] { nextX, nextY, map2[nextY][nextX] });
					map2[nextY][nextX] = 0;
				}
			}
		}
	}

	public static void gravity() {
		Queue<Integer> q = new ArrayDeque<>();

		for (int col = 0; col < W; col++) {
			for (int row = H - 1; row >= 0; row--) {
				if (map2[row][col] != 0) {
					q.offer(map2[row][col]);
				}
			}
			for (int row = H - 1; row >= 0; row--) {
				if (!q.isEmpty()) {
					map2[row][col] = q.poll();
				} else {
					map2[row][col] = 0;
				}
			}
		}
	}

	public static int getCount() {
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map2[i][j] != 0) {
					count++;
				}
			}
		}
		return count;
	}

	public static void mapReset() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map2[i][j] = map[i][j];
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
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			map2 = new int[H][W];
			choice = new int[N];
			min = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = map2[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			perm(0);
			System.out.println("#"+tc+" "+min);

		}
	}
}
