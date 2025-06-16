package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Game{
    private JFrame gameFrame;
    private JLabel questionLabel;
    private JLabel gotQuestionsCorrectCounter;
    private JButton[] answerOptions;
    private List<Question> questions;
    private int currentQuestionNum = 0;
    private int questionsGottenRight = 0;
    /*Initializing variables needed for the trivia game*/

    public Game(List<Question> questions){
        loadPreviousGameData();
        this.questions = questions;
        gameFrame = new JFrame("Trivia Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(500, 500);
        gameFrame.setLayout(new BorderLayout());
        /*Creates the base layout of the GUI*/

        gotQuestionsCorrectCounter = new JLabel("", SwingConstants.CENTER);
        gotQuestionsCorrectCounter.setFont(new Font("Roboto", Font.BOLD, 20));
        gotQuestionsCorrectCounter.setText("Questions gotten right: " + questionsGottenRight);
        gameFrame.add(gotQuestionsCorrectCounter, BorderLayout.SOUTH);
        /*creates a counter for the number of questions the user gets correct
        and adds it onto the base layout of the GUI */

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        gameFrame.add(questionLabel, BorderLayout.NORTH);
        /*Creates the canvas for the questions and adds it onto the base layout of the GUI*/

        JPanel choiceCanvas = new JPanel(new GridLayout(2, 2)); //A canvas gets created for the answer choices
        answerOptions = new JButton[4]; //4 buttons are created for multiple choices

        for (int n = 0; n < 4; n++){
            answerOptions[n] = new JButton(); //creates the multiple choice options
            answerOptions[n].addActionListener(e -> answerChecker(((JButton) e.getSource()).getText()));
            //gets the answer choices onto the buttons by using actionlistener and actionevent
            /*this is the same thing as the traditional action event code but simplified with lambda operator*/
            answerOptions[n].setForeground(Color.black);
            answerOptions[n].setFont(new Font("Roboto", Font.BOLD, 20));
            //Just setting the answer text color and font here
            choiceCanvas.add(answerOptions[n]); //adds the answer choices onto the canvas created earlier
        }
        answerOptions[0].setBackground(new Color(255,109,106));
        answerOptions[1].setBackground(new Color(139,211,230));
        answerOptions[2].setBackground(new Color(233,236,107));
        answerOptions[3].setBackground(new Color(119,221,119));
        //Setting the colors of the answer panels to pastel colors
        gameFrame.add(choiceCanvas, BorderLayout.CENTER); //adds all the answer choices into the GUI
        refreshQuestion(); //here the GUI gets refreshed
        gameFrame.setVisible(true); //allows the game to be seen/to run
    }

    public void loadPreviousGameData(){ //this method will print out the saved data from the previous trivia game
        String[] val = {}; //since the FileLoader class only have a main method, the parameter's value is set to empty
        System.out.println("\nObtaining data from previous game: ");
        FileLoader.main(val); //previous game's data gets printed when the game runs
    }
    public void refreshQuestion(){ //this method refreshes the current question with the next question
        if (currentQuestionNum >= questions.size()){ //Detects if there are no more questions
            JOptionPane.showMessageDialog(gameFrame, "Gameover! You got " + questionsGottenRight + " questions correct!");
            /*Displaying "Gameover!" + number of questions a user gets correct once all the questions have been answered*/
            FileSaver.saveData("The user has gotten " + questionsGottenRight + " questions right in the previous Trivia Game!");
            //Saving the number of questions gotten right from the user onto a txt file
            gameFrame.dispose(); //removes the game interface after the game is complete
            return; //early return to prevent further code running (to fix index out of bounds error)
        }
        Question question = questions.get(currentQuestionNum); //getting the string of the current question
        questionLabel.setText("<html>" + question.question + "</html>");
        /*The HTML tag makes it so the question string is properly displayed onto the GUI*/
        List<String> answerChoices = new ArrayList<>(question.incorrect_answers); //Wrong answers gets put into this list
        answerChoices.add(question.correct_answer); //Since the arraylist didn't contain the right answer before, I added it here
        Collections.shuffle(answerChoices);
        /*The shuffling allows for all the answer choices to either be wrong or right (creates randomness)*/

        for (int n = 0; n < answerOptions.length; n++){
            String answerWithoutHTMLMarks = removeHTMLMarks(answerChoices.get(n));
            //gets the answers from a specific question and uses "removeHTMLMarks" to get the answers with spacing instead
            answerOptions[n].setText("<html>" + answerWithoutHTMLMarks + "<html>");
            /*the HTML tags are added here to display the answers properly first,
            then the answerChecker will remove the HTML tags for to see if the answers are correct*/
        }
    }

    public String removeHTMLMarks(String text){
        //this method removes all the HTML text marks so that the answer can be properly compared in the answerChecker
        return text.replaceAll("<[^>]*>", ""); //replaces the HTML marks with spaces
    }

    public void answerChecker(String chosenAnswer){ //this method checks for if the user gets the answer right for questions
        Question question = questions.get(currentQuestionNum); //gets the current question for the game
        if (removeHTMLMarks(chosenAnswer).trim().equals(question.correct_answer.trim())){ //removes space to compare the original answers
            JOptionPane.showMessageDialog(gameFrame, "Right! Right answer is: " + question.correct_answer);
            questionsGottenRight++; //increase the number of questions got right by 1 until the end of the game
            gotQuestionsCorrectCounter.setText("Questions gotten right: " + questionsGottenRight + "\n");
            //increases the counter of questions a user got right by 1
        }
        else{
            JOptionPane.showMessageDialog(gameFrame, "Wrong! Right answer is: " + question.correct_answer);
        }
        /*a popup will occur once you answered the question, whether right or wrong*/
        currentQuestionNum++; //increases the num so that the gui can move onto the next question
        refreshQuestion(); //refreshes the gui after the user gets the right/wrong answer
    }

    public static void main(String[] args){ //Main method to run the game
        try{
            String jsonData = API.obtainTriviaQuestions(); //gets the JSON data from the Trivia API
            List<Question> convertedQuestions = API.convertedToString(jsonData); //converting json data into java string
            new Game(convertedQuestions); //starts the game with the trivia questions from the API
        }
        catch (Exception e){ //catch statement in case of exception
            e.printStackTrace();
        }
    }
}