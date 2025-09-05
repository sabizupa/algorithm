import java.io.*;
import java.util.*;

public class SWEA4012 {

	// nCn/2
	// n/2 순열 각각구하기

	static int T;
	static int N;
	static int[] selected;
	static int[][] synergy;
	static int min;

	public static void comb(int depth, int start) {
		if (depth == N / 2) {
			int[] selected2 = notUsedOf(selected);
			int score1 = scoreCalc(selected);
			int score2 = scoreCalc(selected2);
			// System.out.println(Arrays.toString(selected)+score1);
			// System.out.println(Arrays.toString(selected2)+score2+"----");

			min = Math.min(min, Math.abs(score1 - score2));
			return;
		}
		for (int i = start; i < N; i++) {
			selected[depth] = i;
			comb(depth + 1, i + 1);

		}

	}

	public static int[] notUsedOf(int[] selected) {
		int[] result = new int[N / 2];
		boolean[] check = new boolean[N];
		for (int i = 0; i < N / 2; i++) {
			check[selected[i]] = true;
		}
		int pos = 0;
		for (int i = 0; i < N; i++) {
			if (check[i] == false) {
				result[pos] = i;
				pos++;
			}
		}
		return result;
	}

	public static int scoreCalc(int[] elements) {
		int sum = 0;
		for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements.length; j++) {
				if (j == i) {
					continue;
				}
				sum += synergy[elements[i]][elements[j]];
			}
		}
		return sum;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			selected = new int[N / 2];
			synergy = new int[N][N];
			min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			comb(0, 0);
			sb.append("#");
			sb.append(tc);
			sb.append(" ");
			sb.append(min);
			sb.append("\n");

		}
		System.out.println(sb.toString());

	}

}
