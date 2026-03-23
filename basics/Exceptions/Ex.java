package basics.Exceptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Ex {
    public static void File_Read() throws FileNotFoundException, IOException{
        File txt = new File("/Ex.java");
        FileReader read= new FileReader(txt);

        try (BufferedReader Buff = new BufferedReader(read)) {
            String line = "";

            while ((line=Buff.readLine()) != null) {
                System.out.println(line);
            }
        }
    }


    public static void Prov_Ex(){
        try {
            File_Read();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo", "Error", JOptionPane.ERROR_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Prov_Ex();
    }
}
