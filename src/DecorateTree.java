/*author: Blake Trebelhorn and Michael Salato
  *date: 12/6/15
  *description: Displays Christmas tree and allows user to draw ornaments
  *and tinsel. Sounds play when started, and a song plays on loop. Bells
  *jingle when an ornament is placed
  *proposed points (52 of 52): The applet meets the requirement of "doing something with a GUI or 
  *applet" and is pretty darn cool and festive, so it should get all of the points
  */

	//bell.wav taken from: http://www.soundjay.com/bell-sound-effect.html
	//yay.wav taken from: http://www.wavsource.com/sfx/sfx3.htm
	//Christmas.aiff converted at: http://audio.online-convert.com/convert-to-aiff
	//Christmas.wav (source for Christmas.aiff) taken from: http://www.thewavsite.com/christmas.htm

import java.applet.AudioClip;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JApplet;

public class DecorateTree extends JApplet
{

	private int HEIGHT = 600;
	private int WIDTH = 400;
	int mouseX;
	int mouseY;
	boolean draw = true;
	boolean click = false;
	boolean drag = false;
	Button clearButton;
	private AudioClip yay;
	private AudioClip christmas;
	private AudioClip bell;
	
	public void init()
	{		
		setSize(WIDTH, HEIGHT);
		
		//create Audio clips from src folder
		yay = getAudioClip(getDocumentBase(), "yay.wav");
		christmas = getAudioClip(getDocumentBase(), "Christmas.aiff");
		bell = getAudioClip(getDocumentBase(), "bell.wav");
		clearButton = new Button("Clear");
		
		//set up button size, background and location and add it to the applet
		setLayout(null);
		clearButton.setBackground(Color.GREEN);
		clearButton.setLocation(10,10);
		clearButton.setSize(60, 30);
		this.add(clearButton);
		 
		//call run method
		run();
		
		//set up listeners for clicking, moving, and button click
		ClickListen c = new ClickListen();
		addMouseListener(c);
		
		MyMouseMotionListener motionListen = new MyMouseMotionListener();
		addMouseMotionListener(motionListen);
		
		ButtonListen bl = new ButtonListen();
		clearButton.addActionListener(bl);
		
	}
	
	/**
	 * run method -- runs while the rest of the app
	 */
	public void run() 
	{
		//plays yay AudioClip and loops christmas AudioClip
		yay.play();
		christmas.loop();
	}
	
	public void paint(Graphics g)
	{
		//if statement to draw the tree and presents just once
		//so when new things are drawn they don't get overwritten
		if (draw)
		{
			//create better colors that suit christmas using RGB
			Color christmasGreen = new Color(00, 64, 00);
			Color christmasRed = new Color(209, 17, 17);
			Color christmasBrown = new Color(66, 33, 00);

			//create several arrays for drawing triangles for the tree
			int[] xPoints1 = {200, 100, 300};
			int[] yPoints1 = {0, 100, 100};
			int[] xPoints2 = {200 , 75, 325};
			int[] yPoints2 = {60, 200, 200};
			int[] xPoints3 = {200, 50, 350};
			int[] yPoints3 = {130, 300, 300};
			int[] xPoints4 = {200, 25, 375};
			int[] yPoints4 = {220, 400, 400};
		
			//draw background with color white
			g.setColor(Color.white);
			g.fillRect(0, 0, WIDTH, HEIGHT);

			//draw christmas tree
			g.setColor(christmasGreen);
			g.fillPolygon(xPoints1, yPoints1, 3);
			g.fillPolygon(xPoints2, yPoints2, 3);
			g.fillPolygon(xPoints3, yPoints3, 3);
			g.fillPolygon(xPoints4, yPoints4, 3);
			g.setColor(christmasBrown);
			g.fillRect(170, 400, 60, 100);
		
			//draw present 1
			g.setColor(christmasRed);
			g.fillRect(50, 430, 100, 70);
			g.setColor(Color.PINK);
			g.fillRect(50, 460, 100, 10);
			g.fillRect(95, 430, 10, 70);
		
			//draw present 2
			Color darkBlue = new Color(00, 33, 66);
			g.setColor(darkBlue);
			g.fillRect(250, 470, 130, 30);
			g.setColor(Color.gray);
			g.fillRect(250, 481, 130, 8);
			g.fillRect(310, 470, 10, 30);
		
			//draw present 3
			Color darkPurple = new Color(102,51,153);
			g.setColor(darkPurple);
			g.fillRect(250, 420, 90, 50);
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(250, 440, 90, 10);
			g.fillRect(290, 420, 10, 50);
			
			//set draw to false, so the background doesn't paint again
			draw = false;
		}
		
		//if statement to run if the mouse is clicked
		if(click)
		{
			Color christmasRed = new Color(209, 17, 17);
			g.setColor(christmasRed);
			g.fillOval(mouseX-10, mouseY-10, 20, 20);
			bell.play();
		}
		
		//if statement to run if the mouse is dragged
		if(drag)
		{
			Color christmasSilver = new Color(200, 200, 200);
			g.setColor(christmasSilver);
			g.fillRoundRect(mouseX-5, mouseY-5, 10, 10, 10, 10);
		}
		
	}
	
	private class ClickListen implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) 
		{
			//set click to true so the click if statement in paint method
			//set draw and and drag to false so they don't run
			click = true;
			draw = false;
			drag = false;
			//get X,Y values where mouse is for clicking
			mouseX = e.getX();
			mouseY = e.getY();
			
			
			//call paint method
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) 
		{
			/*
			 * doot
			 * doot
			 * nothing here
			 */
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class MyMouseMotionListener implements MouseMotionListener
	{

		@Override
		public void mouseDragged(MouseEvent e) 
		{
			//set drag to true so the if statement runs in the paint method
			//set click and drag to false so they don't run
			click = false;
			drag = true;
			draw = false;
			mouseX = e.getX();
			mouseY = e.getY();
			
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ButtonListen implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//set draw to true so the 'canvas' is reset
			//set click and drag to false so they don't run
			draw = true;
			click = false;
			drag = false;
			repaint();
		}
		
	}
}
