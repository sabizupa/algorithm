 import java.io.*;
import java.util.*;

public class SWEA1868 {
	// 숫자표시된맵 만들기
	// 숫자표시된맵 전체 탐색 -> 0 찾기-> 클릭해서 연쇄
	// 남은거 세기

	static char[][] map; // 빈곳 ., 지뢰 *
	static int[][] map2; // 주변지뢰수
	static boolean[][] visited;
	static int clickCount;
	static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1}; // 북서 북 북동 동 남동 남 남서 서
	static int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int N;
	static int T;
	
	public static class Pos{
		int x = -1;
		int y = -1;
	}
	public static void click(int startX, int startY) {
		Deque<Pos> queue = new ArrayDeque<>();
		Pos startPos = new Pos();
		startPos.x = startX;
		startPos.y = startY;
		queue.offer(startPos);
		
		while(!queue.isEmpty()) {
			Pos pos = queue.poll();
			if(pos.x<0 || pos.x>=N || pos.y<0 || pos.y>=N) { continue; }
			if(map[pos.y][pos.x] == '*' || visited[pos.y][pos.x] == true) {continue;}
			
			if(map2[pos.y][pos.x] >=1) {
				visited[pos.y][pos.x] = true;
				continue;
			}
			
			visited[pos.y][pos.x] = true;
			for(int i=0; i<8; i++) {
				int nextX = pos.x+dx[i];
				int nextY = pos.y+dy[i];
				Pos nextPos = new Pos();
				nextPos.x = nextX;
				nextPos.y = nextY;
				queue.offer(nextPos);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<T+1; tc++) {
			clickCount = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			visited = new boolean[N][N];
			map = new char[N][N];
			map2 = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				String row = st.nextToken();
				for(int j=0; j<N; j++) {
					map[i][j] = row.charAt(j);
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int bombCount = 0;
					for(int k=0; k<8; k++) {
						int aroundX = j + dx[k];
						int aroundY = i + dy[k];
						if(aroundX<0 || aroundX>=N || aroundY<0 || aroundY>= N) { continue; }
						if(map[aroundY][aroundX] == '*') { bombCount++; }
					}
					map2[i][j] = bombCount;
				}
			}
			

			// 0 클릭
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map2[i][j] != 0 || visited[i][j] == true || map[i][j] == '*') { continue; }
					else {
						click(j,i);
						clickCount++;
						//System.out.println(i+" "+j);
					}
				}
			}
			// 그리고 안한거
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
						if(visited[i][j]==false && map[i][j]=='.') {
							visited[i][j]=true;
							clickCount++;
						}
				}
			}
			
			
			System.out.println("#"+tc+" "+clickCount);
			
		}
		
		
		
		

	}

}
