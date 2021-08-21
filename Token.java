package Snake;

import java.awt.Color;
import java.awt.Graphics;

public class Token 
{
 private int x, y, score;                                   //x: position of the token along X axis; y: position of token along Y axis; score: score after consuming each token.
 private Snake snake;

 public Token(Snake s)
 {
	 x= (int)(Math.random()*495);                        //token must lie within the working area.
	 y= (int)(Math.random()*395);
	 snake= s;
 }
 
 public void changePosition()
 {
	 x= (int)(Math.random()*495);
	 y= (int)(Math.random()*395);
 }
 
 public int getScore()
 {
	 return score;
 }
 
 public void draw(Graphics g)
 {
	 g.setColor(Color.yellow);                                      //to set the color of the token to yellow.
	 g.fillRect(x, y, 6, 6);                                        //the token will be of rectangular shape having dimension 6*6
	 //g.drawString(""+score,0,0);
 }
 
 public boolean snakeCollision()
 {
	 int snakeX= snake.getHeadX()+2;
	 int snakeY= snake.getHeadY()+2;
	 if(snakeX>= x-1 && snakeX<=x+7)                                 //to check whether the snake is coming in contact with the token or not.
		 if(snakeY>= y-1 && snakeY<=y+7)
		 {
			 changePosition();                               //if the snake touches the token, then the latter is consumed a new token is to be generated elsewhere.
			 score++;                                        //score is to be increased for each token consumed by the snake.
			 snake.setElongate(true);                        //if token is consumed then, the snake can get elongated.
			 return true;
		 }
	 return false;
 }
}
