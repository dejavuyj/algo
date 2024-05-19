package tools;


public class CalcInvest {

    public static double calcCostPrice(double earnings, double currentPrice, double quantity) {
        return currentPrice - (earnings / quantity);
    }

    public static void main(String[] args) {
        // 长江电力
        double earnings = 114562;
        double currentPrice = 25.58;
        double quantity = 21400;
//        double quantity = 30000;
        double costPrice = calcCostPrice(earnings, currentPrice, quantity);
        System.out.printf("%.4f%n", costPrice);

        // 华能水电
        earnings = 81575.63;
        currentPrice = 9.84;
        quantity = 67700;
        costPrice = calcCostPrice(earnings, currentPrice, quantity);
        System.out.printf("%.4f%n", costPrice);
    }
}
