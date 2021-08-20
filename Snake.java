package Snake;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Snake
{
  List<Point> snakePoints;
  int xDir, yDir;
  boolean isMoving, elongate;
  final int START_SIZE=20, START_X=150, START_Y=150;
  
  
  
  public Snake()
  {
	  snakePoints= new ArrayList<Point>();     //list of points will be assumed to track the snake's movement
      
	  /*We assume that if xDir is 0 and yDir is 0, then the snake is not moving. if yDir is positive
	    then the snake is moving in downwards direction else it is moving in upwards direction.
	    If xDir is positive, then the snake is moving in right direction else it is moving in the 
	    left direction.*/
	  xDir=0;
      yDir=0;
      isMoving= false;
      elongate= false;
      snakePoints.add(new Point(START_X,START_Y));
      for(int i=1;i<START_SIZE;i++)
      {
    	  snakePoints.add(new Point(START_X-(4*i),START_Y));
      }
  }
  
  public void Draw(Graphics g)                 //to draw the snake
  {
	  g.setColor(Color.white);                 //color of snake will be white
	  for(Point p: snakePoints)
	  {
		  g.fillRect(p.getX(),p.getY(),4,4);   //4 pixels from p.getX() and p.getY() will get turn to white
	  }
  }
  
  public boolean snakeCollision()
  {
	  int x= this.getHeadX();
	  int y= this.getHeadY();
	  for(int i=1; i<snakePoints.size()-1; i++)
	  {
		  if(snakePoints.get(i).getX()==x && snakePoints.get(i).getY()==y)
			  return true;
	  }
	  return false;
  }
  
  public void move()
  {
	 if(isMoving)
	 {	 
	  Point temp= snakePoints.get(0);
	  Point last= snakePoints.get(snakePoints.size()-1);
	  Point newStart= new Point(temp.getX()+xDir*4,temp.getY()+yDir*4);
	  
	  for(int i=snakePoints.size()-1;i>=1;i--)
	  {
		  snakePoints.set(i, snakePoints.get(i-1));
	  }
	  snakePoints.set(0, newStart);
	  if(elongate){
		  snakePoints.add(last);
		  elongate=false;
	  }
	 }
  }
  
  public void setXDir(int x)
  {
	  xDir= x;
  }
  
  public void setYDir(int y)
  {
	  yDir= y;
  }
  
  public int getXDir()
  {
	  return xDir;
  }
  
  public int getYDir()
  {
	  return yDir;
  }
  
  //to retrieve the x-coordinate of head of the snake
  public int getHeadX()
  {
	  return snakePoints.get(0).getX();
  }
  
  //to retrieve the y-coordinate of head of the snake
  public int getHeadY()
  {
	  return snakePoints.get(0).getY();
  }
  
  public boolean isMoving()
  {
	  return isMoving;
  }
  
  public void setIsMoving(boolean b)
  {
	  isMoving=b;
  }
  
  public void setElongate(boolean b)
  {
	  elongate= b;
  }
}
