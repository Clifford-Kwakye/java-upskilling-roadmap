package org.example.processor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

public class ImageProcessor implements Processor {
  private final Random random = new Random();

  @Override
  public int[][] trimBorders(int[][] imageTwoD, int pixelCount) {
    if (imageTwoD.length > pixelCount * 2 && imageTwoD[0].length > pixelCount * 2) {
      int[][] trimmedImg =
          new int[imageTwoD.length - pixelCount * 2][imageTwoD[0].length - pixelCount * 2];
      for (int i = 0; i < trimmedImg.length; i++) {
        System.arraycopy(
            imageTwoD[i + pixelCount], pixelCount, trimmedImg[i], 0, trimmedImg[i].length);
      }
      return trimmedImg;
    } else {
      System.out.println("Cannot trim that many pixels from the given image.");
      return imageTwoD;
    }
  }

  @Override
  public int[][] negativeColor(int[][] imageTwoD) {
    int[][] manipulatedImg = new int[imageTwoD.length][imageTwoD[0].length];
    for (int i = 0; i < imageTwoD.length; i++) {
      for (int j = 0; j < imageTwoD[i].length; j++) {
        int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);
        rgba[0] = 255 - rgba[0];
        rgba[1] = 255 - rgba[1];
        rgba[2] = 255 - rgba[2];
        manipulatedImg[i][j] = getColorIntValFromRGBA(rgba);
      }
    }
    return manipulatedImg;
  }

  @Override
  public int[][] stretchHorizontally(int[][] imageTwoD) {
    int[][] manipulatedImg = new int[imageTwoD.length][imageTwoD[0].length * 2];
    int it = 0;
    for (int i = 0; i < imageTwoD.length; i++) {
      for (int j = 0; j < imageTwoD[i].length; j++) {
        it = j * 2;
        manipulatedImg[i][it] = imageTwoD[i][j];
        manipulatedImg[i][it + 1] = imageTwoD[i][j];
      }
    }
    return manipulatedImg;
  }

  @Override
  public int[][] shrinkVertically(int[][] imageTwoD) {
    int[][] manipulatedImg = new int[imageTwoD.length / 2][imageTwoD[0].length];
    for (int i = 0; i < imageTwoD[0].length; i++) {
      for (int j = 0; j < imageTwoD.length - 1; j += 2) {
        manipulatedImg[j / 2][i] = imageTwoD[j][i];
      }
    }
    return manipulatedImg;
  }

  @Override
  public int[][] invertImage(int[][] imageTwoD) {
    int[][] invertedImg = new int[imageTwoD.length][imageTwoD[0].length];
    for (int i = 0; i < imageTwoD.length; i++) {
      for (int j = 0; j < imageTwoD[i].length; j++) {
        invertedImg[i][j] = imageTwoD[(imageTwoD.length - 1) - i][(imageTwoD[i].length - 1) - j];
      }
    }
    return invertedImg;
  }

  @Override
  public int[][] colorFilter(
      int[][] imageTwoD, int redChangeValue, int greenChangeValue, int blueChangeValue) {
    int[][] manipulatedImg = new int[imageTwoD.length][imageTwoD[0].length];
    for (int i = 0; i < imageTwoD.length; i++) {
      for (int j = 0; j < imageTwoD[i].length; j++) {
        int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);

        int newRed = rgba[0] + redChangeValue;
        int newGreen = rgba[1] + greenChangeValue;
        int newBlue = rgba[2] + blueChangeValue;

        if (newRed > 255) {
          newRed = 255;
        } else if (newRed < 0) {
          newRed = 0;
        }

        if (newGreen > 255) {
          newGreen = 255;
        } else if (newGreen < 0) {
          newGreen = 0;
        }

        if (newBlue > 255) {
          newBlue = 255;
        } else if (newBlue < 0) {
          newBlue = 0;
        }

        rgba[0] = newRed;
        rgba[1] = newGreen;
        rgba[2] = newBlue;

        manipulatedImg[i][j] = getColorIntValFromRGBA(rgba);
      }
    }
    return manipulatedImg;
  }

  @Override
  public int[][] paintRandomImage(int[][] canvas) {
    for (int i = 0; i < canvas.length; i++) {
      for (int j = 0; j < canvas[i].length; j++) {
        int[] rgba = {random.nextInt(256), random.nextInt(256), random.nextInt(256), 255};
        canvas[i][j] = getColorIntValFromRGBA(rgba);
      }
    }
    return canvas;
  }

  @Override
  public int[][] paintRectangle(
      int[][] canvas, int width, int height, int rowPosition, int colPosition, int color) {
    for (int i = 0; i < canvas.length; i++) {
      for (int j = 0; j < canvas[i].length; j++) {
        if (i >= rowPosition
            && i <= rowPosition + width
            && (j >= colPosition && j <= colPosition + height)) {
          canvas[i][j] = color;
        }
      }
    }
    return canvas;
  }

  @Override
  public int[][] generateRectangles(int[][] canvas, int numRectangles) {
    for (int i = 0; i < numRectangles; i++) {
      int randomWidth = random.nextInt(canvas[0].length);
      int randomHeight = random.nextInt(canvas.length);
      int randomRowPos = random.nextInt(canvas.length - randomHeight);
      int randomColPos = random.nextInt(canvas[0].length - randomWidth);
      int[] rgba = {random.nextInt(256), random.nextInt(256), random.nextInt(256), 255};
      int randomColor = getColorIntValFromRGBA(rgba);
      canvas =
          paintRectangle(
              canvas, randomWidth, randomHeight, randomRowPos, randomColPos, randomColor);
    }
    return canvas;
  }

  @Override
  public int[][] imgToTwoD(String inputFileOrLink) {
    try {
      BufferedImage image = null;
      if (inputFileOrLink.substring(0, 4).equalsIgnoreCase("http")) {
        URL imageUrl = new URL(inputFileOrLink);
        image = ImageIO.read(imageUrl);
        if (image == null) {
          System.out.println("Failed to get image from provided URL.");
        }
      } else {
        image = ImageIO.read(new File(inputFileOrLink));
      }

      assert image != null;
      int imgRows = image.getHeight();
      int imgCols = image.getWidth();
      int[][] pixelData = new int[imgRows][imgCols];

      for (int i = 0; i < imgRows; i++) {
        for (int j = 0; j < imgCols; j++) {
          pixelData[i][j] = image.getRGB(j, i);
        }
      }

      return pixelData;
    } catch (Exception e) {
      System.out.println("Failed to load image: " + e.getLocalizedMessage());
      return null;
    }
  }

  @Override
  public void twoDToImage(int[][] imgData, String fileName) {
    try {
      int imgRows = imgData.length;
      int imgCols = imgData[0].length;
      BufferedImage result = new BufferedImage(imgCols, imgRows, BufferedImage.TYPE_INT_RGB);

      for (int i = 0; i < imgRows; i++) {
        for (int j = 0; j < imgCols; j++) {
          result.setRGB(j, i, imgData[i][j]);
        }
      }

      File output = new File(fileName);
      ImageIO.write(result, "jpg", output);
    } catch (Exception e) {
      System.out.println("Failed to save image: " + e.getLocalizedMessage());
    }
  }

  @Override
  public int[] getRGBAFromPixel(int pixelColorValue) {
    Color pixelColor = new Color(pixelColorValue);
    return new int[] {
      pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha()
    };
  }

  @Override
  public int getColorIntValFromRGBA(int[] colorData) {
    if (colorData.length == 4) {
      Color color = new Color(colorData[0], colorData[1], colorData[2], colorData[3]);
      return color.getRGB();
    } else {
      System.out.println("Incorrect number of elements in RGBA array.");
      return -1;
    }
  }

  @Override
  public void viewImageData(int[][] imageTwoD) {
    if (imageTwoD.length > 3 && imageTwoD[0].length > 3) {
      int[][] rawPixels = new int[3][3];
      for (int i = 0; i < 3; i++) {
        System.arraycopy(imageTwoD[i], 0, rawPixels[i], 0, 3);
      }
      System.out.println("Raw pixel data from the top left corner.");
      System.out.print(Arrays.deepToString(rawPixels).replace("],", "],\n") + "\n");

      int[][][] rgbPixels = new int[3][3][4];
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          rgbPixels[i][j] = getRGBAFromPixel(imageTwoD[i][j]);
        }
      }
      System.out.println();
      System.out.println("Extracted RGBA pixel data from top the left corner.");

      for (int[][] row : rgbPixels) {
        System.out.print(Arrays.deepToString(row) + System.lineSeparator());
      }
    } else {
      System.out.println(
          "The image is not large enough to extract 9 pixels from the top left corner");
    }
  }
}
