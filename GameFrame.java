package FlappySquare;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame {

	GamePanel panel;
	
	GameFrame() {
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("Flappy Bird Game");
		this.setResizable(false);
		this.setBackground(new Color(51, 204, 255));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
