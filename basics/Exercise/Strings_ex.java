package basics.Exercise;
public class Strings_ex {
    public static void main(String[] args) {
        String fraseString = "Aprendiendo Java";
        char caracter = fraseString.charAt(10); // sacar caracter 10
        System.out.println("Caracter de frase: "+caracter);
        
        String palabraString = fraseString.substring(12,16);//sacar un rango del string
        System.out.println("Palabra: "+palabraString);


        //

        System.out.println("Contiene \"Java\": "+fraseString.contains("Java"));
        System.out.println("Position Java: "+fraseString.indexOf("Java"));
        System.out.println(fraseString.toLowerCase());
        System.out.println(fraseString.toUpperCase());
        System.out.println(fraseString.replace("Java", "Python"));
        System.out.println("length string: "+fraseString.length());
    }
}
