
import java.util.HashMap;
import java.util.Map;

public class Mapas {
    public static void main(String[] args) {
        Map<String, Integer> edades = new HashMap<>();

        edades.put("Adrian", 22);
        edades.put("Carlos", 23);
        edades.put("Andres",23);

        System.out.println(edades.toString());

        // atraer el dato de Adrian
        System.out.println(edades.get("Adrian"));

        // si se ingresa otra vez el nombre y otro valor se cambia el anterior
        edades.put("Adrian", 23);

        boolean flagclave = edades.containsKey("Adrian");
        System.out.println(flagclave);

        boolean flagvalor = edades.containsValue(23);
        System.out.println(flagvalor);

        edades.remove("Andres");
        System.out.println(edades.toString());
    }
}
