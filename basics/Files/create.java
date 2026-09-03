package basics.Files;

import java.io.File;
import java.io.IOException;

public class create {

    public static void main(String[] args) {
        // name file
        String name = "text.txt";

        try {
            File myobj = new File(name);
            if (myobj.createNewFile()) {
                System.out.println("File created: " + myobj.getName());
            }
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println("Error:");
            e.printStackTrace();
        }
    }
}