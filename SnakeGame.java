package Snake;
import java.applet.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

public class SnakeGame extends Applet implements Runnable, KeyListener          
{
	Graphics gfx;                        //a graphics object
	Image img;     
    Thread thread;                     //an image object
	Snake snake;
	boolean gameOver;
	Token token;
    
  public void init()                     //called by browser to indicate that the program is loaded
  {
	  this.resize(500,400);              //to resize applet size(width,height)
	  img= createImage(500,400);         //to create a drawable image allowing double buffering
	  gameOver= false;
	  //gfx:: an offscreen Graphics due to getGraphics();   g:: an onscreen graphics
	  gfx= img.getGraphics();            //returns a a Graphics2D, which can be used to draw into this image.
	  this.addKeyListener(this);
	  snake= new Snake();
	  token= new Token(snake);
	  thread= new Thread(this);
      thread.start();
  }
  
  public void paint(Graphics g)          
  {
	  gfx.setColor(Color.black);         //sets the color of the elements associated with Graphics class
	  gfx.fillRect(0, 0, 500, 400);      //to fill the rectangular element;in this case the applet with black
	  if(!gameOver)
	  {
	   snake.Draw(gfx);
	   token.draw(gfx);
	  }
	  else
	  {
		  gfx.setColor(Color.red);
		  gfx.drawString("Game Over", 236, 198);
		  gfx.drawString("Score:: "+token.getScore(), 236, 208);
	  }
	  g.drawImage(img, 0, 0, null);      //returns true if the img is completely loaded else returns false
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
	  
	for(;;)
	{
		if(!gameOver)
		{
			snake.move();
			this.checkGameOver();
			token.snakeCollision();
		}
		showStatus("Score:: "+token.getScore());
		this.repaint();
		try {
			Thread.sleep(40);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
  }
  
  public void checkGameOver()
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
	  if(!snake.isMoving())
	  {
		  if(ke.getKeyCode()==KeyEvent.VK_UP || ke.getKeyCode()==KeyEvent.VK_DOWN ||
				  ke.getKeyCode()==KeyEvent.VK_LEFT || ke.getKeyCode()==KeyEvent.VK_RIGHT)
			         snake.setIsMoving(true);
	  }
	  if(ke.getKeyCode()==KeyEvent.VK_UP)
	  {
		  if(snake.getYDir()!=1)
		  {
			  snake.setYDir(-1);
		  snake.setXDir(0);
		  }
	  }
	  
	  if(ke.getKeyCode()==KeyEvent.VK_DOWN)
	  {
		  if(snake.getYDir()!=-1)
		  {
			  snake.setYDir(1);
		  snake.setXDir(0);
	       }
	  }	  
	  
	  if(ke.getKeyCode()==KeyEvent.VK_LEFT)
	  {
		  if(snake.getXDir()!=1)
		  {
			  snake.setXDir(-1);
		      snake.setYDir(0);
	  
		  }
	  }
	  if(ke.getKeyCode()==KeyEvent.VK_RIGHT)
	  {
		  if(snake.getXDir()!=-1)
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
