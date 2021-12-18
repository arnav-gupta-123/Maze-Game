public class Turkey {
  private int turkeyXPosition;
  private int turkeyYPosition;

  public Turkey(int turkeyXPosition, int turkeyYPosition) {
    this.turkeyXPosition = turkeyXPosition;
    this.turkeyYPosition = turkeyYPosition;
  }

  public void setTurkeyXPosition(int turkeyXPosition) {
    this.turkeyXPosition = turkeyXPosition;
  }

  public void setTurkeyYPosition(int turkeyYPosition) {
    this.turkeyYPosition = turkeyYPosition;
  }

  public int getTurkeyXPosition() {
    return this.turkeyXPosition;
  }

  public int getTurkeyYPosition() {
    return this.turkeyYPosition;
  }

  public String toString() {
    return "I am a turkey and my current x-position is " + this.turkeyXPosition + " and my current y-position is " + this.turkeyYPosition;
  }

}
