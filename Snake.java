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
		  //to make the snake visible we will create each rectangle of dimension 4*4. So the head of snake lies from 150->154 along X as well as Y axis.
		  g.fillRect(p.getX(),p.getY(),4,4);   //4 pixels from p.getX() and p.getY() will get turn to white
	  }
  }
  
  public boolean snakeCollision()
  {
	  int x= this.getHeadX();                     //retrieves the current position of the snake's head along X-axis.
	  int y= this.getHeadY();                     //retrieves the current position of the snake's head along Y-axis.
	  for(int i=1; i<snakePoints.size()-1; i++)
	  {
		  if(snakePoints.get(i).getX()==x && snakePoints.get(i).getY()==y)          //checks whether the head of the snake is touching any other part of its body.
			  return true;                                                      //if it does so, then collision occurs.
	  }
	  return false;                                                                     //else, no collision occurs.
  }
  
  public void move()
  {
	 if(isMoving)                                                  //isMoving is initially false indicating snake is not moving. But if it does move, then the changes are made.
	 {	 
	  Point temp= snakePoints.get(0);                              //stores the point that represents the position of snake's head before it moves to a new position.
	  Point last= snakePoints.get(snakePoints.size()-1);           //stores the last point of the snake before it moves to a new direction.
	  Point newStart= new Point(temp.getX()+xDir*4,temp.getY()+yDir*4);     //position of the snake's head is updated. each time it shifts, it will do so as a multiple of 4
	  
	  for(int i=snakePoints.size()-1;i>=1;i--)                     //position of each points of snake's are updated starting from its end; except the head.
	  {
		  snakePoints.set(i, snakePoints.get(i-1));          //as the snake move each point will now be located on the position occupied by its immediate predecessor point.
	  }
	  snakePoints.set(0, newStart);                              //it is not updated first, because the its immediate next point would need its positon for its updation.
	  if(elongate){                                              //it is checked if the snake will get longer or not.
		  snakePoints.add(last);                             //if it does get longer, then the point that was at the last before moving will be added in the list again.
		  elongate=false;                                    //to maintain the growing of snake by 1 rectangle/
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
