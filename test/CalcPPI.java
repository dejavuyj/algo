package test;


public class CalcPPI {

    public static int calcPPI(float x, float y, float screenDiagonalSizeInch) {
        return (int) (Math.sqrt(x * x + y * y) / screenDiagonalSizeInch);
    }

    public static void main(String[] args) {
        float[][] arrays_pc = {
                // pc
                {1024, 768, 17}, // 台式机显示器
                {1366, 768, 14}, // 联想G460 / Y410P
                {1920, 1080, 14}, // 联想x1
                {2560, 1600, 13.3f}, // macbook pro 13
                {2560, 1440, 27f}, // 27寸 2k屏
                {3440, 1440, 34f}, // 小米曲面屏
                {3840, 2160, 27f}, // 27寸 4k屏
        };
        float[][] arrays_mobilePhone = {
                // 手机
                {640, 360, 3.2f}, // 诺基亚5230
                {1280, 720, 4.65f}, // 三星GALAXY NEXUS
                {1920, 1080, 5f}, // 小米4
                {1920, 1080, 5.15f}, // 小米5s
                {2160, 1080, 5.99f}, // 小米MIX2
                {2340, 1080, 6.67f}, // 小米10U
                {2520, 1080, 6.56f}, // 小米MIX Fold3 外屏
                {2160, 1916, 8.03f}, // 小米MIX Fold3 内屏
        };
        float[][] arrays_pad = {
                // 平板电脑
                {1024, 768, 7.9f}, // iPad mini1
                {2048, 1536, 7.9f}, // iPad mini2
                {1920, 1200, 11}, // 红米平板SE
                {2880, 1800, 11} // 小米6 (pro)
        };
        float[][] arrays_ink = {
                // 电子书阅读器
                {1024, 768, 6f}, // kindle paperwhite 2
                {1680, 1264, 7f}, // 得到阅读器F7
        };
        System.out.println("PC:");
        for (float[] a : arrays_pc) {
            float x = a[0], y = a[1], screenSize = a[2];
            System.out.println((int) x + "x" + (int) y + " \t" + screenSize + "英寸, \tppi is : " + calcPPI(x, y, screenSize));
        }
        System.out.println("\n手机:");
        for (float[] a : arrays_mobilePhone) {
            float x = a[0], y = a[1], screenSize = a[2];
            System.out.println((int) x + "x" + (int) y + " \t" + screenSize + "英寸, \tppi is : " + calcPPI(x, y, screenSize));
        }
        System.out.println("\n平板电脑:");
        for (float[] a : arrays_pad) {
            float x = a[0], y = a[1], screenSize = a[2];
            System.out.println((int) x + "x" + (int) y + " \t" + screenSize + "英寸, \tppi is : " + calcPPI(x, y, screenSize));
        }
        System.out.println("\n电子书阅读器:");
        for (float[] a : arrays_ink) {
            float x = a[0], y = a[1], screenSize = a[2];
            System.out.println((int) x + "x" + (int) y + " \t" + screenSize + "英寸, \tppi is : " + calcPPI(x, y, screenSize));
        }
    }
}
