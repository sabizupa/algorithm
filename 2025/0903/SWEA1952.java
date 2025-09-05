import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA1952 {
	static int T;
	static int[] plan; // 수영계획 저장
	static int day; // 1일이용권가격
	static int month;
	static int threeMonth;
	static int year;
//	static int b;
	static int dp[];
	
	static void solve2() {
		for(int i=12; i>=1; i--) {
			// 이번달에 1일이용권을 사용한다. 혹은 이번달 계획이 0일이다.
			int case1 = dp[i+1] + plan[i]*day;
			// 이번달에 1달이용권.
			int case2 = dp[i+1] + month;
			// 이번달에 3달이용권.
			int case3= dp[i+3] + threeMonth;
			// 이번달에 1년이용권.
			int case4 = year;
			
			int result;
			result = Math.min(case1, case2);
			result = Math.min(result, case3);
			result = Math.min(result, case4);

			dp[i] = result;
		}
	}
//	
//	// n월부터 b월까지 이용했을 때 min 금액? 그런데,b월은 마지막수영계획이있는달로써..main에서 언제까지 수영할건지 파악해서 정해놔야
//	// 함.?그냥b안해도 12월하면될것같음..
//	static int solve(int n) {
//		if (n >= b + 1) {
//			return 0;
//		}
//
//		// 이번달에 1일이용권을 사용한다. 혹은 이번달 계획이 0일이다.
//		int case1 = 10000000;
//		case1 = solve(n + 1) + plan[n] * day;
//		// 이번달에 1달이용권.
//		int case2 = 10000000;
//		case2 = solve(n + 1) + month;
//		// 이번달에 3달이용권.
//		int case3 = 10000000;
//		case3 = solve(n + 3) + threeMonth;
//		// 이번달에 1년이용권.
//		int case4 = year;
//
//		int result;
//		result = Math.min(case1, case2);
//		result = Math.min(result, case3);
//		result = Math.min(result, case4);
//
//		return result;
//	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			threeMonth = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			plan = new int[13];
			dp = new int[16];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
//			for (int i = 1; i <= 12; i++) {
//				if (plan[i] != 0) {
//					b = i;
//				}
//			}
			
			//System.out.println("#"+tc+" "+solve(1));
			solve2();
			System.out.println("#"+tc+" "+dp[1]);
		}
	}

}
