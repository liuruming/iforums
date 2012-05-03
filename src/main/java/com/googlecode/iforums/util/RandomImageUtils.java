package com.googlecode.iforums.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author phx
 */
public class RandomImageUtils {

    private String sRand = "";

    private int width = 80;

    private int height = 30;

    private int fontSize = 22;

    private int sRandLength = 4;

    // rand param
    private int bgColorMin = 220;

    private int bgColorMax = 250;

    @SuppressWarnings("unused")
    private int llColorMin = 160;

    @SuppressWarnings("unused")
    private int llColorMax = 200;

    private int slColorMin = 50;

    private int slColorMax = 160;

    private int fontColorMin = 20;

    private int fontColorMax = 130;

    private int slLengthMin = 2;

    private int slLengthMax = 10;

    private int slLengthRange = slLengthMax - slLengthMin;

    private double scaleMin = -0.1;

    private double scaleMax = 0.2;

    private double scaleRange = scaleMax - scaleMin;

    private double shearMin = -0.2;

    private double shearMax = 0.2;

    private double shearRange = shearMax - shearMin;

    private double thetaMin = -0.2;

    private double thetaMax = 0.2;

    private double thetaRange = thetaMax - thetaMin;

    // draw param
    private int x0 = 6;

    private int y0 = height - (height - fontSize);

    private int dw = (width - x0 * 2) / sRandLength;

    private Font randFont = new Font("Times New Roman", Font.PLAIN, fontSize);

    private Color transColor = new Color(0, true);

    private Random random = new Random(System.currentTimeMillis());

    public String getSRand() {
        return sRand;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSRandLength(int sRandLength) {
        this.sRandLength = sRandLength;
    }

    public Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public char getRandChar() {
        
        int rand = random.nextInt(10);
        return (char) (rand + '0');
    }

    public BufferedImage getCharImage(String rand) {
        BufferedImage charImage = new BufferedImage(dw, height,
                BufferedImage.TRANSLUCENT);
        Graphics g = charImage.getGraphics();

        g.setColor(transColor);
        g.fillRect(0, 0, width, height);

        g.setColor(getRandColor(fontColorMin, fontColorMax));
        g.setFont(randFont);
        g.drawString(rand, (int) (scaleMax * dw), y0);

        double scalex = 1 + scaleMin + random.nextDouble() * scaleRange;
        double scaley = 1 + scaleMin + random.nextDouble() * scaleRange;
        double shearx = shearMin + random.nextDouble() * shearRange;
        double theta = thetaMin + random.nextDouble() * thetaRange;

        AffineTransform tx = new AffineTransform();
        tx.scale(scalex, scaley);
        tx.shear(shearx, 0);
        tx.rotate(theta, charImage.getWidth() / 2, charImage.getHeight() / 2);

        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BILINEAR);
        charImage = op.filter(charImage, null);

        return charImage;
    }

    public VerifyImage creatImage() {
        sRand = "";
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(getRandColor(bgColorMin, bgColorMax));
        g.fillRect(0, 0, width, height);

        int slnum = 20 + random.nextInt(20);
        for (int i = 0; i < slnum; i++) {
            g.setColor(getRandColor(slColorMin, slColorMax));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = slLengthMin + random.nextInt(slLengthRange) - slLengthMax / 2;
            int yl = slLengthMin + random.nextInt(slLengthRange) - slLengthMax / 2;
            g.drawLine(x, y, x + xl, y + yl);
        }

        for (int i = 0; i < sRandLength; i++) {
            String rand = "" + getRandChar();
            g.drawImage(getCharImage(rand), dw * i + x0, 0, null);

            sRand += rand;
        }
        g.dispose();
        return new VerifyImage(image, sRand);
    }
    
    public class VerifyImage {
        
        private BufferedImage image;
        
        private String sRand;
        
        public VerifyImage(BufferedImage image, String sRand) {
            this.setImage(image);
            this.setSRand(sRand);
        }

        public void setSRand(String sRand) {
            this.sRand = sRand;
        }

        public String getSRand() {
            return sRand;
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }

        public BufferedImage getImage() {
            return image;
        }
    }
}


