package basics;

import java.util.Scanner;

public class binary {
    
    static String tobinary(int num){
        String bin= "";
        int res=num;
        if (num==0){
            return "0";
        }

        while (res>0) {
            //System.out.println("res: "+res+" resultado: "+res%2);
            if(res%2==0){
                bin= "0"+ bin;
            }else{
                bin= "1"+ bin;
            }
            res=res/2;
        }
        return bin;
    }

    static int toint(String bin){
        if (bin=="0"){
            return 0;
        }
        int res=0;
        for (int i = bin.length()-1; i >=0; i--) {
            if (bin.charAt(i)=='1'){
                res = (int) (res+(Math.pow(2, bin.length()-(i+1))));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            int num=0;
            System.out.print("Numero a pasar a binario: ");
            num = scan.nextInt();
            System.out.println(tobinary(num));

            System.out.println(toint("11110000"));
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error al leer: "+e);
        }
    }
    
}
