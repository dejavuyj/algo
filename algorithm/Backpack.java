package algorithm;

// ��������
@SuppressWarnings("unused")
public class Backpack {

	int maxW = 0;
	int maxV = 0;
	int resolveCnt = 0;

	/**
	 * 0-1 �޼�ֵ����, lintcode92, ��n����Ʒ����ѡ������Ʒװ�뱳���������װ������ ���豳���Ĵ�СΪm��ÿ����Ʒ�Ĵ�СΪA[i]
	 */
	public int backPack(int m, int[] A) {
		// return backPack_bt(m, A); //����
		return backPack_dp2(m, A); // ��̬�滮
	}

	// �����㷨,0-1 �޼�ֵ����, ʱ�临�Ӷ�O(2^n)
	public int backPack_bt(int m, int[] A) {
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

	// ��̬�滮,0-1 �޼�ֵ����, ʱ�临�Ӷ�m*n, �ռ临�Ӷ�m*n
	public int backPack_dp(int m, int[] A) {
		int len = A.length;
		boolean[][] dp = new boolean[len][m + 1];
		dp[0][0] = true;
		if (A[0] <= m) {
			dp[0][A[0]] = true;
		}
		for (int i = 1; i < len; i++) {
			// ���������Ʒ
			for (int j = 0; j < m; j++) {
				if (dp[i - 1][j] == true) {
					dp[i][j] = true;
				}
			}
			// �������Ʒ
			for (int j = 0; j <= m - A[i]; j++) {
				if (dp[i - 1][j] == true) {
					dp[i][j + A[i]] = true;
				}
			}
		}
		for (int i = m; i >= 0; i--) {
			if (dp[len - 1][i] == true) {
				return i;
			}
		}
		return 0;
	}

	// ��̬�滮,0-1 �޼�ֵ����, ʱ�临�Ӷ�m*n, �ռ临�Ӷ�m
	public int backPack_dp2(int m, int[] A) {
		int len = A.length;
		boolean[] dp = new boolean[m + 1];
		dp[0] = true;
		if (A[0] <= m) {
			dp[A[0]] = true;
		}
		for (int i = 1; i < len; i++) {
			// �������Ʒ,һ��Ҫ�Ӻ���ǰ����,������ظ����㵼�³���
			for (int j = m - A[i]; j >= 0; j--) {
				if (dp[j] == true) {
					dp[j + A[i]] = true;
				}
			}
		}
		for (int i = m; i >= 0; i--) {
			if (dp[i] == true) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * 0-1 �м�ֵ����, lintcode125, �� n ����Ʒ��һ����СΪ m �ı���. �������� A ��ʾÿ����Ʒ�Ĵ�С������ V
	 * ��ʾÿ����Ʒ�ļ�ֵ. �������װ�뱳�����ܼ�ֵ�Ƕ��?
	 */
	public int backPackII(int m, int[] A, int[] V) {
		// return backPackII_bt(m, A, V);
		return backPackII_dp(m, A, V);
	}

	// �����㷨,0-1 �м�ֵ����, ʱ�临�Ӷ�O(2^n)
	public int backPackII_bt(int m, int[] A, int[] V) {
		maxV = 0;
		backTrack2(A, V, 0, 0, 0, m);
		return maxV;
	}

	private void backTrack2(int[] A, int[] V, int i, int cw, int cv, int m) {
		if (cw == m || i == A.length) {
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

	// ��̬�滮,0-1 �м�ֵ����, ʱ�临�Ӷ�m*n, �ռ临�Ӷ�m
	public int backPackII_dp(int m, int[] A, int[] V) {
		int maxV = 0;
		int n = A.length;
		int[] dp = new int[m + 1];
		dp[0] = 0;
		if (A[0] <= m) {
			dp[A[0]] = V[0];
		}
		for (int i = 1; i < n; i++) {
			for (int j = m - A[i]; j >= 0; j--) {
				dp[j + A[i]] = Math.max(dp[j + A[i]], dp[j] + V[i]);
			}
		}
		for (int i = 0; i <= m; i++) {
			maxV = Math.max(maxV, dp[i]);
		}
		return maxV;
	}

	/**
	 * ��ȫ����, lintcode562, ���� n ����Ʒ, �Լ�һ������, nums[i]�����i����Ʒ�Ĵ�С, ��֤��С��Ϊ��������û���ظ�,
	 * ������ target ��ʾ�����Ĵ�С, �ҵ������������ķ������� ÿһ����Ʒ����ʹ��������
	 */
	public int backPackIV(int[] nums, int target) {
		int[] dp = new int[target + 1];
		dp[0] = 1;
		for (int num : nums) {
			for (int i = num; i <= target; i++) {
				dp[i] += dp[i - num];
			}
		}
		return dp[target];
	}

	/**
	 * 0-1 ��ȫ����, lintcode563, ���� n ����Ʒ, �Լ�һ������, nums[i] �����i����Ʒ�Ĵ�С, ��֤��С��Ϊ����,
	 * ������ target ��ʾ�����Ĵ�С, �ҵ������������ķ�������ÿһ����Ʒֻ��ʹ��һ��?
	 */
	public int backPackV(int[] nums, int target) {
		// return backPackV_bt(nums, target);
		// return backPackV_dp(nums, target);
		return backPackV_dp2(nums, target);
	}

	private int backPackV_bt(int[] nums, int target) {
		backTrackV(nums, target, 0, 0);
		return resolveCnt;
	}

	private void backTrackV(int[] nums, int target, int ci, int cw) {
		if (cw == target || ci == nums.length) {
			if (cw == target) {
				resolveCnt++;
			}
			return;
		}

		backTrackV(nums, target, ci + 1, cw);
		if (cw + nums[ci] <= target) {
			backTrackV(nums, target, ci + 1, cw + nums[ci]);
		}
	}

	private int backPackV_dp(int[] nums, int target) {
		int n = nums.length;
		int[][] dp = new int[n][target + 1];
		dp[0][0] = 1;
		if (nums[0] <= target) {
			dp[0][nums[0]] = 1;
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= target; j++) {
				dp[i][j] += dp[i - 1][j];
			}
			for (int j = 0; j <= target - nums[i]; j++) {
				dp[i][j + nums[i]] += dp[i - 1][j];
			}
		}
		return dp[n - 1][target];
	}

	private int backPackV_dp2(int[] nums, int target) {
		int n = nums.length;
		int[] dp = new int[target + 1];
		dp[0] = 1;
		if (nums[0] <= target) {
			dp[nums[0]] = 1;
		}

		for (int i = 1; i < n; i++) {
			for (int j = target - nums[i]; j >= 0; j--) {
				dp[j + nums[i]] += dp[j];
			}
		}
		return dp[target];
	}

	public static void main(String[] args) {
		Backpack c = new Backpack();
		// int[] A = { 2, 3, 6, 7 };
		// int[] V = { 1, 5, 2, 4 };
		// int w = 7;
		// System.out.println(c.backPack(w, A));
		// System.out.println(c.backPackII(w, A, V));
		int[] A = { 1, 2, 3, 3, 7 };
		int w = 7;
		System.out.println(c.backPackV(A, w));
	}
}