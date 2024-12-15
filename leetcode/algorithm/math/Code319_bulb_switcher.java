package leetcode.algorithm.math;

public class Code319_bulb_switcher {

    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n + 0.5);
    }

    public static void main(String[] args) {
        Code319_bulb_switcher c = new Code319_bulb_switcher();
        int n = 10;
        System.out.println(c.bulbSwitch(n));
    }
}