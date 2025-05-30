package com.example;
import org.json.JSONObject;
import org.json.JSONArray;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class App {
    ArrayList<String> wrongAnswersList = new ArrayList<>();
    ArrayList<String> rightAnswersList = new ArrayList<>();
    ArrayList<String> QuestionList = new ArrayList<>();
    ArrayList<String> difficultyList = new ArrayList<>();


    public static void main(String[] args) {
        try {
            String questions = new String(Files.readAllBytes(Paths.get("src/main/resources/questions.json")));
            JSONObject triviaQuestion = new JSONObject(questions);
            JSONObject Question1Obj = triviaQuestion.getJSONObject("Question 1");
            JSONObject Question2Obj = triviaQuestion.getJSONObject("Question 2");
            JSONObject Question3Obj = triviaQuestion.getJSONObject("Question 3");
            JSONObject Question4Obj = triviaQuestion.getJSONObject("Question 4");
            JSONObject Question5Obj = triviaQuestion.getJSONObject("Question 5");
            JSONObject Question6Obj = triviaQuestion.getJSONObject("Question 6");
            JSONObject Question7Obj = triviaQuestion.getJSONObject("Question 7");
            JSONObject Question8Obj = triviaQuestion.getJSONObject("Question 8");
            JSONObject Question9Obj = triviaQuestion.getJSONObject("Question 9");
            JSONObject Question10Obj = triviaQuestion.getJSONObject("Question 10");
            //Question objects from the JSON file.

            String question1 = Question1Obj.getString("question");
            String question2 = Question2Obj.getString("question");
            String question3 = Question3Obj.getString("question");
            String question4 = Question4Obj.getString("question");
            String question5 = Question5Obj.getString("question");
            String question6 = Question6Obj.getString("question");
            String question7 = Question7Obj.getString("question");
            String question8 = Question8Obj.getString("question");
            String question9 = Question9Obj.getString("question");
            String question10 = Question10Obj.getString("question");

            //Questions from the question objects

            String Q1Difficulty = Question1Obj.getString("difficulty");
            String Q2Difficulty = Question2Obj.getString("difficulty");
            String Q3Difficulty = Question3Obj.getString("difficulty");
            String Q4Difficulty = Question4Obj.getString("difficulty");
            String Q5Difficulty = Question5Obj.getString("difficulty");
            String Q6Difficulty = Question6Obj.getString("difficulty");
            String Q7Difficulty = Question7Obj.getString("difficulty");
            String Q8Difficulty = Question8Obj.getString("difficulty");
            String Q9Difficulty = Question9Obj.getString("difficulty");
            String Q10Difficulty = Question10Obj.getString("difficulty");
            //Difficulty of questions

            String Q1Category = Question1Obj.getString("category");
            String Q2Category = Question2Obj.getString("category");
            String Q3Category = Question3Obj.getString("category");
            String Q4Category = Question4Obj.getString("category");
            String Q5Category = Question5Obj.getString("category");
            String Q6Category = Question6Obj.getString("category");
            String Q7Category = Question7Obj.getString("category");
            String Q8Category = Question8Obj.getString("category");
            String Q9Category = Question9Obj.getString("category");
            String Q10Category = Question10Obj.getString("category");
            //Categories for the questions

            String Q1CorrectAnswer = Question1Obj.getString("correct_answer");
            String Q2CorrectAnswer = Question2Obj.getString("correct_answer");
            String Q3CorrectAnswer = Question3Obj.getString("correct_answer");
            String Q4CorrectAnswer = Question4Obj.getString("correct_answer");
            String Q5CorrectAnswer = Question5Obj.getString("correct_answer");
            String Q6CorrectAnswer = Question6Obj.getString("correct_answer");
            String Q7CorrectAnswer = Question7Obj.getString("correct_answer");
            String Q8CorrectAnswer = Question8Obj.getString("correct_answer");
            String Q9CorrectAnswer = Question9Obj.getString("correct_answer");
            String Q10CorrectAnswer = Question10Obj.getString("correct_answer");
            //Correct answer for each questions

            JSONArray Q1IncorrectAnswers = Question1Obj.getJSONArray("incorrect_answers");
            JSONArray Q2IncorrectAnswers = Question2Obj.getJSONArray("incorrect_answers");
            JSONArray Q3IncorrectAnswers = Question3Obj.getJSONArray("incorrect_answers");
            JSONArray Q4IncorrectAnswers = Question4Obj.getJSONArray("incorrect_answers");
            JSONArray Q5IncorrectAnswers = Question5Obj.getJSONArray("incorrect_answers");
            JSONArray Q6IncorrectAnswers = Question6Obj.getJSONArray("incorrect_answers");
            JSONArray Q7IncorrectAnswers = Question7Obj.getJSONArray("incorrect_answers");
            JSONArray Q8IncorrectAnswers = Question8Obj.getJSONArray("incorrect_answers");
            JSONArray Q9IncorrectAnswers = Question9Obj.getJSONArray("incorrect_answers");
            JSONArray Q10IncorrectAnswers = Question10Obj.getJSONArray("incorrect_answers");



        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    
    
        //Creating the main frame

        JFrame frame = new JFrame("Trivia game");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components of the frame 

        JLabel titleLabel = new JLabel("Multiple Choice", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Display", Font.PLAIN, 20));
        JLabel question = new JLabel();

        JRadioButton button1 = new JRadioButton();
        JRadioButton button2 = new JRadioButton();
        JRadioButton button3 = new JRadioButton();
        JRadioButton button4 = new JRadioButton();
       
        
        JButton enterButton = new JButton("Enter");
        JTextArea outputArea = new JTextArea(100, 100);//where the fetched data will output 
        outputArea.setEditable(false);

        // Layout setup
        JPanel panel = new JPanel(new GridLayout(4, 4));

        //Jpanel is like a tray that you put things on and then you put the whole tray into your window
        //this panel holds a title, input field, button, and output area
        panel.add(titleLabel);
        panel.add(question);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(outputArea);
        panel.add(enterButton);
        
        //We have added components to our panel, then we add the PANEL to our FRAME
        frame.add(panel); 

        // Button behavior
        
        frame.setVisible(true);
    }

    private static void refreshQuestion(JLabel questionRefresh, JRadioButton buttonNum1, JRadioButton buttonNum2, JRadioButton buttonNum3, JRadioButton buttonNum4, JButton enterButton){
      
    }
}
