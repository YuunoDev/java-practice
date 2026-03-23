package basics;
import java.util.Scanner;

public class read_scanner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("¿Como te llamas? ");
            String name = scanner.nextLine();

            System.out.println("Name: "+name);

            int edad = scanner.nextInt();
            System.out.println(edad);
            scanner.nextLine(); // "Limpia" el salto de línea pendiente

            String nombre = scanner.nextLine(); // Ahora sí lee el nombre correctamente
            System.out.println(nombre);
        }

    }
}
