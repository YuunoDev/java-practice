package basics.Files;

import java.io.File;
import java.io.IOException;

public class prov_file {

    public static void main(String[] args) {
        //name file
    String name= "text.txt";

    //FILE
    File file_text = new File(name);

    try{
        if(file_text.exists()){
            
        }

    }catch(IOException e){
        System.out.println("Error: "+e);

        e.printStackTrace();
    }
    }
}