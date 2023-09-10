package tools;

public class InsuranceCalculate {

    private static void criticalIllnessCalculate() {
        // 重疾险, 保额50万, 一年4800, 2020年开始缴费, 交20年, 保到70岁
        int startYear = 2024;
        int endYear = 2040;
        int totalYears = endYear - startYear;
        double annualInsuranceCost = 0.48;
        double rate = 1.06; // 投资收益率

        double total = 0;
        for (int i = startYear; i < endYear; i++) {
            int accumulateYears = endYear - i;
            double currYearGains = annualInsuranceCost * Math.pow(rate, accumulateYears);
            System.out.println(i + "年的 " + annualInsuranceCost + " 万, 经过 " + accumulateYears + " 年积累, 总收益 " + currYearGains + " 万");
            total += currYearGains;
        }
        System.out.println(totalYears + " 年投入的 " + annualInsuranceCost * totalYears + " 万, 总收益 " + total + " 万");
    }

    public static void main(String[] args) {
        criticalIllnessCalculate();
    }
}