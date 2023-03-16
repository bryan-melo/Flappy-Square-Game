package FlappySquare;
import java.awt.*;
import java.awt.event.*;

public class Bird extends Rectangle {
	
	int yVelocity;
	int speed = 2;
	int gravity = 6;
	
	Bird(int x, int y, int BIRD_WIDTH, int BIRD_HEIGHT) {
		super(x, y, BIRD_WIDTH, BIRD_HEIGHT);
	}
	
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			setYDirection(speed + 2);
			move();
				
			if (speed > 0)
				speed = -8;
		}	
	}
	
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			speed = 2;
			setYDirection(-speed - gravity);
			move();
		}
	}
	
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	
	public void move() {
		y -= yVelocity;
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x,  y,  width, height);
	}

}
