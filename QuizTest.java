
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class QuizTest extends JFrame implements ActionListener {
    JLabel label;
    JRadioButton radioButtons[] = new JRadioButton[4]; // Adjusting to 4 options per question
    JButton btnNext, btnResult;
    ButtonGroup bg;
    int count = 0, current = 0;
    int[] questionOrder = new int[10]; // To hold the order of questions

    QuizTest(String s) {
        super(s);
        label = new JLabel();
        add(label);
        bg = new ButtonGroup();
        for (int i = 0; i < 4; i++) { // Adjusting to 4 options per question
            radioButtons[i] = new JRadioButton();
            add(radioButtons[i]);
            bg.add(radioButtons[i]);
        }
        btnNext = new JButton("Next");
        btnResult = new JButton("Result");
        btnResult.setVisible(false);
        btnResult.addActionListener(this);
        btnNext.addActionListener(this);
        add(btnNext);
        add(btnResult);
        
        // Initialize and shuffle question order
        for (int i = 0; i < questionOrder.length; i++) {
            questionOrder[i] = i;
        }
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i : questionOrder) {
            indices.add(i);
        }
        Collections.shuffle(indices);
        for (int i = 0; i < questionOrder.length; i++) {
            questionOrder[i] = indices.get(i);
        }

        setData();
        label.setBounds(30, 40, 450, 20);
        for (int i = 0; i < 4; i++) {
            radioButtons[i].setBounds(50, 80 + (30 * i), 450, 20);
        }
        btnNext.setBounds(100, 240, 100, 30);
        btnResult.setBounds(270, 240, 100, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
    }

    void setData() {
        radioButtons[0].setSelected(false);
        radioButtons[1].setSelected(false);
        radioButtons[2].setSelected(false);
        radioButtons[3].setSelected(false);

        int questionIndex = questionOrder[current]; // Get the actual question index
        switch (questionIndex) {
            case 0:
                label.setText("Q: Which is the official language for Android Development?");
                radioButtons[0].setText("Java");
                radioButtons[1].setText("Kotlin");
                radioButtons[2].setText("C++");
                radioButtons[3].setText("JavaScript");
                break;
            case 1:
                label.setText("Q: What is the size of Boolean variable?");
                radioButtons[0].setText("8 bit");
                radioButtons[1].setText("16 bit");
                radioButtons[2].setText("32 bit");
                radioButtons[3].setText("not known");
                break;
            case 2:
                label.setText("Q: What is the default value of long variable?");
                radioButtons[0].setText("0");
                radioButtons[1].setText("0,0");
                radioButtons[2].setText("0L");
                radioButtons[3].setText("none of the above");
                break;
            case 3:
                label.setText("Q: What is the default value of Boolean variable?");
                radioButtons[0].setText("true");
                radioButtons[1].setText("false");
                radioButtons[2].setText("null");
                radioButtons[3].setText("none of the above");
                break;
            case 4:
                label.setText("Q: What kind of variables can a class consist of?");
                radioButtons[0].setText("A - class variables, instance variables");
                radioButtons[1].setText("B - class variables, local variables, instance variables");
                radioButtons[2].setText("C - class variables");
                radioButtons[3].setText("D - class variables, local variables");
                break;
            case 5:
                label.setText("Q: What is function overloading?");
                radioButtons[0].setText("A - Method with same name but different parameters");
                radioButtons[1].setText("B - Methods with same name but different return types");
                radioButtons[2].setText("C - Methods with same name, same parameter types but different parameter names");
                radioButtons[3].setText("None of the above");
                break;
            case 6:
                label.setText("Q: What is serialization?");
                radioButtons[0].setText("A - Serialization is the process of writing the state of an object to another object");
                radioButtons[1].setText("B - Serialization is the process of writing the state of an object to a byte stream");
                radioButtons[2].setText("C - Both of the above");
                radioButtons[3].setText("D - none of these");
                break;
            case 7:
                label.setText("Q: Which of the following is thread-safe?");
                radioButtons[0].setText("StringBuilder");
                radioButtons[1].setText("StringBuffer");
                radioButtons[2].setText("Both of the above");
                radioButtons[3].setText("none of these");
                break;
            case 8:
                label.setText("Q: What is the size of a boolean variable?");
                radioButtons[0].setText("8 bit");
                radioButtons[1].setText("16 bit");
                radioButtons[2].setText("32 bit");
                radioButtons[3].setText("Not precisely defined");
                break;
            case 9:
                label.setText("Q: What can we learn from Geeks for Geeks?");
                radioButtons[0].setText("C++");
                radioButtons[1].setText("Android development");
                radioButtons[2].setText("Java");
                radioButtons[3].setText("All of the above");
                break;
        }
    }

    boolean checkAns() {
        // Use questionOrder[current] to check the answer
        int questionIndex = questionOrder[current];
        switch (questionIndex) {
            case 0: return radioButtons[1].isSelected(); // Kotlin
            case 1: return radioButtons[0].isSelected(); // 8 bit
            case 2: return radioButtons[2].isSelected(); // 0L
            case 3: return radioButtons[1].isSelected(); // false
            case 4: return radioButtons[1].isSelected(); // B
            case 5: return radioButtons[0].isSelected(); // A
            case 6: return radioButtons[1].isSelected(); // B
            case 7: return radioButtons[1].isSelected(); // StringBuffer
            case 8: return radioButtons[3].isSelected(); // Not precisely defined
            case 9: return radioButtons[3].isSelected(); // All of the above
            default: return false; // Fallback
        }
    }

    public static void main(String[] args) {
        new QuizTest("Simple Quiz App");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNext) {
            if (checkAns()) count++;
            current++;
            if (current < questionOrder.length) {
                setData();
            } else {
                btnNext.setEnabled(false);
                btnResult.setVisible(true);
                btnNext.setText("Result");
            }
        }
        
        if (e.getActionCommand().equals("Result")) {
            // Only check the answer if we are on the last question
            if (current < questionOrder.length) {
                if (checkAns()) count++;
            }
            JOptionPane.showMessageDialog(this, "Correct Answers: " + count);
            System.exit(0);
        }
    }
}