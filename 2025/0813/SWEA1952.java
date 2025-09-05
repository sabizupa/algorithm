import java.io.*;
import java.util.*;


public class SWEA1952 {
	static int T;
	static int minCost;
	static int schedule[]; // 0월 없음
	static int ticket[]; // 1일 1달 3달 1년
	
	public static void dfs(int month, int cost) {
		if(month>=12) {
			minCost = Math.min(minCost, cost);
			return;
		}
		
		dfs(month+1, cost+schedule[month+1]*ticket[0]);
		dfs(month+1, cost+ticket[1]);
		dfs(month+3, cost+ticket[2]);
		dfs(month+12, cost+ticket[3]);
		
		
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		schedule = new int[13];
		ticket = new int[4];
		for(int tc=1; tc<=T; tc++) {
			minCost = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				ticket[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=12; i++) {
				schedule[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0,0);
			System.out.println("#"+tc+" "+minCost);
		}
		
		
		
	}
}
