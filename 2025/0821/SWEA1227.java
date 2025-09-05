import java.io.*;
import java.util.*;

public class SWEA1227 {
	
	static int N = 100;
	static boolean visited[][];
	static int map[][];
	static int dx[] = {0, 1, 0, -1}; //북동남서
	static int dy[] = {-1, 0, 1, 0};
	
	public static void bfs(int startX, int startY) {
		Deque<Integer> q = new ArrayDeque<>();
		q.offer(startX);
		q.offer(startY);
		visited[startY][startX] = true;
		
		while(!q.isEmpty()) {
			int posX = q.poll();
			int posY = q.poll();
			
			for(int i=0; i<4; i++) {
				int nextX = posX+dx[i];
				int nextY = posY+dy[i];
				if(nextX<0||nextX>=N||nextY<0||nextY>=N||map[nextY][nextX]==1||visited[nextY][nextX]==true) {
					continue;
				}
				
				visited[nextY][nextX]=true;
				q.offer(nextX);
				q.offer(nextY);
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int tc=1; tc<=10; tc++) {
			st = new StringTokenizer(br.readLine());
			map=new int[N][N];
			visited=new boolean[N][N];
			int startX = -1;
			int startY= -1;
			int endX = -1;
			int endY = -1;

			for(int i=0;i<100;i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				for(int j=0; j<100; j++) {
					//System.out.println(i+" "+j);
					int a = s.charAt(j)-'0';
					map[i][j] = a;
					if(a==2) {
						startX = j;
						startY = i;
					}
					else if(a==3) {
						endX = j;
						endY = i;
					}
				}
			}
			
			bfs(startX, startY);
			int result = 0;
			if(visited[endY][endX]==true) {
				result = 1;
			}
			System.out.println("#"+tc+" "+result);
			
		}
		
	}
}
