import java.util.Scanner;

public class Mat2 {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Matriz A: ");
            System.out.print("Row: ");
            int r1 = scan.nextInt();
            System.out.print("Col:");
            int c1 = scan.nextInt();

            System.out.println("Matriz B: ");
            System.out.print("Row: ");
            int r2 = scan.nextInt();
            System.out.print("Col:");
            int c2 = scan.nextInt();

            if (r1 == c2 && r2 == c1) {
                int[][] matrixA = new int[r1][c1];
                int[][] matrixB = new int[r2][c2];

                System.out.println("Matriz A: ");
                for (int i = 0; i < r1; i++) {
                    for (int j = 0; j < c1; j++) {
                        System.out.print(" [" + i + "]" + " [" + j + "]: ");
                        int num = scan.nextInt();
                        matrixA[i][j] = num;
                    }
                }
                System.out.println("Matriz B: ");
                for (int i = 0; i < r2; i++) {
                    for (int j = 0; j < c2; j++) {
                        System.out.print(" [" + i + "]" + " [" + j + "]: ");
                        int num = scan.nextInt();
                        matrixB[i][j] = num;
                    }
                }

                int[][] matrixC = new int[r1][r1];
                int res = 0;
                int i=0,x=0;
                while(i<=r1){
                     for (int j = 0; j < c1; j++) {
                        res += matrixA[i][j] * matrixB[j][i];
                    }
                    matrixC[x][i] = res;
                    i++;
                }

                for (int k = 0; k < r1; k++) {
                    for (int j = 0; j < r1; j++) {
                        System.out.print(matrixC[k][j] + " ");
                    }
                    System.out.println(" ");
                }

            } else {
                System.out.println("No son compatibles");
            }
        }
    }
}
