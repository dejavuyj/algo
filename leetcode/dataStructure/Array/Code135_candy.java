package leetcode.dataStructure.Array;


public class Code135_candy {

    public int candy(int[] ratings) {
        int n = ratings.length;
        int ret = 1;
        int inc = 1, dec = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;
            } else {
                dec++;
                if (dec == inc) {
                    dec++;
                }
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] ratings = {100, 4, 200, 1, 3, 2};
        Code135_candy c = new Code135_candy();
        int ret = c.candy(ratings);
        System.out.print(ret);
    }
}
