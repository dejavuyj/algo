package tools;


public class CalcInvest {

    public static double calcCostPrice(double earnings, double currentPrice, double quantity) {
        return currentPrice - (earnings / quantity);
    }

    public static void main(String[] args) {
        double earnings = 115519.76;
        double currentPrice = 25.85;
        double quantity = 9500;
//        double quantity = 30000;
        double costPrice = calcCostPrice(earnings, currentPrice, quantity);
        System.out.printf("%.4f%n", costPrice);

        earnings = 81575.63;
        currentPrice = 9.84;
        quantity = 67700;
        costPrice = calcCostPrice(earnings, currentPrice, quantity);
        System.out.printf("%.4f%n", costPrice);
    }
}
