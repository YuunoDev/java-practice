package basics;
public class function {
    public static void saludar(String mensa){
        System.out.println(mensa);
    }

    public static int res(int n, int n2){
        return (n+n2);
    }
    
    public static void main(String[] args) {
        saludar("hola mundo");
        System.out.println(res(2,2));
        
    }


}