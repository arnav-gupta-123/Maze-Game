import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Maze{
  private boolean[][] vWalls;
  private boolean[][] hWalls;
  private int startX;
  private int startY;
  private int goalX;
  private int goalY;

  public Maze(boolean[][] vWalls, boolean[][] hWalls, int startX, int startY, int goalX, int goalY){
    this.vWalls = vWalls;
    this.hWalls = hWalls;
    this.startX = startX;
    this.startY = startY;
    this.goalX = goalX;
    this.goalY = goalY;
  }

  public void mazeGen(){
    boolean found = false;
    int antiLoop = 0;
    int count = 0;
    boolean[][] coveredSquares = new boolean[10][10];
    //stage 1: winning path
    while (!found){ //this loop only runs multiple times if the first generation fails
      vWalls = new boolean[11][10]; //[xPos][yPos]
      hWalls = new boolean[10][11];
      int currentX = startX;
      int currentY = startY;
      int newX = 0;
      int newY = 0;
      coveredSquares = new boolean[10][10];
      hWalls[startX][startY] = true;
      hWalls[startX][startY + 1] = true;
      vWalls[startX][startY] = true;
      vWalls[startX + 1][startY] = true;
      coveredSquares[startX][startY] = true;
      count = 1;
      boolean failedGen = false;
      while (!found && !failedGen){  
        boolean success = false;
        while (!success){ //chosing a random valid direction
          int direction = (int) (Math.random()*4);
          if (direction == 0){
            newX = currentX + 1;
            newY = currentY;
          }
          else if (direction == 1){
            newX = currentX;
            newY = currentY - 1;
          }
          else if (direction == 2){
            newX = currentX - 1;
            newY = currentY;
          }
          else if (direction == 3){
            newX = currentX;
            newY = currentY + 1;
          }
          if (newX > -1 && newX < getWidth() && newY > -1 && newY < getHeight() && !coveredSquares[newX][newY]){
            success = true;
            coveredSquares[newX][newY] = true;
            count++;
          }
        }//update walls
        if (newX == 0 || !coveredSquares[newX - 1][newY] || currentX == newX - 1){
          vWalls[newX][newY] = !vWalls[newX][newY];
        }
        if (newX == getWidth() - 1 || !coveredSquares[newX + 1][newY] || currentX == newX + 1){
          vWalls[newX + 1][newY] = !vWalls[newX + 1][newY];
        }
        if (newY == 0 || !coveredSquares[newX][newY - 1] ||currentY == newY - 1){
          hWalls[newX][newY] = !hWalls[newX][newY];
        }
        if (newY == getHeight() - 1 || !coveredSquares[newX][newY + 1] ||currentY == newY + 1){
          hWalls[newX][newY + 1] = !hWalls[newX][newY + 1];
        }

        if (newX == goalX && newY == goalY){
          found = true;
        }

        if (//Checks if the path is stuck
          (newX == 0 || coveredSquares[newX - 1][newY]) &&
          (newX == getWidth() - 1 || coveredSquares[newX + 1][newY]) &&
          (newY == 0 || coveredSquares[newX][newY - 1]) &&
          (newY == getHeight() - 1 || coveredSquares[newX][newY + 1])
        ){ 
          newX = currentX;
          newY = currentY;
          if ((newX == 0 || coveredSquares[newX - 1][newY]) &&
          (newX == getWidth() - 1 || coveredSquares[newX + 1][newY]) &&
          (newY == 0 || coveredSquares[newX][newY - 1]) &&
          (newY == getHeight() - 1 || coveredSquares[newX][newY + 1])){
            //Checks if the path is very stuck
            failedGen = true;
          }
        }
        else{
          currentX = newX;
          currentY = newY;
          count++;
        }
      }
    }
    //stage 2: branches
    ArrayList<int[]> possibleRoots = new ArrayList<int[]>(0); //allows all covered squares to be
    for (int i = 0; i < getWidth(); i++){
      for (int j = 0; j < getHeight(); j++){
        if (coveredSquares[i][j] && !(i == goalX && j == goalY)){
          int square[] = {i, j};
          possibleRoots.add(square);
        }
      }
    }
    while(!possibleRoots.isEmpty()){
      int rootIndex = (int)(Math.random() * possibleRoots.size());
      int rootX = possibleRoots.get(rootIndex)[0];
      int rootY = possibleRoots.get(rootIndex)[1];
      int newX = 0;
      int newY = 0;
      
      if (//Checks if the path is stuck
        (rootX == 0 || coveredSquares[rootX - 1][rootY]) &&
        (rootX == getWidth() - 1 || coveredSquares[rootX + 1][rootY]) &&
        (rootY == 0 || coveredSquares[rootX][rootY - 1]) &&
        (rootY == getHeight() - 1 || coveredSquares[rootX][rootY + 1]))
      {
        possibleRoots.remove(rootIndex);
      }
      else{
        boolean success = false;
        while (!success){ //choosing a random valid direction
          int direction = (int) (Math.random()*4);
          if (direction == 0){
            newX = rootX + 1;
            newY = rootY;
          }
          else if (direction == 1){
            newX = rootX;
            newY = rootY - 1;
          }
          else if (direction == 2){
            newX = rootX - 1;
            newY = rootY;
          }
          else if (direction == 3){
            newX = rootX;
            newY = rootY + 1;
          }
          if (newX > -1 && newX < getWidth() && newY > -1 && newY < getHeight() && !coveredSquares[newX][newY]){
            success = true;
            coveredSquares[newX][newY] = true;
            count++;
            int[] newSquare = {newX, newY};
            possibleRoots.add(newSquare);
          }
        }//update walls
        if (newX == 0 || !coveredSquares[newX - 1][newY] || rootX == newX - 1){
          vWalls[newX][newY] = !vWalls[newX][newY];
        }
        if (newX == getWidth() - 1 || !coveredSquares[newX + 1][newY] || rootX == newX + 1){
          vWalls[newX + 1][newY] = !vWalls[newX + 1][newY];
        }
        if (newY == 0 || !coveredSquares[newX][newY - 1] || rootY == newY - 1){
          hWalls[newX][newY] = !hWalls[newX][newY];
        }
        if (newY == getHeight() - 1 || !coveredSquares[newX][newY + 1] || rootY == newY + 1){
          hWalls[newX][newY + 1] = !hWalls[newX][newY + 1];
        }
      }
    }
  }

  public boolean[][] getVWalls(){
    return vWalls;
  }

  public boolean[][] getHWalls(){
    return hWalls;
  }

  public int getStartX(){
    return startX;
  }

  public int getStartY(){
    return startY;
  }

  public int getGoalX(){
    return goalX;
  }

  public int getGoalY(){
    return goalY;
  }

  //return whether there is a wall with the specificed orientation and position
  public boolean getWallAt(boolean vertical, int xPosition, int yPosition){
    if (vertical){
      return vWalls[xPosition][yPosition];
    }
    else{
      return hWalls[xPosition][yPosition];
    }
  }

  //return number of grid spaces wide
  public int getWidth(){
    return hWalls.length;
  }
  
  //return number of grid spaces tall
  public int getHeight(){
    return vWalls[0].length;
  }

  public void setVWalls(boolean[][] vWalls){
    this.vWalls = vWalls;
  }

  public void setHWalls(boolean[][] hWalls){
    this.hWalls = hWalls;
  }

  public void setStartX(int startX){
    this.startX = startX;
  }

  public void setStartY(int startY){
    this.startY = startY;
  }

  public void setGoalX(int goalX){
    this.goalX = goalX;
  }

  public void setGoalY(int goalY){
    this.goalY = goalY;
  }
}
