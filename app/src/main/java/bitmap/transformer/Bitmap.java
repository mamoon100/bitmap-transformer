package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Bitmap {
    private final String imgName;
    private String imgPath;
    private String savePath;
    private BufferedImage img = null;
    private List<BufferedImage> oldImg = new ArrayList<>();

    public Bitmap(String imgPath, String savePath) {
        this.imgPath = imgPath;
        this.savePath = savePath;
        this.imgName = (this.imgPath.split("/"))[imgPath.split("/").length - 1].split("\\.")[0];
        try {
            this.img = ImageIO.read(new File(this.imgPath));
        } catch (IOException e) {
            System.out.println("\n\t\t\t****Please make sure the path is correct****\n");
            e.printStackTrace();
        }
        this.oldImg.add(this.img);
    }

    public String getImgName() {
        return imgName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public BufferedImage getImg() {
        return img;
    }

    public int randomNumber(int lower, int upper) {
        return (int) (Math.random() * (upper - lower)) + lower;
    }

    public void invert() {
        for (int i = 0; i < this.img.getWidth(); i++) {
            for (int j = 0; j < this.img.getHeight(); j++) {
                Color invertedPixel = new Color(this.img.getRGB(i, j), true);
                invertedPixel = new Color(255 - invertedPixel.getRed(), 255 - invertedPixel.getGreen(), 255 - invertedPixel.getBlue());
                this.img.setRGB(i, j, invertedPixel.getRGB());
            }
        }
    }

    public void randomize() {
        // create set to avoid duplicate and fast the searching for the certain pixel.
        Map<Color, ArrayList<Integer>> randomColor = new HashMap<>();
        for (int i = 0; i < this.img.getWidth(); i++) {
            for (int j = 0; j < this.img.getHeight(); j++) {
                Color pixelColor = new Color(this.img.getRGB(i, j), true);
                // make sure that every duplicate pixel have the same color instead of random colored shaped bmp
                if (!randomColor.containsKey(pixelColor)) {
                    // assign random rgb to every pixle that contain the same color

                    ArrayList<Integer> rgb = new ArrayList<Integer>();
                    rgb.add(randomNumber(0, 255));
                    rgb.add(randomNumber(0, 255));
                    rgb.add(randomNumber(0, 255));
                    randomColor.put(pixelColor, rgb);
                }

                pixelColor = new Color(randomColor.get(pixelColor).get(0), randomColor.get(pixelColor).get(1), randomColor.get(pixelColor).get(2));
                this.img.setRGB(i, j, pixelColor.getRGB());
            }
        }
    }

    public void blackAndWhite() {
        // create set to avoid duplicate and fast the searching for the certain pixel.
        Map<Color, Integer[]> blackAndWhite = new HashMap<>();

        for (int i = 0; i < this.img.getWidth(); i++) {
            for (int j = 0; j < this.img.getHeight(); j++) {
                Color pixelColor = new Color(this.img.getRGB(i, j), true);
                // make sure that every duplicate pixel have the same color instead of random colored shaped bmp
                if (!blackAndWhite.containsKey(pixelColor)) {
                    Integer[] bnw = (pixelColor.getRed() + pixelColor.getBlue() + pixelColor.getGreen()) / 3 >= 255
                            ? new Integer[]{255, 255, 255} :
                            new Integer[]{160, 160, 160};
                    blackAndWhite.put(pixelColor, bnw);
                }
                pixelColor = new Color(blackAndWhite.get(pixelColor)[0], blackAndWhite.get(pixelColor)[1], blackAndWhite.get(pixelColor)[2]);
                this.img.setRGB(i, j, pixelColor.getRGB());
            }
        }
    }


    public boolean saveImg() {
        try {
            ImageIO.write(this.img, "bmp", new File(this.savePath + "/Rendered" + this.imgName + ".bmp"));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
