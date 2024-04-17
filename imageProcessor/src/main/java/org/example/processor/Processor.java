package org.example.processor;

public interface Processor {
  int[][] trimBorders(int[][] imageTwoD, int pixelCount);

  int[][] negativeColor(int[][] imageTwoD);

  int[][] stretchHorizontally(int[][] imageTwoD);

  int[][] shrinkVertically(int[][] imageTwoD);

  int[][] invertImage(int[][] imageTwoD);

  int[][] colorFilter(
      int[][] imageTwoD, int redChangeValue, int greenChangeValue, int blueChangeValue);

  int[][] paintRandomImage(int[][] canvas);

  int[][] paintRectangle(
      int[][] canvas, int width, int height, int rowPosition, int colPosition, int color);

  int[][] generateRectangles(int[][] canvas, int numRectangles);

  int[][] imgToTwoD(String inputFileOrLink);

  void twoDToImage(int[][] imgData, String fileName);

  int[] getRGBAFromPixel(int pixelColorValue);

  int getColorIntValFromRGBA(int[] colorData);

  void viewImageData(int[][] imageTwoD);
}
