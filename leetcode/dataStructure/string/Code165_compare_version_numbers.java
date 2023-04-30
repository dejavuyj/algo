package leetcode.dataStructure.string;

public class Code165_compare_version_numbers {

    public int compareVersion(String version1, String version2) {
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");
        int size1 = arr1.length;
        int size2 = arr2.length;
        int maxSize = Math.max(size1, size2);

        for (int i = 0; i < maxSize; i++) {
            int v1 = i < size1 ? Integer.parseInt(arr1[i]) : 0;
            int v2 = i < size2 ? Integer.parseInt(arr2[i]) : 0;
            if (v1 > v2) {
                return 1;
            } else if (v1 < v2) {
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String version1 = "0.1", version2 = "1.1";
        Code165_compare_version_numbers c = new Code165_compare_version_numbers();
        System.out.println(c.compareVersion(version1, version2));
    }
}
