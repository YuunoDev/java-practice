package basics.OOP.Anonymous;

interface vehicle {
    public void move();
}

public class an {

    public static void main(String[] args) {
        vehicle mustang = new vehicle() {
            public void move() {
                System.out.println("Moveeeeeeee!!!!");
            }
        };

        mustang.move();
    }
}
