import java.io.IOException;

public class Display {

  public static void printArray2(int[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        System.out.print(arr[i][j]+" ");
      }
      System.out.println();
    }
  }

  public static int[][] negatif(int[][] img) {
    int[][] negatif = new int[img.length][img[0].length];
    for (int i = 0; i < img.length; i++) {
      for (int j = 0; j < img[i].length; j++) {
        negatif[i][j] = 255 - img[i][j];
      }
    }
    return negatif;
  }

  public static int[][] flip(int[][] img) {
    int[][] fliped = new int[img.length][img[0].length];
    for (int i = 0; i < fliped.length; i++) {
      for (int j = 0; j < fliped[i].length; j++) {
        fliped[i][j] = img[img.length - 1 - i][j];
      }
    }
    return fliped;
  }
  
  public static int[][] bords(int[][] img) {
    int[][] bords = new int[img.length][img[0].length];
    img = flip(img);
    for (int i = 0; i < bords.length; i++) {
      for (int j = 0; j < bords[i].length; j++) {
        bords[i][j] = 255;
      }
    }
    for (int i = 1; i < bords.length - 1; i++) {
      for (int j = 1; j < bords[i].length - 1; j++) {
        int a = img[i-1][j] - img[i][j];
        a = a < 0 ? -a : a;
        int b = img[i][j-1] - img[i][j];
        b = b < 0 ? -b : b;
        int d = a + b;
        bords[i][j] = d > 5 ? 0 : 255;
      }
    }
    img = flip(img);
    bords = flip(bords);
    return bords;
  }

  public static void drawASCII(int[][] img) {
    for (int j = 0; j < img.length; j++) {
      for (int i = 0; i < img[j].length; i++) {
        if (img[i][j] < 26) {
          System.out.print('@');
        } else if (img[i][j] < 52) {
          System.out.print('%');
        } else if (img[i][j] < 78) {
          System.out.print('#');
        } else if (img[i][j] < 104) {
          System.out.print('*');
        } else if (img[i][j] < 130) {
          System.out.print('+');
        } else if (img[i][j] < 156) {
          System.out.print('=');
        } else if (img[i][j] < 182) {
          System.out.print('-');
        } else if (img[i][j] < 208) {
          System.out.print(':');
        } else if (img[i][j] < 234) {
          System.out.print('.');
        } else {
          System.out.print(' ');
        }
      }
      System.out.println();
    }
  }

  public static void main(String[] args) throws IOException {
    int[][] img = getGray("icon.gif");
    saveGray(bords(img), "test.png");
    drawASCII(img);
  }


  public static int [][] getGray(String filename) throws IOException {
      Picture p = new Picture(filename);
      return p.getGray();
  }

  public static void drawGray(int [][] gray) {
    Picture p = new Picture(gray);
    p.draw();
  }


  public static void saveGray(int [][] gray, String filename) throws IOException {
    Picture p = new Picture(gray);
    p.save(filename);
  }



}
