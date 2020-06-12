package test;

import java.util.HashMap;
import java.util.Map;

public class RedPacket {

	// 每个红包最少1分钱
	private static final double minAmount = 1;

	/**
	 * args: amount 红包总金额 num 领取人数
	 *
	 * return 每个人领取的金额
	 */
	public Map<Integer, Integer> shareRedPacket(double amount, int num) {
		double min = minAmount;
		// 最多红包金额占总金额比重
		double maxRate = 0.5;
		if (num <= 2) {
			maxRate = 1;
		}

		double max = amount * maxRate;
		int shareAmount = 0;
		double sum = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < num; i++) {
			min = min > amount - max * (num - i - 1) ? min : amount - max * (num - i - 1);
			max = max < amount - min * (num - i - 1) ? max : amount - min * (num - i - 1);
			shareAmount = (int) Math.floor((max - min) * Math.random() + min);
			amount -= shareAmount;
			sum += shareAmount;
			System.out.println("第" + (i + 1) + "个红包金额是" + shareAmount);
			map.put(i, shareAmount);
		}

		System.out.println("总红包金额是" + sum);
		return map;
	}

	public static void main(String[] args) {
		RedPacket c = new RedPacket();
		Map<Integer, Integer> m = c.shareRedPacket(100, 3);
	}
}
