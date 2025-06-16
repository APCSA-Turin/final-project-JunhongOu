package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileLoader{ //class to print the loaded data from the txt file
    public static void main(String[] args){
        try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))){
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);  // Print each line to the console
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
