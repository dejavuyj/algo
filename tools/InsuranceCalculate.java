package tools;

public class InsuranceCalculate {

    private static void medicalCalculate() {
        // 好医保 长期医疗
        int[] renewalFee1 = {
                479,479,479,479,479, // 36-40
                609,609,609,609,609, // 41-45
                873,873,873,873,873, // 46-50
                1172,1172,1172,1172,1172, // 51-55
                1599,1599,1599,1599,1599, // 56-60
                2541,2541,2541,2541,2541, // 61-65
                3368,3368,3368,3368,3368, // 66-70
                4349,4349,4349,4349,4349, // 71-75
                5463,5463,5463,5463,5463, // 76-80
                6360,6360,6360,6360,6360, // 81-85
                7797,7797,7797,7797,7797, // 86-90
                9493,9493,9493,9493,9493, // 91-95
                11387,11387,11387,11387,11387 // 96-100
        };

        // 好医保 长期医疗（0免赔）
        int[] renewalFee2 = {
                586,586,586,586,586, // 36-40
                832,832,832,832,832, // 41-45
                1132,1132,1132,1132,1132, // 46-50
                1650,1650,1650,1650,1650, // 51-55
                2087,2087,2087,2087,2087, // 56-60
                3031,3031,3031,3031,3031, // 61-65
                3857,3857,3857,3857,3857, // 66-70
                4827,4827,4827,4827,4827, // 71-75
                5942,5942,5942,5942,5942, // 76-80
                6818,6818,6818,6818,6818, // 81-85
                8258,8258,8258,8258,8258, // 86-90
                9985,9985,9985,9985,9985, // 91-95
                11875,11875,11875,11875,11875 // 96-100
        };

        // 好医保 长期医疗（旗舰版）
        int[] renewalFee3 = {
                689,690,734,758,879, // 36-40
                899,952,999,1044,1249, // 41-45
                1299,1331,1457,1595,1729, // 46-50
                1776,1855,1991,2100,2337, // 51-55
                2718,2729,2736,2749,3269, // 56-60
                3721,3845,3968,3893,4093, // 61-65
                4935,4940,4944,4950,5106, // 66-70
                6490,6496,6503,6508,6605 // 71-75
                // 76以上费率表无数据
        };

        int startAge = 36;
        int endAge = 55;
        System.out.printf("起始年龄%d, 截止年龄%d, 3款长期医疗续保费用分别为:%n%d%n%d%n%d%n", startAge, endAge,
                renewalFeeCalc(startAge, endAge, renewalFee1),
                renewalFeeCalc(startAge, endAge, renewalFee2),
                renewalFeeCalc(startAge, endAge, renewalFee3));
    }

    private static int renewalFeeCalc(int startAge, int endAge, int[] renewalFee) {
        int startIndex = startAge - 36;
        int endIndex = endAge - 36;

        int totalRenewalFee = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            totalRenewalFee += renewalFee[i];
        }
        return totalRenewalFee;
    }

    private static void criticalIllnessCalculate() {
        // 重疾险, 保额50万, 一年4800, 2020年开始缴费, 交20年, 保到70岁
        int startYear = 2024;
        int endYear = 2040;
        int totalYears = endYear - startYear;
        double annualInsuranceCost = 0.48;
        double rate = 1.1; // 投资收益率

        double total = 0;
        for (int i = startYear; i < endYear; i++) {
            int accumulateYears = endYear - i;
            double currYearGains = annualInsuranceCost * Math.pow(rate, accumulateYears);
            System.out.println(i + "年的 " + annualInsuranceCost + " 万, 经过 " + accumulateYears + " 年积累, 总收益 " + currYearGains + " 万");
            total += currYearGains;
        }
        System.out.println("投入 " + annualInsuranceCost * totalYears + " 万, 总收益 " + total + " 万");
    }

    public static void main(String[] args) {
//        medicalCalculate();
        criticalIllnessCalculate();
    }
}