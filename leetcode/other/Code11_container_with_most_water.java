package leetcode.other;

public class Code11_container_with_most_water {

    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = Math.min(height[i], height[j]) * (j - i);
                max = Math.max(max, area);
            }
        }
        return max;
    }

    public int maxArea2(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(max, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] height = {4, 3, 2, 1, 4};
        Code11_container_with_most_water c = new Code11_container_with_most_water();
        int ret = c.maxArea2(height);
        System.out.print(ret);
    }
}
