package test;

@SuppressWarnings("unused")
public class PensionPlan {

    private static void simulation() {
        double total = 0;
        //double anualSave = (double) 1504 * 12 / 10000; // 每年存
        double anualSave = 40; // 每年存
        double anualCost = 25; // 每年花费
        double annuity = 15; // 65岁后,每年领的养老金
        int saveYears = 10; // 存多少年
        double rate = 1.05; // 每年收益率
        int startYear = 2021; // 起始

        int leftYears = 57; // 多少年后
        System.out.println("每年存" + anualSave + "万,总共存" + saveYears + "年, 年收益率" + rate);
        System.out.println(saveYears + "年后不工作, 每年花" + anualCost + "万");
        System.out.println("2052年后退休, 每年领养老金" + annuity + "万");
        int i = leftYears;
        for (int j = 0; j < leftYears; j++, i--) {
            int currYear = startYear + j;
            total *= rate;
            if (j < saveYears) {
                total += anualSave;
            } else {
                total -= anualCost;
            }
            if (currYear >= 2052) {
                total += annuity;
            }
            System.out.println(currYear + "年, 总金额是" + total);
        }
    }

    private static void simulation_pingan_caifuxinsheng() {
        // 平安 财富鑫生 年金保险
        // 按费率表 33岁投保 2万保额 每个月交752 7年后每年领5200
        double total = 0;
        double anualPay = (double) 1504 * 12 / 10000; // 每年存
        double totalPay = anualPay * 10;
        double rate = 1.045; // 每年收益率
        int startYear = 2021; // 起始
        double sevenYearAfterAnualGet = 0.52;

        System.out.println("每年交" + anualPay + "万, 年收益率" + rate);
        System.out.println("总成本" + totalPay + "万");
        System.out.println(startYear + 5 + "年, 领" + anualPay / 2 + "万");
        System.out.println(startYear + 6 + "年, 领" + anualPay / 2 + "万");
        System.out.println(startYear + 7 + "年后, 每年领" + sevenYearAfterAnualGet + "万");

        int leftYears = 60;
        total += anualPay * Math.pow(rate, leftYears);
        System.out.println(startYear + 6 + "年的" + anualPay + "万, 累积" + leftYears + "年后,金额是" + total + "万");

        int i = leftYears;
        for (int j = 0; j < leftYears; j++, i--) {
            int currYear = startYear + 7 + j;
            double currYearPow = sevenYearAfterAnualGet * Math.pow(rate, i);
            total += currYearPow;
            System.out.println(currYear + "年的" + sevenYearAfterAnualGet + ",累积" + i + "年后,金额是" + currYearPow + ",  总金额是" + total);
        }

        System.out.println("总收益" + (total - totalPay) + "万");
    }

    public static void main(String[] args) {
        simulation();
//        simulation_pingan_caifuxinsheng();
    }
}