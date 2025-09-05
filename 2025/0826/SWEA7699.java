import java.io.*;
import java.util.*;

public class SWEA7699 {
	static int max;
	static char map[][];
	static int[] dx = { 0, 1, 0, -1 };// 북동남서
	static int[] dy = { -1, 0, 1, 0 };
	static int R; // 세로
	static int C;// 가로
	static int T;
	
	public static void dfs() {
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(1);
		stack.push(0);
		stack.push(0);
		stack.push(1 << (map[0][0] - 'A'));
		
		while (!stack.isEmpty()) {
			int flag = stack.pop();
			int x = stack.pop();
			int y = stack.pop();
			int count = stack.pop();
			max = Math.max(count, max);
			//System.out.println(y+' '+x);
			for (int i = 0; i < 4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				if (nextX < 0 || nextX >= C || nextY < 0 || nextY >= R) {
					continue;
				}
				if ((flag & (1 << (map[nextY][nextX]-'A'))) != 0) {
					continue;
				}
				
				stack.push(count+1);
				stack.push(nextY);
				stack.push(nextX);
				stack.push(flag | (1 << (map[nextY][nextX]-'A')));
				
				
				
			}

		}

	}

	public static void main(String[] args)  throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			max = Integer.MIN_VALUE;
			for(int i=0; i<R; i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				for(int j=0; j<C; j++) {
					map[i][j] = s.charAt(j);
				}
			}
			dfs();
			System.out.println("#"+tc+" "+max);
		}
	}
}
