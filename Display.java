package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class Display extends JFrame implements ActionListener {
	private JLabel l1, l2, resultLabel;
    private JTextField t1;
    private JButton b1;
    private int numberToGuess;
    private int attemptsLeft;

    public Display() {
        // Initialize components
        l1 = new JLabel("GUESS THE NUMBER ");
        l2 = new JLabel("                           (Between 1 and 50)                        ");
        t1 = new JTextField(25);
        b1 = new JButton("                         Guess                      "); 
        resultLabel = new JLabel("You have 5 attempts remaining.");
        
        Random random = new Random();
        numberToGuess = random.nextInt(50) + 1; 
        attemptsLeft = 5;

        add(l1);
        add(l2);
        add(t1);
        add(b1);
        add(resultLabel);
        
        b1.addActionListener(this);

        setLayout(new FlowLayout());
        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (attemptsLeft > 0) {
            try {
                int playerGuess = Integer.parseInt(t1.getText());

                if (playerGuess < 1 || playerGuess > 50) {
                    resultLabel.setText("Please enter a number between 1 and 50.");
                } else {
                    if (playerGuess < numberToGuess) {
                        resultLabel.setText("Too low! Try again. Attempts left: " + --attemptsLeft);
                    } else if (playerGuess > numberToGuess) {
                        resultLabel.setText("Too high! Try again. Attempts left: " + --attemptsLeft);
                    } else {
                        resultLabel.setText("Congratulations! You guessed the number right.");
                        b1.setEnabled(false);
                    }

                    if (attemptsLeft == 0 && playerGuess != numberToGuess) {
                        resultLabel.setText("Game Over! The correct number was: " + numberToGuess);
                        b1.setEnabled(false);
                    }
                }
            } catch (NumberFormatException e) {
                resultLabel.setText("Please enter a valid number!");
            }
        }
    }
}