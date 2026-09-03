package basics.Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class read {
    public static void main(String[] args) {
        File tex= new File("text.txt");

        try(Scanner myReader = new Scanner(tex)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            
        } catch (FileNotFoundException e) {
            // TODO: handle exception
            System.out.println("File error: ");
            e.printStackTrace();
        }
    }
}
