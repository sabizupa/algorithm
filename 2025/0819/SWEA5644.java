import java.util.*;
import java.io.*;

public class SWEA5644 {
	static int T;
	static int M;
	static int A;
	static int N = 11;
	static int userA[];
	static int userB[];
	static int ap[][]; // [ap][x y 범위 충전량]. 충전량순정렬
	static boolean visited[]; // ap 랭킹별.
	static int[] dx = {0,0,1,0,-1};
	static int[] dy = {0,-1,0,1,0};
	
	public static int calc(int aX, int aY, int bX, int bY) {
		int result = 0;
		Arrays.fill(visited, false);
		int ap1 = chargeWhere(aX, aY);
		if(ap1!=-1) {
			visited[ap1]=true;
			result += ap[ap1][3];
		}
		int ap2 = chargeWhere(bX, bY);
		if(ap2!=-1) {
			visited[ap2]=true;
			result += ap[ap2][3];
		}
		return result;
	}
	
	
	public static int chargeWhere(int x, int y) {
		for(int i=0; i<A; i++) {
			if(visited[i]) {continue;}
			if(Math.abs(x-ap[i][0])+Math.abs(y-ap[i][1])<=ap[i][2]) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			M =Integer.parseInt(st.nextToken());
			A=Integer.parseInt(st.nextToken());
			userA = new int[M];
			userB = new int[M];
			ap = new int[A][4];
			visited = new boolean[A];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M;i++) {
				userA[i]=Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M;i++) {
				userB[i]=Integer.parseInt(st.nextToken());
			}	
			for(int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				ap[i][0] = Integer.parseInt(st.nextToken());
				ap[i][1] =Integer.parseInt(st.nextToken());
				ap[i][2] = Integer.parseInt(st.nextToken());
				ap[i][3] = Integer.parseInt(st.nextToken());
				
			}
			
			Arrays.sort(ap, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[3]>o2[3]) {return -1;}
					else if(o1[3]==o2[3]) {return 0;}
					else {return 1;}
					
				}
			});
			
			// 0초 차지			
			int result = 0;
			int userAX = 1;
			int userAY = 1;
			int userBX = 10;
			int userBY= 10;
			result += Math.max(calc(userAX,userAY,userBX,userBY),calc(userBX,userBY,userAX,userAY));

			// 1초부터
			for(int i=0;i<M;i++) {
				userAX += dx[userA[i]];
				userAY += dy[userA[i]];
				userBX += dx[userB[i]];
				userBY += dy[userB[i]];
				result += Math.max(calc(userAX,userAY,userBX,userBY),calc(userBX,userBY,userAX,userAY));
			}
			
			
			System.out.println("#"+tc+" "+result);
			
			
		}
		

		
		
	}
}
