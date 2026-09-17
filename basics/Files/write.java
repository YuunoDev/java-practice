package basics.Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class write {

  public static void main(String[] args) {
    try {
      FileWriter txt = new FileWriter("basics/Files/text.txt");

      txt.write("Files in Java might be tricky, but it is fun enough!");
      txt.close(); // must close manually
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
