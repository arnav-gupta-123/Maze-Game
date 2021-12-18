import javax.swing.*;
import java.awt.*;

public class MazePainter extends Canvas {
  private Maze maze;
  private int cornerX;
  private int cornerY;
  private int gridSize;
  private int wallWidth;
  public MazePainter(Maze maze, int cornerX, int cornerY, int gridSize, int wallWidth){
    this.maze = maze;
    this.cornerX = cornerX;
    this.cornerY = cornerY;
    this.gridSize = gridSize;
    this.wallWidth = wallWidth;
  }

  public void paint(Graphics g){
    Color bgColor = new Color(135, 171, 82);
    g.setColor(bgColor);
    g.fillRect(cornerX, cornerY, gridSize * maze.getWidth(), gridSize * maze.getHeight());//background
    g.setColor(Color.black);
    //paint vertical walls
    for (int i = 0; i < maze.getWidth() + 1; i++){//through columns
      for (int j = 0; j < maze.getHeight(); j++){//within columns
        if (maze.getWallAt(true, i, j)){
          g.fillRect(cornerX + i * gridSize - wallWidth/2, cornerY + j * gridSize - wallWidth/2, wallWidth, gridSize + wallWidth);
        }
      }
    } 
    //paint horizontal walls
    for (int i = 0; i < maze.getWidth(); i++){//through columns
      for (int j = 0; j < maze.getHeight() + 1; j++){//within columns
        if (maze.getWallAt(false, i, j)){
          g.fillRect(cornerX + i * gridSize - wallWidth/2, cornerY + j * gridSize - wallWidth/2, gridSize + wallWidth, wallWidth);
        }
      }
    } 
  }

  public int getCornerX(){
    return cornerX;
  }
  public int getCornerY(){
    return cornerY;
  }
  public int getGridSize(){
    return gridSize;
  }
  public int getWallWidth(){
    return wallWidth;
  }
  public int getIconSize(){
    return gridSize - wallWidth;
  }
}
