package algorithm;

// ��������
public class Backpack {

	int maxW = 0;
	int maxV = 0;

	/**
	 * 0-1 �޼�ֵ����, lintcode92, ��n����Ʒ����ѡ������Ʒװ�뱳���������װ������ ���豳���Ĵ�СΪm��ÿ����Ʒ�Ĵ�СΪA[i]
	 */
	// �����㷨
	public int backPack(int m, int[] A) {
		maxW = 0;
		backTrack1(A, 0, 0, m);
		return maxW;
	}

	private void backTrack1(int[] A, int i, int cw, int m) {
		if (cw == m || i == A.length) {
			if (cw > maxW) {
				maxW = cw;
			}
			return;
		}

		backTrack1(A, i + 1, cw, m);
		if (cw + A[i] <= m) {
			backTrack1(A, i + 1, cw + A[i], m);
		}
	}

	/**
	 * 0-1 �м�ֵ����, lintcode125, �� n ����Ʒ��һ����СΪ m �ı���. �������� A ��ʾÿ����Ʒ�Ĵ�С������ V
	 * ��ʾÿ����Ʒ�ļ�ֵ. �������װ�뱳�����ܼ�ֵ�Ƕ��?
	 */
	// �����㷨
	public int backPackII(int m, int[] A, int[] V) {
		maxW = 0;
		maxV = 0;
		backTrack2(A, V, 0, 0, 0, m);
		return maxV;
	}

	private void backTrack2(int[] A, int[] V, int i, int cw, int cv, int m) {
		if (cw == m || i == A.length) {
			if (cw > maxW) {
				maxW = cw;
			}
			if (cv > maxV) {
				maxV = cv;
			}
			return;
		}

		backTrack2(A, V, i + 1, cw, cv, m);
		if (cw + A[i] <= m) {
			backTrack2(A, V, i + 1, cw + A[i], cv + V[i], m);
		}
	}

	// ��̬�滮

	public static void main(String[] args) {
		Backpack c = new Backpack();
		int[] A = { 1, 3, 5, 11, 5 };
		int[] V = { 33, 3, 5, 11, 52 };
		int w = 20;
		// System.out.println(c.backPack(w, A));
		System.out.println(c.backPackII(w, A, V));
	}
}