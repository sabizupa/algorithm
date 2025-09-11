import java.io.*;
import java.util.*;

public class SWEA2383 {
	// 참고함: https://velog.io/@hyunsoo730/SWEA-2383-%EC%A0%90%EC%8B%AC-%EC%8B%9D%EC%82%AC%EC%8B%9C%EA%B0%84-Python
	static class Person {
		int x;
		int y;

		public Person(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Stair {
		int x;
		int y;
		int k;

		public Stair(int x, int y, int k) {
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}

	static int result;
	static int T;
	static int N;
	static int map[][];
	static int selected[];
	static List<Person> people;
	static List<Stair> stairs;
	
	static void perm(int depth) {
		if(depth == people.size()) {
			PriorityQueue<Integer> waitingA = new PriorityQueue<>();
			PriorityQueue<Integer> waitingB = new PriorityQueue<>();
			for(int i=0; i<people.size(); i++) {
				if(selected[i] == 0) {
					int dx = Math.abs(people.get(i).x-stairs.get(0).x);
					int dy = Math.abs(people.get(i).y-stairs.get(0).y);
					waitingA.offer(dx+dy);
				}
				else {
					int dx = Math.abs(people.get(i).x-stairs.get(1).x);
					int dy = Math.abs(people.get(i).y-stairs.get(1).y);
					waitingB.offer(dx+dy);
				}
			}
			
			Deque<Integer> descendingA = new ArrayDeque<>();
			Deque<Integer> descendingB = new ArrayDeque<>();
			while(!waitingA.isEmpty()) {
				int arrived = waitingA.poll();
				if(descendingA.size()<3) {
					descendingA.offer(arrived);
				}else {
					int descended = descendingA.poll() + stairs.get(0).k;
					if(descended < arrived) {
						descendingA.offer(arrived);
					}else {
						descendingA.offer(descended);
					}
				}
			}
			while(!waitingB.isEmpty()) {
				int arrived = waitingB.poll();
				if(descendingB.size()<3) {
					descendingB.offer(arrived);
				}
				else {
					int descended = descendingB.poll()+stairs.get(1).k;
					if(descended < arrived) {
						descendingB.offer(arrived);
					}else {
						descendingB.offer(descended);
					}
				}
			}
			
			int descendedA = 0;
			int descendedB = 0;
			if(descendingA.size()>0) {
				descendedA = descendingA.peekLast()+stairs.get(0).k+1;
			}
			if(descendingB.size()>0) {
				descendedB = descendingB.peekLast()+stairs.get(1).k+1;
			}
			result = Math.min(result, Math.max(descendedA, descendedB));
			return;
		}else {
			selected[depth] = 0;
			perm(depth+1);
			selected[depth] = 1;
			perm(depth+1);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			people = new ArrayList<>();
			stairs = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == 1) {
						people.add(new Person(j,i));
					}
					else if(map[i][j]>=2){
						stairs.add(new Stair(j, i, map[i][j]));
					}
				}
			}
			selected = new int[people.size()];
			result = Integer.MAX_VALUE;
			perm(0);
			System.out.println("#"+tc+" "+result);
		}
	}
}
