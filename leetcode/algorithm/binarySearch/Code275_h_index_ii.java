package leetcode.algorithm.binarySearch;

public class Code275_h_index_ii {

    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = citations.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] >= n - mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return n - left;
    }

    public static void main(String[] args) {
        int[] citations = {0, 1};
        Code275_h_index_ii c = new Code275_h_index_ii();
        int ret = c.hIndex(citations);
        System.out.println(ret);
    }
}
