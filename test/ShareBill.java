package test;

import java.text.DecimalFormat;

/**
 * 计算拆单匹配的方案数量
 */
public class ShareBill {

    /**
     * n个元素可以划分为多少个不同的非空子集
     */
    private static long stirlingNumber2(int n, int m) {
        long[][] dp = new long[n + 1][n + 1];
        dp[0][0] = 1;
        int min = 0;
        int i, j;
        // 非空子集0个
        for (i = 1; i <= n; i++)
            dp[i][0] = 0;

        // m > n
        for (i = 0; i < n; i++)
            dp[i][i + 1] = 0;

        for (i = 1; i <= n; i++) {
            min = i < m ? 0 : m;
            for (j = 1; j <= m; j++) {
                dp[i][j] = j * dp[i - 1][j] + dp[i - 1][j - 1];
//                System.out.println("dp["+i+"]["+j+"] " + dp[i][j]);
            }
        }
//        System.out.println(dp[n][m]);
        return dp[n][m];
    }

    private static long getSolutionNum(int n) {
        long num = 0;
        for (int i = 1; i <= n; i++) {
            num += stirlingNumber2(n, i);
        }
        return num;
    }

    private static long factorial(int number) {
        long result = 1;

        for (int i = number; i > 0; i--) {
            result *= i;
        }

        return result;
    }

    // 组合数
    private static long combination(int n, int m) {
        return factorial(n) / (factorial(m) * factorial(n - m));
    }

    private static long getAllCombination(int n) {
        long num = 0;
        for (int i = 1; i <= n; i++) {
            num += combination(n, i);
        }
        return num;
    }

    private static void addNum(long num) {
        long tmp = 0;
        for (long i = 0; i <= num; i++) {
            tmp += i;
        }
    }

    private static void printBellNums() {
        StringBuilder sb = new StringBuilder();
        sb.append("商品件数").append("\t\t").append("订单数量").append("\t\t").append("拆单方案数量").append("\n");
//        sb.append("商品件数").append("\t\t").append("拆单方案数量").append("\n");
        for (int n = 1; n <= 15; n++) {
            //long allCombinationNum = getAllCombination(n);
            long allCombinationNum = (long) (Math.pow(2, n) - 1);
//            System.out.println("订单数量" + n + ", 调用权益计算的次数: " + allCombinationNum);

            long solutionNum = getSolutionNum(n);
//            System.out.println("订单数量" + n + ", 拆单方案数量: " + solutionNum);

            sb.append(n).append("\t\t\t")
                    .append(new DecimalFormat(",###").format(allCombinationNum))
                    .append("\t\t").append(n < 10 ? "\t" : "")
                    .append(new DecimalFormat(",###").format(solutionNum)).append("\n");

//            long calCost = allCombinationNum * 50;
//            System.out.println(allCombinationNum + "次权益计算,耗时: " + calCost + "ms");
//
//            long start = System.currentTimeMillis();
//            addNum(solutionNum);
//            long addCost = System.currentTimeMillis() - start;
//            System.out.println(solutionNum + "次加法,耗时: " + addCost + "ms");
//
//            System.out.println("总耗时: " + (calCost + addCost) / 1000 + "s");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        printBellNums();
//        stirlingNumber2(4,2);
    }
}
