package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拆单匹配阶梯优惠, 满3减1, 满5减3
 */
public class SplitBillMatch {

    int total = 3;
    int subBillNo = 1;
    int solutionNo = 1;
    int maxDiscountAmt = 0;
    Map<Integer, Integer> map = new HashMap<>();

    private void subBillBacktrack(List<Integer> list, int cur) {
        printTabs(cur);
        System.out.print("计数器值为" + cur + ", 商品列表: " + list);
        if (cur == total) {
            if (list.size() == 0) {
                return;
            }
            System.out.println(" 计算子订单" + subBillNo++ + ", " + amtInfo(list));
            return;
        }
        System.out.println();

        printTabs(cur + 1);
        System.out.println("将商品" + (cur + 1) + "放入集合");
        list.add(cur + 1);
        subBillBacktrack(list, cur + 1);
        printTabs(cur + 1);
        System.out.println("将商品" + list.get(list.size() - 1) + "从集合里移除");
        list.remove(list.size() - 1);
        subBillBacktrack(list, cur + 1);
    }

    private String amtInfo(List<Integer> list) {
        // 满3减1, 满5减3
        Integer subBillTotalAmt = list.stream().reduce(Integer::sum).orElse(0);
        Integer subBillDiscountAmt = 0;
        if (subBillTotalAmt >= 5) {
            subBillDiscountAmt = 3;
        } else if (subBillTotalAmt >= 3) {
            subBillDiscountAmt = 1;
        }
        map.put(subBillTotalAmt, subBillDiscountAmt);
        return "子订单总金额: " + subBillTotalAmt + ", 优惠金额: " + subBillDiscountAmt;
    }

    private void printTabs(int cur) {
        for (int i = 0; i < cur; i++) {
            System.out.print("\t");
        }
    }

    private void selectBestBacktrack(List<List<Integer>> groupList, int cur) {
        printTabs(cur);
        System.out.print("计数器值为" + cur + ", 子订单列表: " + groupList);
        if (cur == total) {
            if (groupList.size() == 0) {
                return;
            }
            System.out.println(" 计算方案" + solutionNo++ + ", " + solutionInfo(groupList));
            return;
        }
        System.out.println();

//        printTabs(cur);
        // 将遍历到的商品行依次加入已重组的订单中，并回溯
        for (int i = 0; i < groupList.size(); i++) {
            List<Integer> regroupEffectLines = groupList.get(i);
            printTabs(cur + 1);
            System.out.println("将商品" + (cur + 1) + "放入子订单" + (i + 1));
            regroupEffectLines.add(cur + 1);
            selectBestBacktrack(groupList, cur + 1);
            printTabs(cur + 1);
            System.out.println("将商品" + regroupEffectLines.get(regroupEffectLines.size() - 1) + "从子订单里移除");
            regroupEffectLines.remove(regroupEffectLines.size() - 1);
        }
        // 将当前订单单独加入新的重组订单中，并进入随后的回溯算法中
        List<Integer> newRegroupEffectLinesWrapper = new ArrayList<>();
        printTabs(cur + 1);
        System.out.println("将商品" + (cur + 1) + "放入新的子订单");
        newRegroupEffectLinesWrapper.add(cur + 1);
        groupList.add(newRegroupEffectLinesWrapper);
        printTabs(cur + 1);
        System.out.println("将子订单" + groupList.get(groupList.size() - 1) + "加入当前拆单方案");
        selectBestBacktrack(groupList, cur + 1);
        printTabs(cur + 1);
        System.out.println("将子订单" + groupList.get(groupList.size() - 1) + "从当前拆单方案里移除");
        groupList.remove(groupList.size() - 1);
    }

    private String solutionInfo(List<List<Integer>> groupList) {
        Integer currentTotalDiscount = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < groupList.size(); i++) {
            List<Integer> subBill = groupList.get(i);
            Integer subBillTotalAmt = subBill.stream().reduce(Integer::sum).orElse(0);
            Integer subBillDiscountAmt = map.get(subBillTotalAmt);
            sb.append("子订单" + (i + 1) + ", 金额: " + subBillTotalAmt + ", 优惠金额: " + subBillDiscountAmt + ", ");
            currentTotalDiscount += subBillDiscountAmt;
        }
        sb.append("当前方案总优惠金额: " + currentTotalDiscount);
        maxDiscountAmt = Math.max(maxDiscountAmt, currentTotalDiscount);
        return sb.toString();
    }

    public static void main(String[] args) {
        SplitBillMatch c = new SplitBillMatch();
        c.subBillBacktrack(new ArrayList<>(), 0);
        System.out.println();
        System.out.println();
        c.selectBestBacktrack(new ArrayList<>(), 0);

    }
}