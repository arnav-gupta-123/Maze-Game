import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class startGUI extends JFrame implements ActionListener {
  private JFrame frame;
  private JTextField welcomeField;
  private JLabel welcomeLbl;
  private JButton startBtn;
  private JLabel instructionsLbl1;
  private JLabel instructionsLbl2;
  private JLabel instructionsLbl3;
  private JLabel instructionsLbl4;
  private JLabel instructionsLbl5;
  private JLabel instructionsLbl6;
  private JLabel instructionsLbl7;
  private JButton playBtn;

  public startGUI() {
    
    //Setting up the background
    frame = new JFrame("Turkey Maze Game!");
    frame.getContentPane().setBackground(new Color(173,216,230));

    //start Button
    startBtn = new JButton("Start!");
    startBtn.setForeground(Color.green);
    startBtn.setBackground(Color.gray);
    startBtn.setBounds(230, 100, 125, 80);
    startBtn.setFont(new Font("Serif", Font.BOLD, 25));
    frame.add(startBtn);
    startBtn.addActionListener(this);

    //Instructions Label
    int position = 30;
    instructionsLbl1 = new JLabel("Welcome ");
    instructionsLbl1.setBounds(210, position, 200, 50);
    instructionsLbl2 = new JLabel("in this game, you are the");
    instructionsLbl2.setBounds(210, position + 30, 200, 50);
    instructionsLbl3 = new JLabel("turkey and you must find");
    instructionsLbl3.setBounds(210, position + 60, 200, 50);
    instructionsLbl4 = new JLabel("your way through the maze");
    instructionsLbl4.setBounds(210, position + 90, 200, 50);
    instructionsLbl5 = new JLabel("quickly to escape the");
    instructionsLbl5.setBounds(210, position + 120, 200, 50);
    instructionsLbl6 = new JLabel("Thanksgiving Dinner in");
    instructionsLbl6.setBounds(210, position + 150, 200, 50);
    instructionsLbl7 = new JLabel("time. Have Fun!");
    instructionsLbl7.setBounds(210, position + 180, 200, 50);

    //Play Button
    playBtn = new JButton("Play!");
    playBtn.setForeground(Color.green);
    playBtn.setBackground(Color.gray);
    playBtn.setBounds(230, 280, 125, 80);
    playBtn.setFont(new Font("Serif", Font.BOLD, 25));
    playBtn.addActionListener(this);

    //welcome Label
    welcomeLbl = new JLabel("Enter your name below: ");
    welcomeField = new JTextField("");
    welcomeLbl.setBounds(210, 10, 200, 50);
    welcomeField.setBounds(240, 50, 100, 35);
    frame.add(welcomeField);
    frame.add(welcomeLbl);

    //Setting up the frame
    frame.setSize(600, 600);
    frame.setLayout(null);
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == startBtn){
      String username = welcomeField.getText();
      startBtn.setVisible(false);
      welcomeLbl.setVisible(false);
      welcomeField.setVisible(false);
      instructionsLbl1.setText("Welcome " + username);
      frame.add(instructionsLbl1);
      frame.add(instructionsLbl2);
      frame.add(instructionsLbl3);
      frame.add(instructionsLbl4);
      frame.add(instructionsLbl5);
      frame.add(instructionsLbl6);
      frame.add(instructionsLbl7);
      frame.add(playBtn);
      frame.setVisible(false);
      frame.setVisible(true);
    }
    
    if (e.getSource() == playBtn){
      gameGUI gui = new gameGUI();
      frame.setVisible(false);
    }
  }
}
