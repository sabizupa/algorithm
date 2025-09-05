import java.io.*;
import java.util.*;

public class SWEA1249 {
	static int T;
	static int N;
	static int[] dx = {0, 1, 0, -1}; //북동남서
	static int[] dy = {-1, 0, 1, 0};
	static int map[][];
	static int map2[][]; // 복구비용 
	
	public static void bfs() {
		map2[0][0] = 0;
		
		Deque<Integer> q = new ArrayDeque<>();
		q.offer(0);
		q.offer(0);
		q.offer(0);
		
		while(!q.isEmpty()) {
			int x= q.poll();
			int y = q.poll();
			int cost = q.poll();
			
			for(int i=0; i<4; i++) {
				int nextX = x + dx[i];
				int nextY=  y + dy[i];
				if(nextX<0||nextX>=N||nextY<0||nextY>=N) {
					continue;
				}
				int nextCost = cost + map[nextY][nextX];
				if(nextCost >= map2[nextY][nextX]) {
					continue;
				}
				map2[nextY][nextX] = nextCost;
				q.offer(nextX);
				q.offer(nextY);
				q.offer(nextCost);
				//System.out.println("y x cost:"+nextY+" "+nextX+" "+cost);
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			map2 = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				for(int j=0; j<N; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}
			for(int i=0;i<N;i++) {
				Arrays.fill(map2[i], Integer.MAX_VALUE);
			}
			bfs();
			System.out.println("#"+tc+" "+map2[N-1][N-1]);
			
			
		}
		
		
	}
}
