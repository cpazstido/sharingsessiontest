package com.cf.sessiontest.test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class MatrixToImageWriter {
    /**
     * 生成二维码图片文件
     * @param content 二维码图片中所嵌入的内容
     * @param logoPath 图片文件格式（后缀名）
     * @param width 二维码图片生成的位置
     * @param logoPath logo图片的路径
     * @param width 二维码图片的宽
     * @param height 二维码图片的高
     * @return
     * @return boolean 生成二维码成功返回true
     * @throws WriterException
     * @throws IOException
     */
    public static BufferedImage makeMatrixImg(String content, String logoPath, int width, int height) throws WriterException, IOException {
        /*
         * 1.将内容写入矩阵
         */
        // 设置矩阵所使用的编码格式
        Hashtable<EncodeHintType,String> hints = new Hashtable<EncodeHintType, String>() ;
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 初始化矩阵
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        // 将矩阵图形化
        BufferedImage matrixImage = toBufferredImage(bitMatrix);

        /*
         * 2.往即将生成的‘二维码图+logo’图片中载入logo
         */
        // 2.1 读取logo图片
        BufferedImage logoImage = ImageIO.read(new File(logoPath));

        /*
         * 3。使用二维码图形创建图形工具
         */
        Graphics2D g2d = matrixImage.createGraphics() ;
        /*
         * 4. 画出中间含有logo的二维码图形对象
         */
        int posW = (matrixImage.getWidth()-logoImage.getWidth())/2 ;
        int posH = (matrixImage.getHeight()-logoImage.getHeight())/2 ;
        g2d.drawImage(logoImage, posW, posH, null);
        g2d.dispose();
        logoImage.flush();

        /*
         * 5. 将图片读出到文件中
         */

        //boolean writed = ImageIO.write(matrixImage, format, os) ;
        return matrixImage;
    }

    public static BufferedImage toBufferredImage(BitMatrix bitMatrix){
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i=0; i<width; i++) {
            for(int j=0; j<height; j++){
                image.setRGB(i, j, bitMatrix.get(i, j)?0XFF000000:0XFFFFFF);
            }
        }
        return image;
    }
}
