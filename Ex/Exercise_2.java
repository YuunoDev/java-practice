package Ex;

public class Exercise_2 {
    public static void main(String[] args) {
        double base = 4;
        double height = 5;

        System.out.println("Area: "+(base*height));
        System.out.println("Perimeter: "+(2*(base+height)));

        Empleado emp1 = new Empleado("Adrian", 2000);

        System.out.println(emp1.toString());
    }
}
