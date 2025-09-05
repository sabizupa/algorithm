import java.io.*;
import java.util.*;

public class SWEA3124 {
	static int T;
	static int V;
	static int E;
	static int parent[];
	static long result;
	static List<Edge> edgeList;

	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int cost;
		
		Edge(int start, int end, int cost){
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	
	
	public static int findParent(int n1) {
		if(parent[n1] == n1) {
			return n1;
		}
		return parent[n1] = findParent(parent[n1]);
	}
	public static void union(int n1, int n2, int cost) {
		int p1 = findParent(n1);
		int p2 = findParent(n2);
		if(p1 != p2) {
			parent[p2] = p1;
			result += cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			result = 0;
			parent = new int[V+1];
			edgeList = new ArrayList<>();
			for(int i=1; i<=V; i++) {
				parent[i] = i;
			}
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int start= Integer.parseInt(st.nextToken());
				int end= Integer.parseInt(st.nextToken());
				int cost= Integer.parseInt(st.nextToken());
				edgeList.add(new Edge(start, end, cost));
			}
			
			Collections.sort(edgeList);
			
			for(int i=0; i<E; i++) {
				Edge e = edgeList.get(i);
				union(e.start, e.end, e.cost);
			}
			
			System.out.println("#"+tc+" "+result);
			
		}
	}
}
