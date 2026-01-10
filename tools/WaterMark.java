//package tools;
//
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.*;
//
//public class WaterMark {
//
//    public static final String DIAGONAL_SINGLE = "DiagonalSingle";
//    public static final String HORIZONTAL_SINGLE = "HorizontalSingle";
//    public static final String DIAGONAL_MULTI = "DiagonalMulti";
//
//    /**
//     * 水印
//     */
//    public void waterMark(String text, String srcImgPath,
//                          String targetPath, String mode) throws IOException {
//        System.out.println(text);
//        // 1、读取源图片，Image获取图片宽度、高度
//        Image srcImg;
//        if (srcImgPath == null) {
//            int width = 300, height = 200;
//            BufferedImage baseImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g2d = baseImg.createGraphics();
//            g2d.setComposite(AlphaComposite.Clear);
//            g2d.fillRect(0, 0, width, height); // Area to make transparent
//
////            g2d.setColor(Color.BLUE);
////            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_ATOP, 0.5f));
////            g2d.setFont(new Font("微软雅黑", Font.BOLD, 50));
////            //10、水印文字开始，文字内容，x,y坐标
////            g2d.drawString(logoText, 100, 80);
//
//            g2d.dispose();
//
//            ImageIO.write(baseImg, "png", new File("images/base.png"));
//            srcImg = baseImg;
//        } else {
//            srcImg = ImageIO.read(new File(srcImgPath));
//        }
//        BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_ARGB);
//
//        // 2、得到画笔对象
//        Graphics2D graphics = buffImg.createGraphics();
//
//        // 3、设置对线段的锯齿状边缘处理
//        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        graphics.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
//
//        // 4、设置水印倾斜度，这里是在图片的对角线上
//
//        // 对角线长度lengthOfDiagonal
//        double lengthOfDiagonal = Math.sqrt(Math.pow(buffImg.getWidth(), 2) + Math.pow(buffImg.getHeight(), 2));
//        double v = (Math.pow(buffImg.getWidth(), 2) + Math.pow(lengthOfDiagonal, 2) - Math.pow(buffImg.getHeight(), 2)) / (2 * buffImg.getWidth() * lengthOfDiagonal);
//        // get到了一个弧度数
//        double acos = Math.acos(v);
//        double myDegree = Math.toDegrees(acos);
//
//        // 5、设置水印文字颜色
////        graphics.setColor(Color.DARK_GRAY);
//        graphics.setColor(Color.BLUE);
//
//        // 6、获取源图片的宽度和高度
//        int width = srcImg.getWidth(null);
//        int height = srcImg.getHeight(null);
//
//        //8、设置透明度
//        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_ATOP, 0.2f));
//
//        if (DIAGONAL_SINGLE.equals(mode)
//                || HORIZONTAL_SINGLE.equals(mode)) {
//            int fontSize = (int) (Math.max(width, height) / text.length() / 1.2);
//            // 设置水印文字大小
//            graphics.setFont(new Font("微软雅黑", Font.BOLD, fontSize));
//
//            //9、设置文字位置
////            FontDesignMetrics metrics = FontDesignMetrics.getMetrics(graphics.getFont());
//            //获取文字宽度
//            int strWidth = metrics.stringWidth(text);
//
//            int strHeight = metrics.getHeight();
//            //文字在画布上的坐标
//            int left = Math.max((width - strWidth) / 2, strHeight / 2);
//            int top = 0;
//            if (DIAGONAL_SINGLE.equals(mode)) {
//                //这里的负号决定对角线-Math.toRadians(myDegree)
//                graphics.rotate(-Math.toRadians(myDegree),
//                        (double) buffImg.getWidth() / 2,
//                        (double) buffImg.getHeight() / 2);
//            }
//            //有条基线，加上ascent，相当于画布要向上移动（或是文字向下移），才能保证文字是居中的
//            top = (height - strHeight) / 2 + metrics.getAscent();
//
//            //10、水印文字开始，文字内容，x,y坐标
//            graphics.drawString(text, left, top);
//
//        } else if (DIAGONAL_MULTI.equals(mode)) {
//            //这里的负号决定对角线-Math.toRadians(myDegree)
//            graphics.rotate(-Math.toRadians(myDegree),
//                    (double) buffImg.getWidth() / 2,
//                    (double) buffImg.getHeight() / 2);
//
//            int fontSize = width / 20;
//            // 设置水印文字大小
//            graphics.setFont(new Font("微软雅黑", Font.BOLD, fontSize));
//            //9、设置文字位置
//            FontDesignMetrics metrics = FontDesignMetrics.getMetrics(graphics.getFont());
//            //获取文字宽度
//            int strWidth = metrics.stringWidth(text);
//
//            int split = 30;
//            int xNum = width / strWidth + 1;
//            int yNum = height / split + 1;
//            for (int i = 1; i <= 2 * yNum; i++) {
//                int y = -height + 50 * i + 5 * split * i;
//                for (int j = 0; j < xNum; j++) {
//                    int x = strWidth * j + 3 * split * j;
//                    graphics.drawString(text, x, y);
//                }
//            }
//        }
//
//        //11、释放资源
//        graphics.dispose();
//
//        //12、生成图片
//        try {
//            OutputStream os = new FileOutputStream(targetPath);
//            ImageIO.write(buffImg, "png", os);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void svg(String text, int fontSize, String mode, String targetPath) throws IOException {
//        int width = (int) (text.length() * fontSize * 1.2);
//        int height = width;
//        int x = width / 10;
//        int y = (height + fontSize) / 2;
//        String rotate = DIAGONAL_SINGLE.equals(mode) ? String.format("transform=\"rotate(-45, %d, %d)\"", width/2, y) : "";
//
//        String svgContext = String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%d\" height=\"%d\">" +
//                "    <text x=\"%d\" y=\"%d\" font-size=\"%d\" fill=\"rgba(204,204,204, 0.8)\" %s>%s</text>" +
//                "</svg>", width, height, x, y, fontSize, rotate, text
//                );
//        try (FileWriter fileWriter = new FileWriter(targetPath)) {
//            fileWriter.append(svgContext);
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        WaterMark m = new WaterMark();
////        String text = "AA水印文字CCDD";
//        String text = "水印文字水印文字水印文字";
////        m.waterMark(text, null, "images/PureWaterMark.png", DIAGONAL_SINGLE);
////        m.waterMark(text, "images/Bill.png", "images/BillWaterMark.png", HORIZONTAL_SINGLE);
//        m.svg(text, 20, DIAGONAL_SINGLE, "images/diagonal.svg");
//        m.svg(text, 20, HORIZONTAL_SINGLE, "images/horizontal.svg");
//    }
//}
