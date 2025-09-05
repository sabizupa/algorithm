import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1247 {
	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static Point[] points;
	static int min;
	static boolean visited[];
	static int T;
	static int N;

	static void perm(int pos, int cost, int depth) {
		if (cost > min) {
			return;
		}
		if (depth == N) {
			int nextCost = cost + Math.abs(points[pos].x - points[N + 1].x) + Math.abs(points[pos].y - points[N + 1].y);
			min = Math.min(min, nextCost);
			return;
		}
		for (int i = 1; i <= N; i++) {
			int nextPos = i;
			if (!visited[i]) {
				visited[i] = true;
				int nextCost = cost + Math.abs(points[pos].x - points[nextPos].x)
						+ Math.abs(points[pos].y - points[nextPos].y);
				perm(nextPos, nextCost, depth + 1);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N =Integer.parseInt(st.nextToken());
			visited = new boolean[N+1];
			points = new Point[N+2];
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			int companyX = Integer.parseInt(st.nextToken());
			int companyY = Integer.parseInt(st.nextToken());
			int homeX = Integer.parseInt(st.nextToken());
			int homeY = Integer.parseInt(st.nextToken());
			points[0] = new Point(companyX, companyY);
			points[N+1] = new Point(homeX, homeY);
			
			for(int i=1; i<=N; i++) {
				int customerX = Integer.parseInt(st.nextToken());
				int customerY = Integer.parseInt(st.nextToken());
				points[i] = new Point(customerX, customerY);
			}
			
			perm(0, 0, 0);
			System.out.println("#"+tc+" "+min);
		}

	}
}
