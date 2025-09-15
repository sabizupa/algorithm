import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1494 {
	// 참고: https://jhk0307.tistory.com/m/291

	static int T;
	static int N; // 지렁이수
	static int worm[][]; // [지렁이][x, y]
	static long min;
	static boolean selected[];

	public static void comb(int start, int count) {
		if (count == N/2) {
			long xSum = 0;
			long ySum = 0;

			for (int i = 0; i < N; i++) {
				if (selected[i]) {
					xSum += worm[i][0];
					ySum += worm[i][1];
				} else {
					xSum -= worm[i][0];
					ySum -= worm[i][1];
				}
			}

			min = Math.min(min, xSum * xSum + ySum * ySum);
			return;
		} else {
			for(int i=start; i<N; i++) {
				selected[i] = true;
				comb(i+1, count+1);
				selected[i] = false;
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
			worm = new int[N][2];
			min = Long.MAX_VALUE;
			selected = new boolean[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				worm[i][0] = x;
				worm[i][1] = y;
			}
			comb(0,0);
			System.out.println("#"+tc+" "+min);
		}

	}
}
