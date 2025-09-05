import java.util.*;
import java.io.*;

public class SWEA1486{
	static int[] person;
	static int N;
	static int B;
	static int min;
	static int T;
	
	static public class Tower{
		int pos = 0;
		int height = 0;
	}
	
	public static void dfs() {
		min = Integer.MAX_VALUE;
		Deque<Tower> stack = new ArrayDeque<>();
		Tower t1 = new Tower();
		Tower t2 = new Tower();
		t2.height = person[0];
		
		stack.push(t1);
		stack.push(t2);
		
		while(!stack.isEmpty()) {
			Tower tower = stack.pop();
			//System.out.println("pos:"+tower.pos);
			//System.out.println("height:"+tower.height);
			if(tower.height >= min) {
				continue;
			}
			else if(tower.height < min){
				if(tower.height >= B) {
					min = tower.height;
					continue;
				}
				else if(tower.height < B) {
					if(tower.pos == N-1) {
						continue;
					}
					else {
						Tower nextTower1 = new Tower();
						Tower nextTower2 = new Tower();
						int nextPos = tower.pos + 1;
						nextTower1.pos = nextPos;
						nextTower2.pos = nextPos;
						nextTower1.height = tower.height;
						nextTower2.height = tower.height+person[nextPos];
						stack.push(nextTower1);
						stack.push(nextTower2);
						//System.out.println("pos: "+nextTower1.pos+" / next height1: "+nextTower1.height);
						//System.out.println("pos: "+nextTower2.pos+" / next height2: "+nextTower2.height);
					}
				}
			}
			
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			person = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				person[i] = Integer.parseInt(st.nextToken());
			}
			dfs();
			int d = Math.abs(min-B);
			System.out.println("#"+tc+" "+d);
			
		}
	}

}
