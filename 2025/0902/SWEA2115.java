import java.io.*;
import java.util.*;

public class SWEA2115 {
	static int T;
	static int N; // NxN
	static int M; // 가로 M개
	static int C; // 최대양
	static int max;
	static int pointMax;
	static List<int[]> points;
	static int[][] map;
	
	static void getPoints() {
		points = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				points.add(new int[] { j, i });
			}
		}
	}

	static void comb() {
		for (int i = 0; i < points.size(); i++) {
			for (int j = i + 1; j < points.size(); j++) {
				int[] point1 = points.get(i);
				int[] point2 = points.get(j);
				// 시작지점 겹치나 체크
				if ((point1[1] == point2[1]) && (point1[0] <= point2[0] && point2[0] <= point1[0] + N - 1)) {
					continue;
				}
				comb2(point1[0], point1[1], 0,0, 0);
				int point1Max = pointMax;
				pointMax= 0;
				comb2(point2[0], point2[1], 0,0, 0);
				int point2Max = pointMax;
				pointMax = 0;
				max = Math.max(max, point1Max+point2Max);
			}
		}
	}
	
	static void comb2(int x, int y, int sum1, int sum2, int depth) {
		if(depth==M) {
			pointMax = Math.max(pointMax, sum2);
			return;
		}
		if(sum1+map[y][x+depth] <= C) {
			comb2(x, y, sum1+map[y][x+depth], sum2+map[y][x+depth]*map[y][x+depth], depth+1);
		}
		comb2(x, y, sum1, sum2, depth+1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			max = 0;
			pointMax = 0;
			
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]= Integer.parseInt(st.nextToken());
				}
			}
			getPoints();
			comb();
			System.out.println("#"+ tc+" "+max);
		}
	}
}
