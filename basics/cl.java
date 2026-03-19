package basics;
public class cl {
    private String name;
    String color;
    int num;

    public cl(String name, String color){
        this.name = name;
        this.color = color;
        this.num = 3;
    }

    public cl(){
        this.name = "";
        this.color = "";
        this.num = 0;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        String s = "Name: "+name+"\nNumero: "+num+"\nColor: "+color;
        return s;
    }


    public static void main(String[] args) {
        cl coche = new cl();
        coche.name = "Mustang";
        coche.num = 2;
        coche.color= "Rojo";

        System.out.println(coche.toString());

        // Prueba  - 2026/03/09 09:48:03

        
        cl muestra = new cl("Adrian", "Azul");
        System.out.println(muestra.toString());
    }
}
