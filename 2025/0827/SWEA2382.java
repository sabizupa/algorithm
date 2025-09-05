import java.io.*;
import java.util.*;

public class SWEA2382 {
	static int[] dx = {0, 0, 0, -1, 1}; // 1~상하좌우
	static int[] dy = {0, -1, 1, 0, 0};
	static int[] dt = {0, 2, 1, 4, 3}; // type 변경
	static int T;
	static int N; // 한변길이
	static int M; // 시간
	static int K; // 미생물군집개수
	static List<Pack> packs;
	static class Pack implements Comparable<Pack>{
		int yx;
		int x;
		int y;
		int count;
		int type;
		
		Pack(int yx, int x, int y, int count, int type){
			this.yx = yx;
			this.x = x;
			this.y= y;
			this.count = count;
			this.type = type;
		}

		@Override
		public int compareTo(Pack o) {
			if(this.yx < o.yx) {
				return -1;
			}else if(this.yx == o.yx) {
				if(this.count > o.count) {
					return -1;
				}else if(this.count == o.count){
					return 0;
				}else {
					return 1;
				}
			}else {
				return 1;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			packs = new ArrayList<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());
				int type = Integer.parseInt(st.nextToken());
				Pack p = new Pack(y*N+x, x, y, count, type);
				packs.add(p);
			}
			
			for(int i=0; i<M; i++) {
				for(int j=0; j<packs.size(); j++) {
					Pack p = packs.get(j);
					p.x = p.x + dx[p.type];
					p.y = p.y + dy[p.type];
					p.yx = p.y*N+p.x;
					if(p.x == 0 || p.x == N-1 || p.y == 0 || p.y == N-1) {
						p.count = p.count / 2;
						p.type = dt[p.type];
					}
					if(p.count == 0) {
						packs.remove(p);
						j--;
					}
				}
				Collections.sort(packs);
				for(int j=0; j<packs.size()-1; j++) {
					Pack p1 = packs.get(j);
					Pack p2 = packs.get(j+1);
					if(p1.yx == p2.yx) {
						p1.count += p2.count;
						packs.remove(p2);
						j--;
					}
				}
			}
			
			int result = 0;
			for(int i=0; i<packs.size(); i++) {
				
				result += packs.get(i).count;
			}
			
			System.out.println("#"+tc+" "+result);
			
		}
	}
}
