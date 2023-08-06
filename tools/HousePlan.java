package tools;

public class HousePlan {

    private static void simulation() {
        double total = 0;
        double anualSave1 = 10; // 前10年,每年存
        double anualSave2 = 14.7; // 后7年,每年存
        int years = 10; // 前多少年
        int leftYears = 17 - years; // 后多少年

        double rate = 1.07; // 每年收益率

        System.out.println("前 " + years + " 年, 每年存 " + anualSave1 + " 万");
        System.out.println("后 " + leftYears + " 年, 每年存 " + anualSave2 + " 万");
        System.out.println("年收益率 " + (int) ((rate - 1) * 100) + "%");

        int startYear = 2025; // 起始
        total = 0;
        double previous = total;
        for (int j = 0; j < 48; j++) {
            int currYear = startYear + j;
            total = getTotal(total, anualSave1, anualSave2, years, rate, j, currYear);
            System.out.println(currYear + "年, 总金额是 " + (int) total + ", 比前一年增加 " + (int) (total - previous));
            previous = total;
        }
    }

    private static double getTotal(double total, double anualSave1, double anualSave2, int years, double rate, int j, int currYear) {
        total *= rate;
        if (j < years) {
            total += anualSave1;
        } else if (j < 17) {
            total += anualSave2;
        }

        if (j == years) {
            System.out.println(" --------- " + currYear + "年开始, 14.76万");
        }

        if (j == 17) {
            System.out.println(" --------- " + currYear + "年, 贷款还清");
        }

//        int costAge = 55;
//        double cost = 500;
//        if (currYear == (1987+costAge)) {
//            System.out.print(" --------- " + costAge + "岁,花 " + cost + "  ");
//            total -= cost;
//        }
        return total;
    }

    public static void main(String[] args) {
        simulation();
    }
}