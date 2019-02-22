package bb_display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	private String title;
	private int width, height;
	
	public static JFrame frame;
	private Canvas canvas;
	
	
	public Display(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		creatDisplay();
	}
	
	public void creatDisplay(){
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setBackground(Color.black);
		canvas.setFocusable(false);
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}
	
	public Canvas getCanvas(){
		return canvas;
	}

}
