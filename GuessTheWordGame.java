import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheWordGame extends JFrame {
    private JPanel panel;
    private JLabel instructionLabel;
    private ButtonGroup optionsGroup;
    private JRadioButton option1, option2, option3, option4;
    private JButton submitButton;
    private JLabel resultLabel;
    private String[][] questions = {
            {"Apple", "Fruit", "Vegetable", "Animal", "Place"},
            {"Lion", "Animal", "Bird", "Fruit", "Vehicle"},
            {"Car", "Animal", "Fruit", "Vehicle", "Building"},
            {"Banana", "Fruit", "Animal", "Bird", "Place"},
            {"Earth", "Planet", "Animal", "Vegetable", "Vehicle"},
            {"Piano", "Instrument", "Food", "Animal", "Place"},
            {"Sun", "Star", "Animal", "Plant", "Building"},
            {"Dog", "Animal", "Bird", "Vehicle", "Fruit"},
            {"Mountain", "Place", "Animal", "Bird", "Fruit"},
            {"Rose", "Flower", "Tree", "Animal", "Food"}
    };
    private String correctAnswer;
    private int currentQuestionIndex = -1;

    public GuessTheWordGame() {
        setTitle("Guess The Word Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Instruction label
        instructionLabel = new JLabel("Guess the category for the word:");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Options
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        // Submit button
        submitButton = new JButton("Submit Guess");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new SubmitButtonListener());

        // Result label
        resultLabel = new JLabel("");
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to the panel
        panel.add(instructionLabel);
        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);
        panel.add(submitButton);
        panel.add(resultLabel);

        // Add panel to the frame
        add(panel);
        loadNextQuestion();
    }

    private void loadNextQuestion() {
        currentQuestionIndex++;
        //currentQuestionIndex--;//
        

        if (currentQuestionIndex >= questions.length) {
            // If all questions are answered, show end message
            resultLabel.setText("Game Over! You've completed all the questions.");
            resultLabel.setForeground(Color.BLUE);
            submitButton.setEnabled(false);
            return;
        }

        String[] currentQuestion = questions[currentQuestionIndex];
        String word = currentQuestion[0];
        String[] options = {currentQuestion[1], currentQuestion[2], currentQuestion[3], currentQuestion[4]};
        Random rand = new Random();

        // Randomly shuffle options
        for (int i = 0; i < options.length; i++) {
            int j = rand.nextInt(options.length);
            String temp = options[i];
            options[i] = options[j];
            options[j] = temp;
        }

        correctAnswer = currentQuestion[1]; // Correct answer is always the first option (the correct category)

        // Set word and options
        instructionLabel.setText("Guess the category for the word: " + word);
        option1.setText(options[0]);
        option2.setText(options[1]);
        option3.setText(options[2]);
        option4.setText(options[3]);

        // Clear previous result and enable options
        resultLabel.setText("");
        optionsGroup.clearSelection();
        submitButton.setEnabled(true);
    }

    // ActionListener for the submit button
    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedAnswer = "";
            if (option1.isSelected()) {
                selectedAnswer = option1.getText();
            } else if (option2.isSelected()) {
                selectedAnswer = option2.getText();
            } else if (option3.isSelected()) {
                selectedAnswer = option3.getText();
            } else if (option4.isSelected()) {
                selectedAnswer = option4.getText();
            }

            // Check if the selected word is the correct answer
            if (selectedAnswer.equals(correctAnswer)) {
                resultLabel.setText("Correct! The category is: " + correctAnswer);
                resultLabel.setForeground(Color.GREEN);
            } else {
                resultLabel.setText("Incorrect. The correct category was: " + correctAnswer);
                resultLabel.setForeground(Color.RED);
            }

            // Disable options and submit button after a guess
            submitButton.setEnabled(false);

            // Move to the next question after 3 seconds
            Timer timer = new Timer(3000, e1 -> loadNextQuestion());
            timer.setRepeats(false);
            timer.start();
            
        }
    }

    // Main method to launch the game
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuessTheWordGame game = new GuessTheWordGame();
            game.setVisible(true);
        });
    }
}

