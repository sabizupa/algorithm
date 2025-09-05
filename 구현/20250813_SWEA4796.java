import java.io.*;
import java.util.*;

public class SWEA4796 {
	static int T;
	static int N;
	static int height[];
	static int count;
	// 1. 시작지점 정한다.
	// --
	// 2. peek 찾기:
	// height[i]>height[i+1] 되면, i가 꼭대기이다.
	// 3-1. 계속올라가는경우: 만약 i==height.lenghth-1인데도 못 찾았으면 리턴한다.
	// 3-2. 시작부터내려가는경우: 만약 peek==시작지점이면, 시작지점을 height[j]<height[j+1]인 j로 만들고 다시돌린다.
	// 근데, j가 height.length-1인데도 못 찾았으면 리턴한다.
	// 4. 끝지점 찾기:
	// i=꼭대기부터 시작해서, height[i]<height[i+1]이면 i가 끝지점이다.
	// i가 height.length-1인데 못찾으면 height.lenght-1이 끝지점이다.
	// 5. 그렇다면 구간의 수는
	// (꼭대기-시작지점) * (끝지점-꼭대기).
	// 다음 시작지점 = 끝지점?
	// 근데 다음시작지점 == height.length-1이면 종료하자.

	public static void find() {
		int start = 0;
		int end = 0;
		int peek = 0;
		while (true) {

			int i = start;
			while (i <= height.length - 2) {
				if (height[i] > height[i + 1]) {
					peek = i;
					break;
				}
				i++;
			}
			// 끝까지 계속 올라감
			if (i == height.length - 1) {
				return;
			}
			// 처음부터 계속 내려감
			else if (peek == start) {
				int j = start;
				while (j <= height.length - 2) {
					if (height[j] < height[j + 1]) {
						start = j;
						break;
					}
					j++;
				}
				// 끝까지 내려감
				if (j == height.length - 1) {
					return;
				}
				continue;
			}

			int k = peek;
			while (k <= height.length - 2) {
				if (height[k] < height[k + 1]) {
					end = k;
					break;
				}
				k++;
			}
			if (k == height.length - 1) {
				end = k;
			}

			count += (peek - start) * (end - peek);
			start = end;
			if (start == height.length - 1) {
				return;
			}

		}

	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			height = new int[N];
			count = 0;
			for (int i = 0; i < N; i++) {
				height[i] = sc.nextInt();
			}

			find();
			System.out.println("#"+tc+" "+count);
		}

	}

}
