import java.io.*;
import java.util.*;

public class SWEA1953 {
	static int N; // 세로
	static int M; // 가로
	static int R, C; // 시작y, 시작x
	static int L; // 소요된시간
	static int dx[][] = { {}, { 0, 0, -1, 1 }, { 0, 0 }, { -1, 1 }, { 0, 1 }, { 0, 1 }, { 0, -1 }, { 0, -1 } }; // 구조물
	static int dy[][] = { {}, { -1, 1, 0, 0 }, { -1, 1 }, { 0, 0 }, { -1, 0 }, { 1, 0 }, { 1, 0 }, { -1, 0 } }; 
	static int[][] map;
	static int count;
	static boolean visited[][];
	
	static boolean canGo(int type, int dir, int nextType) {
		switch(type) {
		case 1:
			switch(dir) {
			case 0: // 상
				if(nextType == 1 || nextType == 2 || nextType == 5 || nextType == 6) {
					return true;
				}
				break;
			case 1: //하
				if(nextType == 1 || nextType == 2 || nextType == 4 || nextType == 7) {
					return true;
				}
				break;
			case 2: //좌
				if(nextType == 1 || nextType == 3 || nextType == 4 || nextType == 5) {
					return true;
				}
				break;
			case 3: //우
				if(nextType == 1 || nextType == 3 || nextType == 6 || nextType == 7) {
					return true;
				}
				break;
			}
			return false;
		case 2:
			switch(dir) {
			case 0: // 상
				if(nextType == 1 || nextType == 2 || nextType == 5 || nextType == 6) {
					return true;
				}
				break;
			case 1:// 하
				if(nextType == 1 || nextType == 2 || nextType == 4 || nextType == 7) {
					return true;
				}
				break;
			}
			return false;
		case 3:
			switch(dir) {
			case 0: // 좌
				if(nextType == 1 || nextType == 3 || nextType == 4 || nextType == 5) {
					return true;
				}
				break;
			case 1: // 우
				if(nextType == 1 || nextType == 3 || nextType == 6 || nextType == 7) {
					return true;
				}
				break;
			}
			return false;
		case 4:
			switch(dir) {
			case 0: //상
				if(nextType == 1 || nextType == 2 || nextType == 5 || nextType == 6) {
					return true;
				}
				break;
			case 1: //우
				if(nextType == 1 || nextType == 3 || nextType == 6 || nextType == 7) {
					return true;
				}
				break;
			}
			return false;
		case 5:
			switch(dir) {
			case 0: // 하
				if(nextType == 1 || nextType == 2 || nextType == 4 || nextType == 7) {
					return true;
				}
				break;
			case 1:// 우
				if(nextType == 1 || nextType == 3 || nextType == 6 || nextType == 7) {
					return true;
				}
				break;
			}
			return false;
		case 6:
			switch(dir) {
			case 0: //하
				if(nextType == 1 || nextType == 2 || nextType == 4 || nextType == 7) {
					return true;
				}
				break;
			case 1://좌
				if(nextType == 1 || nextType == 3 || nextType == 4 || nextType == 5) {
					return true;
				}
				break;
			}
			return false;
		case 7:
			switch(dir) {
			case 0: // 상
				if(nextType == 1 || nextType == 2 || nextType == 5 || nextType == 6) {
					return true;
				}
				break;
			case 1: // 좌
				if(nextType == 1 || nextType == 3 || nextType == 4 || nextType == 5) {
					return true;
				}
				break;
			}
			return false;
		}
		return false;
	}
	
	static void calc(int x, int y) {
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {x,y, 2});
		visited[y][x] = true;
		count = 1;
		for(int i=2; i<=L;i++){
			// 큐에서 남은위치 꺼냄
			while(!q.isEmpty()) {
				int[] pos = q.poll();
				int posX = pos[0];
				int posY = pos[1];
				int type = map[posY][posX];
				int time = pos[2];
				if(time!=i) {
					q.offer(pos);
					break;
				}
				
				// 종류따라 dx, dy를 돌고
				for(int j=0; j<dx[type].length; j++) {
					int nextX = posX + dx[type][j];
					int nextY = posY + dy[type][j];
					if(nextX<0 || nextX>=M || nextY<0 || nextY>=N || visited[nextY][nextX] || map[nextY][nextX] == 0) {
						continue;
					}
					if(!canGo(type, j, map[nextY][nextX])) {continue;}
					
					// visited따라 true처리및 count증가
					visited[nextY][nextX] = true;
					count++;
					q.offer(new int[] {nextX, nextY, time+1});
				}
			}
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			calc(C, R);
			System.out.println("#"+tc+" "+count);
			
		}
		
	}
}
