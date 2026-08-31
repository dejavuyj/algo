package tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalcPPI {

    private final static List<String> tableHead = Arrays.asList("设备型号", "分辨率", "屏幕尺寸", "ppi");

    public static int calcPPI(float x, float y, float screenDiagonalSizeInch) {
        return (int) (Math.sqrt(x * x + y * y) / screenDiagonalSizeInch);
    }

    // 数组结构：{设备型号, 横向分辨率, 纵向分辨率, 屏幕尺寸(英寸)}
    private final static String[][] arrays_pc = {
            {"台式机显示器", "800", "600", "14"},
            {"台式机显示器", "1024", "768", "17"},
            {"联想G460 / Y410P", "1366", "768", "14"},
            {"联想x1", "1920", "1080", "14"},
            {"MacBook Pro 13", "2560", "1600", "13.3"},
            {"23.8寸 2k屏", "1920", "1080", "23.8"},
            {"27寸 2k屏", "2560", "1440", "27"},
            {"小米曲面屏", "3440", "1440", "34"},
            {"27寸 4k屏", "3840", "2160", "27"},
            {"联想拯救者 R9000P", "2560", "1600", "16"},
            {"小米Book Pro 14", "3120", "2080", "14.6"},
    };

    private final static String[][] arrays_mobilePhone = {
            {"摩托罗拉E375", "176", "220", "1.8"},
            {"诺基亚5230", "640", "360", "3.2"},
            {"三星GALAXY NEXUS", "1280", "720", "4.65"},
            {"小米4", "1920", "1080", "5"},
            {"小米5s", "1920", "1080", "5.15"},
            {"小米MIX2", "2160", "1080", "5.99"},
            {"小米10U", "2340", "1080", "6.67"},
            {"小米MIX Fold3 外屏", "2520", "1080", "6.56"},
            {"小米MIX Fold3 内屏", "2160", "1916", "8.03"},
    };

    private final static String[][] arrays_pad = {
            {"iPad mini1", "1024", "768", "7.9"},
            {"iPad mini2", "2048", "1536", "7.9"},
            {"红米平板 SE", "1920", "1200", "11"},
            {"小米6 (pro)", "2880", "1800", "11"},
            {"Surface Go", "1800", "1200", "10"},
            {"酷比魔方 掌玩mini", "1920", "1200", "8.4"},
            {"红米平板2 SE", "2048", "1280", "9.7"},
    };

    private final static String[][] arrays_ink = {
            {"Kindle Paperwhite 2", "1024", "768", "6"},
            {"海信 TOUCH Lite", "1440", "720", "5.84"},
            {"得到阅读器F7", "1680", "1264", "7"},
            {"墨案迷你阅 Plus", "1440", "720", "5.84"},
    };

    private static void printGroup(String groupName, String[][] devices) {
        System.out.println(groupName + ":");

        List<List<String>> datas = new ArrayList<>();
        for (String[] device : devices) {
            String model = device[0];
            float x = Float.parseFloat(device[1]);
            float y = Float.parseFloat(device[2]);
            float size = Float.parseFloat(device[3]);
            int ppi = calcPPI(x, y, size);
            List<String> data = new ArrayList<>();
            data.add(model);
            data.add(device[1] + " x " + device[2]);
            data.add(device[3]);
            data.add(String.valueOf(ppi));
            datas.add(data);
//            System.out.printf("-%s\t%d x %d\t%.1f英寸, ppi is : %d%n", model, (int)x, (int)y, size, ppi);
        }
        PrintTable.printTableForList(tableHead, datas);
        System.out.println();
    }

    public static void main(String[] args) {
        printGroup("PC", arrays_pc);
        printGroup("手机", arrays_mobilePhone);
        printGroup("平板电脑", arrays_pad);
        printGroup("电子书阅读器", arrays_ink);
    }
}