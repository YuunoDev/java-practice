
import java.util.HashSet;
import java.util.Set;

public class Conjunto {
    public static void main(String[] args) {
        //lista de conjuntos no se grega algo que ya existe en el 
        Set<Integer> number = new HashSet<>();

        number.add(10);
        number.add(20);

        System.out.println(number.size());
        System.out.println(number.toString());

        boolean add = number.add(10);

        System.out.println(add);
        //buscar si contiene algo en la lista
        System.out.println(number.contains(10));

        //elimina el objeto indicado
        number.remove(20);
        System.out.println(number.toString());
    }
}
