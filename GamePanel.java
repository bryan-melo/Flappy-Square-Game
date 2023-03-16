package FlappySquare;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
	
	static final int GAME_WIDTH = 375;
	static final int GAME_HEIGHT = 667;
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BIRD_WIDTH = 35;
	static final int BIRD_HEIGHT = 35;
	static final int TUBE_WIDTH = 65;
	static final int TUBE_GAP = 150;
	int tubeHeight;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Bird bird;
	Tubes topTube1;
	Tubes topTube2;
	Tubes botTube1;
	Tubes botTube2;
	Score score;
	
	GamePanel() {
		newBird();
		newTubes1();
		newTubes2();
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBird() {
		bird = new Bird(100, (GAME_HEIGHT / 2) - (BIRD_HEIGHT / 2), BIRD_WIDTH, BIRD_HEIGHT);
	}
	
	public void newTubes1() {		
		random = new Random();		
		tubeHeight = random.nextInt((GAME_HEIGHT / 2) - TUBE_GAP) + 100;
				
		topTube1 = new Tubes(GAME_WIDTH + TUBE_WIDTH, 0, TUBE_WIDTH, (GAME_HEIGHT - tubeHeight) - (TUBE_GAP + 100));
		botTube1 = new Tubes(GAME_WIDTH + TUBE_WIDTH, (GAME_HEIGHT - tubeHeight) - 100, TUBE_WIDTH, tubeHeight);
	}
	
	public void newTubes2() {
		random = new Random();		
		tubeHeight = random.nextInt((GAME_HEIGHT / 2) -  TUBE_GAP) + 100;
				
		topTube2 = new Tubes(GAME_WIDTH + TUBE_WIDTH, 0, TUBE_WIDTH, (GAME_HEIGHT - tubeHeight) - (TUBE_GAP + 100));
		botTube2 = new Tubes(GAME_WIDTH + TUBE_WIDTH, (GAME_HEIGHT - tubeHeight) - 100, TUBE_WIDTH, tubeHeight);
	}
	
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,  0,  0,  this);
	}
	
	public void draw(Graphics g) {
		bird.draw(g);
		topTube1.draw(g);
		botTube1.draw(g);
		topTube2.draw(g);
		botTube2.draw(g);
	}
	
	public void move() {
		bird.move();
		topTube1.move();
		botTube1.move();
		topTube2.move();
		botTube2.move();
		
		if (topTube1.x == (bird.x + 90)) {
			newTubes2();
		}
		if (topTube2.x == (bird.x + 90)) {
			newTubes1();
		}
		
		
	}
	
	public void checkCollision() {
		
		if (bird.y <= 0) {
			bird.y = 0;
		}
		if (bird.y >= GAME_HEIGHT - BIRD_HEIGHT - 100) {
			bird.y = GAME_HEIGHT - BIRD_HEIGHT - 100;
			gameThread.suspend();
		}
		
		/*
		if (bird.x == (topTube1.x - (TUBE_WIDTH / 2)) || bird.x == (botTube1.x - (TUBE_WIDTH / 2))) {
			gameThread.suspend();
		}
		
		if (bird.x == (topTube2.x - (TUBE_WIDTH / 2)) || bird.x == (botTube2.x - (TUBE_WIDTH / 2))) {
			gameThread.suspend();
		}
		*/
		
	}
	
	public void run() {
		// game loop
				long lastTime = System.nanoTime();
				double amountOfTicks = 60.0;
				double ns = 1000000000 / amountOfTicks;
				double delta = 0;
				while(true) {
					long now = System.nanoTime();
					delta += (now - lastTime) / ns;
					lastTime = now;
					if (delta >= 1) {
						move();
						checkCollision();
						repaint();
						delta--;
						
					}
					
				}
		}
	
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			bird.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			bird.keyReleased(e);
		}
	}

}
