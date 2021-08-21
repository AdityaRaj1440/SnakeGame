package Snake;
import java.applet.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

public class SnakeGame extends Applet implements Runnable, KeyListener    //to create a thread and perform event handling.      
{
	Graphics gfx;                        //a graphics object
	Image img;                           //an image object
    Thread thread;                     
	Snake snake;
	boolean gameOver;
	Token token;
    
  public void init()                     //called by browser to indicate that the program is loaded
  {
	  this.resize(500,400);              //to resize applet size(width,height)
	  img= createImage(500,400);         //to create a drawable image allowing double buffering
	  gameOver= false;                   //initially game has just started so it cannot be over.
	  //gfx:: an offscreen Graphics due to getGraphics();   g:: an onscreen graphics
	  gfx= img.getGraphics();            //returns a a Graphics2D, which can be used to draw into this image.
	  this.addKeyListener(this);         //the source will also act as a listener to the event peformed here and so it is being registerd.
	  snake= new Snake();
	  token= new Token(snake);
	  thread= new Thread(this);           
      thread.start();
  }
  
  public void paint(Graphics g)          
  {
	  gfx.setColor(Color.black);         //sets the color of the elements associated with Graphics class
	  gfx.fillRect(0, 0, 500, 400);      //to fill the rectangular element;in this case the applet with black
	  if(!gameOver)                      //if the game is still ongoing then the snake and the token will be drawn.
	  {
	   snake.Draw(gfx);
	   token.draw(gfx);
	  }
	  else
	  {
		  gfx.setColor(Color.red);                          //if the game is over, then the Game Over text along with score will be displayed in working area in red color.
		  gfx.drawString("Game Over", 236, 198);
		  gfx.drawString("Score:: "+token.getScore(), 236, 208);
	  }
	  g.drawImage(img, 0, 0, null);      //loads the snake and token image updated offscreen using gfx on the scree.
	                                     //returns true if the img is completely loaded else returns false
  }
  
  public void update(Graphics g)
  {
	  paint(g);
  }
  
  public void repaint(Graphics g)
  {
	  paint(g);
  }

  public void run() 
  {
	  
	for(;;)                                             //an infinite loop is run so that game keeps going on until the snake hit itself or move out of boundary.
	{
		if(!gameOver)                               //if the game is not over, the snake is moved, any collisions or out of boundary movements are checked.
		{
			snake.move();
			this.checkGameOver();
			token.snakeCollision();
		}
		showStatus("Score:: "+token.getScore());    //while the game is going on, the status bar of the applet will show the changing score.
		this.repaint();                             //used to update the screen's content.
		try {
			Thread.sleep(40);                   //to control the speed of the snake.
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
  }
  
  public void checkGameOver()                                //turne gameOver to false if any of the following conditions are satisfied.
  {
	  if(snake.getHeadX()<0||snake.getHeadX()>496)
		  gameOver= true;
	  if(snake.getHeadY()<0||snake.getHeadY()>396)
		  gameOver= true;
	  if(snake.snakeCollision())
		  gameOver= true;
  }
  
  public void keyTyped(KeyEvent ke)
  {
	 
  }
  
  public void keyPressed(KeyEvent ke)
  {
	  if(!snake.isMoving())                          //if snakeMoving is set to false, the move operation is impossible until snakeMoving is set to true.
	  {                                              //if any of the following keys are pressed, the snake should move.
		  if(ke.getKeyCode()==KeyEvent.VK_UP || ke.getKeyCode()==KeyEvent.VK_DOWN ||             
				  ke.getKeyCode()==KeyEvent.VK_LEFT || ke.getKeyCode()==KeyEvent.VK_RIGHT)
			         snake.setIsMoving(true);
	  }
	  
	   //if the snake is currently moving in one direction, its movement cannot be reverset to opposite direction.
	  if(ke.getKeyCode()==KeyEvent.VK_UP)
	  {
		  if(snake.getYDir()!=1)         //if the snake is not moving down then the snake can move up.        
		  {
			  snake.setYDir(-1);
		  snake.setXDir(0);
		  }
	  }
	  
	  if(ke.getKeyCode()==KeyEvent.VK_DOWN)
	  {
		  if(snake.getYDir()!=-1)        //if the snake is not moving up, it can start moving down.
		  {
			  snake.setYDir(1);
		  snake.setXDir(0);
	       }
	  }	  
	  
	  if(ke.getKeyCode()==KeyEvent.VK_LEFT)
	  {
		  if(snake.getXDir()!=1)          //if the snake is not moving in the right, it can start moving to left.
		  {
			  snake.setXDir(-1);
		      snake.setYDir(0);
	  
		  }
	  }
	  if(ke.getKeyCode()==KeyEvent.VK_RIGHT)
	  {
		  if(snake.getXDir()!=-1)         //if the snake is not moving in the left, it can start moving to right.
		  {
			  snake.setXDir(1);
		      snake.setYDir(0);
	       }
	  }
  }
  
  public void keyReleased(KeyEvent ke)
  {
	 
  }
	
}
