package leetcode.algorithm.binarySearch;

public class Code278_first_bad_version {

    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean isBadVersion(int i) {
        int firstErrorVersion = 0;
        return i >= firstErrorVersion;
    }

    public static void main(String[] args) {
        int n = 1;
        Code278_first_bad_version c = new Code278_first_bad_version();
        int ret = c.firstBadVersion(n);
        System.out.println(ret);
    }
}
