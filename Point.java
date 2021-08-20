package Snake;

public class Point 
{
	private int x,y;                     //x and y coordinates
  public Point()                         //non parameterized constructor
  {
	  x=0;
	  y=0;
  }
  
  public Point(int x,int y)              //Parameterized constructor
  {
	  this.x= x;
	  this.y= y;
  }
  
  public void setX(int x)                //to set/change the value of x coordinate
  {
	  this.x= x;
  }
  
  public void setY(int y)                //to set/change the value of x coordinate
  {
	  this.y= y;
  }
  
  public int getX()                      //to retrieve x coordinate
  {
	  return x;
  }
  
  public int getY()                      //to retrieve y coordinate
  {
	  return y;
  }
}
