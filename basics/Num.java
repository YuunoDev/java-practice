package basics;
public class Num {
    public static void main(String[] args) {
        int num = 10; //-2,147,483,648	2,147,483,647
        double numd = 10.0; //~±1.8e+308 (15-16 decimal digits)
        float numf = 12.0f; //~±3.4e+38 (6-7 decimal digits)
        long numl = 1000l; //-9,223,372,036,854,775,808	9,223,372,036,854,775,807

        System.out.println("Int: "+num);
        System.out.println("Double: "+numd);
        System.out.println("Float: "+numf);
        System.out.println("Long: "+numl);
    }
}
