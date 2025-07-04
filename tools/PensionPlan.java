package tools;

public class PensionPlan {

    public static void main(String[] args) {
        simulation();
//        simulation_pingan_caifuxinsheng();
    }

    private static void simulation() {
//        boolean single = true;
        boolean single = false;
        int startYear = 2025; // 起始
        int saveYears; // 存多少年
        double total, anualSave, anualCost, annuity;
        if (single) {
            total = 109;
//            anualSave = (double) 1504 * 12 / 10000; // 每年存
            anualSave = 18; // 每年存
            anualCost = 16 - 7.0f / 2; // 不工作后,每年的花费, 房租7万
            annuity = 5; // 65岁后,每年领的养老金
            saveYears = 2;
        } else {
            total = 260;
            anualCost = 30 - 5; // 不工作后,每年的花费, 房租5万
            anualSave = 8; // 每年存
            annuity = 15; // 65岁后,每年领的养老金
            saveYears = 4;
        }

        double rate = 1.1; // 每年收益率
        double rate2 = rate - 0.02; // 剔除通货膨胀
        double rateShrink = 0.99; // 收益率缩水
//        double rateShrink = 1; // 收益率不缩水

        int endYear = 2087; // 到哪一年

        System.out.println("每年存 " + anualSave + " 万,总共存 " + saveYears + " 年");
        System.out.println("年收益率 " + (int) ((rate - 1) * 100) + "%");
        System.out.println("剔除通货膨胀后, 年收益率 " + (int) ((rate2 - 1) * 100) + "%");
        System.out.println(saveYears + "年后不工作, 每年花 " + anualCost + " 万");
        System.out.println("2052年后退休, 每年领养老金 " + annuity + " 万");
        System.out.println(startYear + "年初, 初始金额 " + (int) total + " 万");

        double total2 = total;
        double previous = total;
        double previous2 = total;
        for (int currYear = startYear, j = 0; currYear <= endYear; currYear++, j++) {
            if (currYear == 2028) {
                System.out.println(" --------- " + currYear + "年, 开始赡养父母, anualCost + " + (single ? 2.4 : 4.8));
                anualCost += single ? 2.4 : 4.8;
            }

            if (currYear == 2035) {
                System.out.println(" --------- " + currYear + "年, 房贷还清, anualCost - 5 ");
                anualCost -= 5;
            }

            if (currYear == 2046) {
                System.out.println(" --------- " + currYear + "年, 房贷还清, anualCost - 3 ");
                anualCost -= 3;
            }

            total = getTotal(total, anualSave, anualCost, annuity, saveYears, rate, j, currYear);
            System.out.print(currYear + "年底, 总金额是 " + (int) total + ", 比前一年增加 " + (int) (total - previous));
            previous = total;

            total2 = getTotal(total2, anualSave, anualCost, annuity, saveYears, rate2, j, currYear);
            System.out.println("   剔除通货膨胀后, 有效金额是 " + (int) total2 + ", 比前一年增加 " + (int) (total2 - previous2));
            previous2 = total2;

            if (total > 500 && rate > 1.07) {
                rate = 1 + (rate - 1) * rateShrink;
                rate2 = 1 + (rate2 - 1) * rateShrink;
            }
        }
    }

    private static double getTotal(double total, double anualSave, double anualCost, double annuity, int saveYears, double rate, int j, int currYear) {
        total *= rate;

        if (j < saveYears) {
            total += anualSave;
        } else {
            total -= anualCost;
        }

        if (j == saveYears) {
            System.out.print(" --------- " + currYear + "年开始,不工作了 ");
        }

        if (currYear == 2052) {
            System.out.print(" --------- " + currYear + "年开始,领退休金 ");
        }
        if (currYear >= 2052) {
            total += annuity;
        }

//        int costAge = 55;
//        double cost = 500;
//        if (currYear == (1987 + costAge)) {
//            System.out.print(" --------- " + costAge + "岁,花 " + cost + "  ");
//            total -= cost;
//        }
        return total;
    }

    @SuppressWarnings("unused")
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
}