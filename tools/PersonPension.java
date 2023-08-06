package tools;

public class PersonPension {

    private static void simulation() {
        // 个人养老金
        int startYear = 2022; // 起始
        double total = 0;
        double anualSave = 1.2; // 每年存
        int saveYears = 8; // 存多少年

        double rate = 1.06; // 每年收益率
        double rate2 = rate - 0.02; // 剔除通货膨胀

        int leftYears = saveYears + 23; // 多少年后
        System.out.println("个人养老金每年存 " + anualSave + " 万,总共存 " + saveYears + " 年, 总投入 " + anualSave*saveYears);
        System.out.println("年收益率 " + (int)((rate-1)*100) + "%");
        System.out.println("剔除通货膨胀后, 年收益率 " + (int)((rate2-1)*100) + "%");

        double total2 = total;
        double previous = total;
        double previous2 = total;
        for (int j = 0; j < leftYears; j++) {
            int currYear = startYear + j;
            total = getTotal(total, anualSave, saveYears, rate, j, currYear);
            System.out.print(currYear + "年, 总金额是 " + (int) total + ", 比前一年增加 " + (int) (total - previous));
            previous = total;

            total2 = getTotal(total2, anualSave, saveYears, rate2, j, currYear);
            System.out.println("   剔除通货膨胀后, 有效金额是 " + (int) total2 + ", 比前一年增加 " + (int) (total2 - previous2));
            previous2 = total2;
        }
    }

    private static double getTotal(double total, double anualSave, int saveYears, double rate, int j, int currYear) {
        total *= rate;
        if (j < saveYears) {
            total += anualSave;
        }

        if (j == saveYears) {
            System.out.print(" --------- " + currYear + "年开始,不存个人养老金了 ");
        }

        if (currYear == 2052) {
            System.out.print(" --------- " + currYear + "年开始,领个人养老金");
        }
        return total;
    }

    public static void main(String[] args) {
        simulation();
    }
}