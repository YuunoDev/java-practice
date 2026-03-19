package basics;
import java.util.Scanner;

public class prov {
  public static void main(String[] args) {
    try (Scanner lectura = new Scanner(System.in)) {
      System.out.print("Row: ");
      int r = lectura.nextInt();
      System.out.print("Col: ");
      int c = lectura.nextInt();
      int[][] Matrix = new int[r][c];
      int mul = r * c;

      // espiral llenado
      int x = 1;
      int top = 0, bottom = r - 1, left = 0, right = c - 1;
      while (x <= mul) {
        for (int i = left; i <= right && x <= mul; i++) {
          Matrix[top][i] = x;
          x++;
        }
        top++;
        for (int i = top; i <= bottom && x <= mul; i++) {
          Matrix[i][right] = x;
          x++;
        }
        right--;
        for (int i = right; i >= left && x <= mul; i--) {
          Matrix[bottom][i] = x;
          x++;
        }
        bottom--;
        for (int i = bottom; i >= top && x <= mul; i--) {
          Matrix[i][left] = x;
          x++;
        }
        left++;
      }

      for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
          System.out.print(Matrix[i][j] + " ");
        }
        System.out.println(" ");
      }
    }
  }
}
