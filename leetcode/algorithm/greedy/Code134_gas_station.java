package leetcode.algorithm.greedy;

public class Code134_gas_station {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] < cost[i] || gas[i] == 0) {
                continue;
            }
            int leftGas = 0;
            int j = i;
            int cnt = gas.length;
            while (cnt-- > 0) {
                leftGas += gas[j];
                leftGas -= cost[j];

                if (leftGas < 0) {
                    break;
                }
                j = (j + 1 == gas.length) ? 0 : j + 1;
            }
            if (j == i) {
                return i;
            }
        }
        return -1;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n) {
            int sumOfGas = 0, sumOfCost = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return i;
            } else {
                i = i + cnt + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Code134_gas_station c = new Code134_gas_station();
        int[] gas = {2, 3, 4};
        int[] cost = {3, 4, 3};
        System.out.println(c.canCompleteCircuit(gas, cost));
    }
}