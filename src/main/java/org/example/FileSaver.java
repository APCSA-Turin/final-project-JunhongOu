package org.example;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver{ //class to save the data from the game onto the output txt file
    public static void saveData(String data){
        try (FileWriter writer = new FileWriter("output.txt")){
            writer.write(data);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
