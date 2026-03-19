package basics;


import java.util.Scanner;

public class pro_static {
    public static double pi = 3.1416;

    public static double pow(double base, double exponent){
        double result= 1;
        
        for (; exponent != 0; --exponent) {
            result *= base;
        }

        return result;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            double radius = 0.0;


            System.out.println("Give me the radius: ");
            radius = scanner.nextDouble();
            
            System.out.println("Area: "+(pro_static.pi*(pro_static.pow(radius, 2))));
        }

    } 
}
