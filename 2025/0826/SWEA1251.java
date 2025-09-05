import java.io.*;
import java.util.*;

public class SWEA1251 {
	static class Node{
		int x;
		int y;
		
		Node(int x, int y){
			this.x = x;
			this.y =y ;
		}
	}
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		double cost;
		
		Edge(int start, int end, double cost){
			this.start= start;
			this.end =end;
			this.cost =cost;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.cost<o.cost) {
				return -1;
			}else if(this.cost>o.cost) {
				return 1;
			}else{
				return 0;
			}
		}
	}
	
	static Node[] nodeList; // 번호순
	static List<Edge> edgeList; // 걍
	static int[] parent;
	static double result;
	static int T;
	static int N; // 섬수
	static double E; // 세율 
	
	static int findParent(int n) {
		if(parent[n] == n) {
			return n;
		}
		return parent[n] = findParent(parent[n]);
	}
	
	static void union(int n1, int n2, double cost) {
		int p1 = findParent(n1);
		int p2 = findParent(n2);
		
		if(p1 != p2) {
			//System.out.println(p1+"->"+p2);
			parent[p2] = p1;
			result += E*cost*cost;
		}
	}
	
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			nodeList = new Node[N];
			edgeList = new ArrayList<>();
			parent = new int[N];
			for(int i=0; i<N; i++) {
				parent[i]=i;
			}
			result = 0;
			
			StringTokenizer xs = new StringTokenizer(br.readLine());
			StringTokenizer ys = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				int x =Integer.parseInt(xs.nextToken());
				int y =Integer.parseInt(ys.nextToken());
				nodeList[i] = new Node(x,y);
			} 
			for(int i=0; i<N; i++) {
				for(int j=i+1; j<N; j++) {
					double dx = Math.abs(nodeList[i].x-nodeList[j].x);
					double dy = Math.abs(nodeList[i].y-nodeList[j].y);
					double cost = Math.sqrt(dx*dx+dy*dy);
					edgeList.add(new Edge(i,j,cost));
				}
			}
			
			st = new StringTokenizer(br.readLine());
			E = Double.parseDouble(st.nextToken());
			
			Collections.sort(edgeList);
			for(int i=0; i<edgeList.size(); i++) {
				Edge e = edgeList.get(i);
				union(e.start, e.end, e.cost);
			}
			
			System.out.println("#"+tc+" "+Math.round(result));
		}
		
	}
	
}
