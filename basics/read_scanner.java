package basics;
import java.util.Scanner;

public class read_scanner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("¿Como te llamas? ");
            String name = scanner.nextLine();

            System.out.println("Name: "+name);
        }

    }
}
