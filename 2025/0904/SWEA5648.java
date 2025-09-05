import java.io.*;
import java.util.*;

public class SWEA5648 {
	static class Atom implements Comparable<Atom> {
		int x;
		int y;
		int dir;
		int energy;

		public Atom(int x, int y, int dir, int energy) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.energy = energy;
		}

		@Override
		public int compareTo(Atom o) {
			if(this.y < o.y) {
				return -1;
			}
			else if(this.y> o.y) {
				return 1;
			}
			else {
				return Integer.compare(this.x, o.x);
			}
		}
	}

	static int T;
	static int sum;
	static ArrayList<Atom> list;
	static int dx[] = { 0, 0, -1, 1 }; // 상하좌우
	static int dy[] = { 1, -1, 0, 0 };

	static void move() {
		for (int i = 0; i < list.size(); i++) {
			Atom a = list.get(i);
			a.x += dx[a.dir];
			a.y += dy[a.dir];
		}
		Collections.sort(list);
	}

	static void merge() {
		for(int i=0; i<list.size()-1; i++) {
			boolean flag = false;
			Atom a = list.get(i);
			for(int j=i+1; j<list.size();) {
				Atom b = list.get(j);
				if(a.y == b.y && a.x == b.x) {
					flag = true;
					sum += b.energy;
					list.remove(b);
				}else break;
			}
			if(flag == true) {
				sum += a.energy;
				list.remove(a);
				i--;
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			sum = 0;
			list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken())*2;
				int y = Integer.parseInt(st.nextToken())* 2;
				int dir = Integer.parseInt(st.nextToken());
				int K = Integer.parseInt(st.nextToken());
				list.add(new Atom(x, y, dir, K));
			}
			for (int i = 1; i <= 4005; i++) {
				move();
				merge();
			}
			System.out.println("#" + tc + " " + sum);

		}

	}
}
