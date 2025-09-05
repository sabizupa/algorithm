import java.io.*;
import java.util.*;

public class SWEA1238 {
	static int maxDepth;
	static int maxNumber;
	static int graph[][]; // 101 101
	static boolean visited[];

	public static void bfs(int start, int startDepth) {
		Deque<Integer> q = new ArrayDeque<>();
		q.offer(start);
		q.offer(startDepth);
		visited[start]=true;
		
		while(!q.isEmpty()) {
			int pos = q.poll();
			int depth = q.poll();
			//System.out.println(pos+" "+depth);
			for (int i = 1; i <= 100; i++) {
				if (graph[pos][i] != 0 && !visited[i]) {
					visited[i]=true;
					q.offer(i);
					q.offer(depth+1);
				}
			}
			if (depth > maxDepth) {
				maxDepth = depth;
				maxNumber = pos;
			} else if (depth == maxDepth && pos > maxNumber) {
				maxNumber = pos;
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int tc = 1; tc <= 10; tc++) {
			st= new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			maxDepth = -1;
			maxNumber = -1;
			graph=new int[101][101];
			visited=new boolean[101];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from][to]=1;
			}

			bfs(start, 1);
			System.out.println("#"+tc+" "+maxNumber);
		}
	}
}
