import java.util.*;
import java.io.*;

public class SWEA1861{
	static int[][][] visited;
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 }; 
	static int[] dy = { -1, 0, 1, 0 };
	static int T;
	static int N;

	public static class Pack {
		int startRoom;
		int x;
		int y;

		Pack(int startRoom, int x, int y) {
			this.startRoom = startRoom;
			this.x = x;
			this.y = y;
		}
	}

	public static void bfs(int startX, int startY) {
		Deque<Pack> q = new ArrayDeque<>();
		q.offer(new Pack(visited[1][startY][startX], startX, startY));

		while (!q.isEmpty()) {
			Pack pos = q.poll();
			int myStartRoom = pos.startRoom;
			int myCount = visited[0][pos.y][pos.x];

			for (int i = 0; i < 4; i++) {
				int nextX = pos.x + dx[i];
				int nextY = pos.y + dy[i];

				if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
					continue;
				}

				int nextStartRoom = visited[1][nextY][nextX];
				int nextCount = visited[0][nextY][nextX];

				if (map[pos.y][pos.x] + 1 == map[nextY][nextX]) {
					if (myCount + 1 > nextCount) {
						q.offer(new Pack(pos.startRoom, nextX, nextY));
						visited[1][nextY][nextX] = myStartRoom;
						visited[0][nextY][nextX] = myCount + 1;
					} else if (myCount + 1 == nextCount) {
						if (myStartRoom < nextStartRoom) {
							visited[1][nextY][nextX] = myStartRoom;
							q.offer(new Pack(pos.startRoom, nextX, nextY));
						}
					}
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
			map = new int[N][N];
			visited = new int[2][N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
					visited[0][i][j] = 1;
					visited[1][i][j] = num;

				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					bfs(i,j);
				}
			}
			
			int maxCount = Integer.MIN_VALUE;
			int minRoomNumber = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[0][i][j]>maxCount) {
						maxCount = visited[0][i][j];
						minRoomNumber = visited[1][i][j];
					}
					else if(visited[0][i][j]==maxCount) {
						if(visited[1][i][j]<minRoomNumber) {
							maxCount = visited[0][i][j];
							minRoomNumber = visited[1][i][j];
						}
					}
				}
			}
			
			System.out.println("#"+tc+" "+minRoomNumber+" "+maxCount);
		}

	}
}
