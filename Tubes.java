package FlappySquare;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Tubes extends Rectangle {
	
	int speed = 2;
	
	
	Tubes(int x, int y, int TUBE_WIDTH, int TUBE_HEIGHT) {
		super(x, y, TUBE_WIDTH, TUBE_HEIGHT);
		move();
	}
	
	public void move() {
			x -= speed;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
	}
}
