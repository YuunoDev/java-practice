package basics;
public class Constantes {
    //     static final tipo_de_dato NOMBRE_DE_LA_CONSTANTE = valor;
    public static final int days = 7;
    
    // public → accesible desde cualquier clase
    // static → pertenece a la clase, no a instancias individuales (no necesitas crear un objeto para usarla)
    // final → su valor queda fijo, si intentas hacer PI = 3.14; en otro lugar, el compilador marca error

    public static void main(String[] args) {
        int most = 10;
        //final tipo_de_dato NOMBRE_DE_LA_CONSTANTE = valor;
          final int p = 3; // solo es constante en este bloque de codigo


        System.out.println(most + p);
        most = 12;
        System.out.println(most+ " const "+days);

    }
}
