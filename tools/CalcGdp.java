package tools;

public class CalcGdp {

    public static void main(String[] args) {
        // 汇率 7.12, gdp单位:万亿美元
        int startYear = 2024;
        double china = 18.95;
        double america = 29.21;
        double chinaRatio = 0.042;
        double americaRatio = 0.02;

        for (int i = startYear; i < 2050; i++) {
            System.out.printf("%d年, 中国:%.2f, 美国:%.2f", i, china, america);
            china = china * (1 + chinaRatio);
            america = america * (1 + americaRatio);
            if (china > america) {
                System.out.print(" 超过美国");
            }
            System.out.println();
        }
    }
}
