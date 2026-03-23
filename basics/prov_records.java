package basics;

public class prov_records {
    public record Person(String name, int age) { // Java auto-generates: constructor, getters, equals(), hashCode(), and
                                                 // toString().

    }

    public record Circle(double radius) {
        public double area() {
            return Math.PI * radius * radius;
        }
    }

    public record Product(String name, double price) {
        // Compact constructor — no need to repeat parameters
        public Product {
            if (price < 0)
                throw new IllegalArgumentException("Price cannot be negative");
            name = name.trim(); // you can normalize fields here
        }
    }

    public interface Describable {
        String describe();
    }

    public record Point(int x, int y) implements Describable {
        @Override
        public String describe() {
            return "Point at (" + x + ", " + y + ")";
        }
    }

    public static void main(String[] args) {

        // Usage
        Person p = new Person("Alice", 30);
        System.out.println(p.name()); // Alice
        System.out.println(p.age()); // 30
        System.out.println(p); // Person[name=Alice, age=30]
        System.out.println("");
        // Usage
        Circle c = new Circle(5.0);
        System.out.println(c.area()); // 78.53...
        System.out.println("");
        // Usage
        Product pr = new Product("  Laptop  ", 999.99);
        System.out.println(pr.name()); // "Laptop" (trimmed)
        System.out.println("");
        // Usage
        Point pt = new Point(3, 7);
        System.out.println(pt.describe()); // Point at (3, 7)
        System.out.println("");

        // it can create a record in class, and it is used only in this class
        record OrderSummary(String id, double total) {
        }
        var summary = new OrderSummary("ORD-001", 250.0);
        System.out.println(summary); // OrderSummary[id=ORD-001, total=250.0]
    }
}
