public class bucles {
    public static void main(String[] args) {
        //While
        int i=0;
        System.out.println("While: ");
        while (i!=10) {
            System.out.println(i);
            i+=1;
        }

        //for
        System.out.println("For: ");
        for (int j = 0; j < 10; j++) {
            System.out.println(j);
        }

        //foreach
        int[] vec = {1,2,3,4,5};
        for (int j : vec) {
            System.out.println("numero j"+j);
        }

        //do while
        int k=0;
        System.out.println("do While: "+k);
        do {
            k+=1;
            System.out.println(k);
        } while (k!=10);
    

        //prueba
        System.out.println("");
        int num = 1;
        int prod= 1;
        while (num <= 5) {
            System.out.println("numero "+num+"Prod "+prod);
            prod*=num;
            num++;
        }

        System.out.println(prod);


        System.out.println("\n For con break");
        for (int j = 0; j <= 10; j++) {
            if (j==5){
                break;
            }

            System.out.println(j);
        }

        // continue solo salta la iteracion
        System.out.println("\n For con continue");
        for (int j = 0; j <= 10; j++) {
            if (j==5){
                continue;
            }

            System.out.println(j);
        }
    }
}
