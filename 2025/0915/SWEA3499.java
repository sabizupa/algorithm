import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3499 {
	static int T;
	static int N;
	static String[] cards;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			cards = new String[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				cards[i] = st.nextToken();
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(tc);
			sb.append(" ");
			if (N % 2 == 0) {
				for(int i=0; i<N/2; i++) {
					sb.append(cards[i]);
					sb.append(" ");
					sb.append(cards[N/2+i]);
					sb.append(" ");
				}
			} else {
				for(int i=0; i<N/2; i++) {
					sb.append(cards[i]);
					sb.append(" ");
					sb.append(cards[N/2+1+i]);
					sb.append(" ");
				}
				sb.append(cards[N/2]);
			}
			
			System.out.println(sb.toString());

		}

	}
}
