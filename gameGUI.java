import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class gameGUI extends JFrame implements ActionListener{
  private JFrame frame;
  private JLabel turkeyImg;
  private Maze maze;
  private Turkey turkey;
  private MazePainter mazePaint;
  private JLabel fieldImg;
  private JLabel continueLbl1;
  private JLabel continueLbl2;
  private JLabel startlineImg;
  private JButton quitBtn;
 
  public gameGUI() {

    //initialize random maze
    boolean[][] vWalls = new boolean[11][10]; //[xPos][yPos]
    boolean[][] hWalls = new boolean[10][11];

    //initializes maze
    maze = new Maze(vWalls, hWalls, 1, 3, 8, 6);
    maze.mazeGen();

    //Setting up the background
    frame = new JFrame("Turkey Maze Game!");
    frame.getContentPane().setBackground(new Color(173,216,230));

    //initializes mazePaint
    mazePaint = new MazePainter(maze, 50, 50, 50, 6);

    //Setting up the turkey image
    ImageIcon turkeyIcon = new ImageIcon("turkey.png");
    Image temp = turkeyIcon.getImage();
    temp = temp.getScaledInstance(mazePaint.getIconSize(), mazePaint.getIconSize(), Image.SCALE_DEFAULT);
    turkeyIcon.setImage(temp);
    turkeyImg = new JLabel(turkeyIcon);
    turkeyImg.setBounds(
      mazePaint.getCornerX() + maze.getStartX()* mazePaint.getGridSize() + mazePaint.getWallWidth()/2, 
      mazePaint.getCornerY() + maze.getStartY()* mazePaint.getGridSize() + mazePaint.getWallWidth()/2, 
      mazePaint.getIconSize(), 
      mazePaint.getIconSize());
    frame.add(turkeyImg);
    turkey = new Turkey(mazePaint.getCornerX() + maze.getStartX()* mazePaint.getGridSize() + mazePaint.getWallWidth()/2, 
      mazePaint.getCornerY() + maze.getStartY()* mazePaint.getGridSize() + mazePaint.getWallWidth()/2);

    //setting up the startline image
    ImageIcon startIcon = new ImageIcon("startline.png");
    Image temp2 = startIcon.getImage();
    temp2 = temp2.getScaledInstance(mazePaint.getIconSize(), mazePaint.getIconSize(), Image.SCALE_DEFAULT);
    startIcon.setImage(temp2);
    startlineImg = new JLabel(startIcon);
    startlineImg.setBounds(
      mazePaint.getCornerX() + maze.getStartX()* mazePaint.getGridSize() + mazePaint.getWallWidth()/2, 
      mazePaint.getCornerY() + maze.getStartY()* mazePaint.getGridSize() + mazePaint.getWallWidth()/2, 
      mazePaint.getIconSize(), 
      mazePaint.getIconSize());
    frame.add(startlineImg);

    //setting up field image
    ImageIcon fieldIcon = new ImageIcon("field.jpg");
    Image temp1 = fieldIcon.getImage();
    temp1 = temp1.getScaledInstance(mazePaint.getIconSize(), mazePaint.getIconSize(), Image.SCALE_DEFAULT);
    fieldIcon.setImage(temp1);
    fieldImg = new JLabel(fieldIcon);
    fieldImg.setBounds(
      mazePaint.getCornerX() + maze.getGoalX()* mazePaint.getGridSize() + mazePaint.getWallWidth()/2, 
      mazePaint.getCornerY() + maze.getGoalY()* mazePaint.getGridSize() + mazePaint.getWallWidth()/2, 
      mazePaint.getIconSize(), 
      mazePaint.getIconSize());
    frame.add(fieldImg);

    //Continue label
    continueLbl1 = new JLabel("You have completed the maze!");
    continueLbl2 = new JLabel("Thank you for playing!");
    continueLbl1.setBounds(180, 150, 300, 200);
    continueLbl2.setBounds(200, 180, 300, 200);
    continueLbl1.setVisible(false);
    continueLbl2.setVisible(false);
    frame.add(continueLbl1);
    frame.add(continueLbl2);

    //quit button
    quitBtn = new JButton("Quit");
    quitBtn.setForeground(Color.green);
    quitBtn.setBackground(Color.gray);
    quitBtn.setBounds(220, 350, 125, 80);
    quitBtn.setFont(new Font("Serif", Font.BOLD, 25));
    frame.add(quitBtn);
    quitBtn.addActionListener(this);
    quitBtn.setVisible(false);

    //Setting up the frame
    frame.setSize(600, 600);
    frame.setLayout(null);
    frame.setVisible(true);
    
    //adds Canvas component to paint maze
    mazePaint.setBounds(0,0,600,600);
    frame.add(mazePaint);
    
    //allows the turkey to move
    fieldImg.setFocusable(false);
    mazePaint.setFocusable(false);
    turkeyImg.setFocusable(true);
    quitBtn.setFocusable(false);
    int movement = mazePaint.getGridSize();
    turkeyImg.addKeyListener(new KeyListener() {
      public void keyPressed(KeyEvent e) {
        
        int turkeyXCoordinateInTermsOfMaze = (turkey.getTurkeyXPosition() - mazePaint.getCornerX() - (mazePaint.getWallWidth()/2))/mazePaint.getGridSize();

        int turkeyYCoordinateInTermsOfMaze = (turkey.getTurkeyYPosition() - mazePaint.getCornerY() - (mazePaint.getWallWidth()/2))/mazePaint.getGridSize();

        if (e.getKeyCode() == KeyEvent.VK_UP && maze.getWallAt(false, turkeyXCoordinateInTermsOfMaze, turkeyYCoordinateInTermsOfMaze) == false) {
          turkey.setTurkeyYPosition(turkey.getTurkeyYPosition() - movement);
          turkeyImg.setBounds(turkey.getTurkeyXPosition(), turkey.getTurkeyYPosition(), mazePaint.getIconSize(), mazePaint.getIconSize());
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && maze.getWallAt(false, turkeyXCoordinateInTermsOfMaze, turkeyYCoordinateInTermsOfMaze + 1) == false) {
          turkey.setTurkeyYPosition(turkey.getTurkeyYPosition() + movement);
          turkeyImg.setBounds(turkey.getTurkeyXPosition(), turkey.getTurkeyYPosition(), mazePaint.getIconSize(), mazePaint.getIconSize());
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && maze.getWallAt(true, turkeyXCoordinateInTermsOfMaze, turkeyYCoordinateInTermsOfMaze) == false) {
          turkey.setTurkeyXPosition(turkey.getTurkeyXPosition() - movement);
          turkeyImg.setBounds(turkey.getTurkeyXPosition(), turkey.getTurkeyYPosition(), mazePaint.getIconSize(), mazePaint.getIconSize());
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && maze.getWallAt(true, turkeyXCoordinateInTermsOfMaze + 1, turkeyYCoordinateInTermsOfMaze) == false) {
          turkey.setTurkeyXPosition(turkey.getTurkeyXPosition() + movement);
          turkeyImg.setBounds(turkey.getTurkeyXPosition(), turkey.getTurkeyYPosition(), mazePaint.getIconSize(), mazePaint.getIconSize());
        }
         
        int x = (mazePaint.getCornerX() + maze.getGoalX()* mazePaint.getGridSize() + mazePaint.getWallWidth()/2);

        int y = (mazePaint.getCornerY() + maze.getGoalY()* mazePaint.getGridSize() + mazePaint.getWallWidth()/2);

        if (turkey.getTurkeyXPosition() == x && turkey.getTurkeyYPosition() == y) {
          continueLbl1.setVisible(true);
          continueLbl2.setVisible(true);
          quitBtn.setVisible(true);
          turkeyImg.setFocusable(false);
          turkeyImg.setVisible(false);
          fieldImg.setVisible(false);
          mazePaint.setVisible(false);
          startlineImg.setVisible(false);
        }
      }

      public void keyReleased(KeyEvent e) { 
      }

      public void keyTyped(KeyEvent e) { 
      }
    });
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == quitBtn) {
      frame.setVisible(false);
    }
  }
}
