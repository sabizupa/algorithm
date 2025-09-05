import java.io.*;
import java.util.*;

public class SWEA1873 {
	static char[][] map;
	static int posY;
	static int posX;
	static int dx[]= {0,1,0,-1};//북동남서
	static int dy[]= {-1,0,1,0};
	
	static int T;
	static int H;
	static int W;
	static int N;

	public static void doThis(int type) {
		if (type <= 3) {
			if (type==0) {
				map[posY][posX] = '^';
			} else if (type==1) {
				map[posY][posX] = '>';
			} else if (type==2) {
				map[posY][posX] = 'v';
			} else if (type==3) {
				map[posY][posX] = '<';
			}
			
			
			int nextY = posY + dy[type];
			int nextX = posX + dx[type];

			if (nextY < 0 || nextY >= H || nextX < 0 || nextX >= W) {
				return;
			}
			if (map[nextY][nextX] == '.') {
				map[nextY][nextX]=map[posY][posX];
				map[posY][posX]='.';
				posY = nextY;
				posX = nextX;
			}
			return;
		} else if (type == 4) {
			int tanY = posY;
			int tanX = posX;
			int tanType=-1;
			if (map[posY][posX] == '^') {
				tanType=0;
			} else if (map[posY][posX] == '>') {
				tanType=1;
			} else if (map[posY][posX] == 'v') {
				tanType=2;
			} else if (map[posY][posX] == '<') {
				tanType=3;
			}
			while (true) {
				tanY+=dy[tanType];
				tanX+=dx[tanType];
				if (tanY < 0 || tanY >= H || tanX < 0 || tanX >= W) {
					return;
				}
				if (map[tanY][tanX] == '*') {
					map[tanY][tanX] = '.';
					return;
				} else if (map[tanY][tanX] == '#') {
					return;
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
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				for (int j = 0; j < W; j++) {
					map[i][j] = s.charAt(j);
					if(map[i][j]== '^'||map[i][j]== '>'||map[i][j]== 'v'||map[i][j]== '<') {
						posX=j;
						posY=i;
					}
				}
			}
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int i = 0; i < N; i++) {
				
				char type = s.charAt(i);

				if (type == 'U') {
	
					doThis(0);
				} else if (type == 'R') {
					doThis(1);
				} else if (type == 'D') {
					doThis(2);
				} else if (type == 'L') {
					doThis(3);
				} else if (type == 'S') {
					doThis(4);
				}
				

			}
			System.out.print("#"+tc+" ");
			for(int ii=0; ii<H; ii++) {
				for(int jj=0; jj<W; jj++) {
					System.out.print(map[ii][jj]);
				}
				System.out.println();
			}
			
		}
	
	}
}
