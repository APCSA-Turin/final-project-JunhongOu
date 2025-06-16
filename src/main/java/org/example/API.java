package org.example;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import com.google.gson.Gson;

/* The API class in which the JSON data gets obtained from the Trivia API
while turning the JSON data into java string*/

public class API{
    public static String obtainTriviaQuestions() throws Exception{
        URL url = new URL("https://opentdb.com/api.php?amount=10&type=multiple"); //gets the URL of the Trivia API
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        /*connect to the URL*/
        connection.setRequestMethod("GET");
        /* creates a GET request to the API.. Asking the server to retrieve information for our program */
        BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        /* The BufferedReader wraps the text in a buffer so we can read it line by line*/
        String inputLine; //variable to store text, line by line
        StringBuilder content = new StringBuilder();
        /*A string builder is similar to a string object but faster for larger strings,
        you can concatenate to it and build a larger string. Loop through the buffer
        (read line by line). Add it to the string builder */
        while((inputLine = buff.readLine()) != null){
            content.append(inputLine);
        }
        buff.close(); //close the bufferreader
        connection.disconnect(); //disconnect from server
        return content.toString(); //return the content as a string
    }

    public static List<Question> convertedToString(String jsonStr){ //converts trivia api's JSON data into java string with GSON
        Gson gson = new Gson();
        Output response = gson.fromJson(jsonStr, Output.class);
        return response.results;
    }
}
