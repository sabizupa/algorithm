import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA2105 {
	static int N;
	static boolean[] visited;
	static int[][] map;
	static int[] dx = {1, -1, -1, 1}; // ³²µ¿, ³²¼­, ºÏ¼­, ºÏµ¿
	static int[] dy = {1, 1, -1, -1};
	static int max;
	static int startX;
	static int startY;

	public static void dfs(int x, int y, int dir, int sum) {

		
		for(int i=dir; i<4; i++) {
			
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N){
				continue;
			}
			else if(nextX==startX && nextY==startY) {
				if(sum==2) {return;}
				max = Math.max(max, sum);
				return;
			}
			else if(visited[map[nextY][nextX]] == true) {
				continue;
			} 
			
			visited[map[nextY][nextX]] = true;
			dfs(nextX, nextY, i, sum+1);
			visited[map[nextY][nextX]] = false;
			
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			max = Integer.MIN_VALUE;
			visited = new boolean[101];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i <= N - 3; i++) {
				for (int j = 1; j <= N - 2; j++) {
					startX = j;
					startY = i;
					visited[map[i][j]] = true;
					dfs(j, i, 0, 1);
					visited[map[i][j]] = false;
				}
			}
			
			if (max == Integer.MIN_VALUE) {
				max = -1;
			}
			System.out.println("#" + tc + " " + max);
		}

	}
}
