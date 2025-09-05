import java.io.*;
import java.util.*;

public class SWEA4796 {
	static int T;
	static int N;
	static int height[];
	static int count;
	// 1. �������� ���Ѵ�.
	// --
	// 2. peek ã��:
	// height[i]>height[i+1] �Ǹ�, i�� ������̴�.
	// 3-1. ��ӿö󰡴°��: ���� i==height.lenghth-1�ε��� �� ã������ �����Ѵ�.
	// 3-2. ���ۺ��ͳ������°��: ���� peek==���������̸�, ���������� height[j]<height[j+1]�� j�� ����� �ٽõ�����.
	// �ٵ�, j�� height.length-1�ε��� �� ã������ �����Ѵ�.
	// 4. ������ ã��:
	// i=�������� �����ؼ�, height[i]<height[i+1]�̸� i�� �������̴�.
	// i�� height.length-1�ε� ��ã���� height.lenght-1�� �������̴�.
	// 5. �׷��ٸ� ������ ����
	// (�����-��������) * (������-�����).
	// ���� �������� = ������?
	// �ٵ� ������������ == height.length-1�̸� ��������.

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
			// ������ ��� �ö�
			if (i == height.length - 1) {
				return;
			}
			// ó������ ��� ������
			else if (peek == start) {
				int j = start;
				while (j <= height.length - 2) {
					if (height[j] < height[j + 1]) {
						start = j;
						break;
					}
					j++;
				}
				// ������ ������
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
