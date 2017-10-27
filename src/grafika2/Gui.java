package grafika2;

//To do z tego co jest w mouse released/draged zrobic metody/fcje np drawRect itp (?)
//polynomial
//jak sie uda to wywalic troche globalnych i porobic milion metod
//rozbic na klasy (?) rect, oval, poly (?)

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.scene.shape.Ellipse;

public class Gui 
{
	JFrame frame;
	public static void main(String[] args)
	{
		Gui gui = new Gui();
		gui.GUI();
	}
	
	private void GUI()
	{
		frame = new JFrame();
		DrawPanel panel = new DrawPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("lab2");
		frame.setSize(800, 500);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
}

class DrawPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
	Graphics2D g2d;
	BufferedImage image;
	int[] currArgs = new int[4];
	int currFigure = 1;
	JButton rectButton;
	JButton ovalButton;
	JButton polynomialButton;
	
	ArrayList<Rectangle> rectArray;
	ArrayList<int[]> ovalArray;
	
	public DrawPanel()
	{
		super();
		setLayout(null);
		setBackground(new Color(100, 100, 100));
		image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
		
		rectButton = new JButton("Rectangle");
		ovalButton = new JButton("Oval");
		polynomialButton = new JButton("Polynomial");
		
		rectArray = new ArrayList<Rectangle>();
		ovalArray = new ArrayList<int[]>();
		
		rectButton.setBounds(20, 50, 100, 30);
		ovalButton.setBounds(20, 80, 100, 30);
		polynomialButton.setBounds(20, 110, 100, 30);
		
		try
		{
			image = ImageIO.read(new File("cat1.bmp"));
		}
		catch (IOException e)
		{
			System.out.println("The image cannot be loaded");
		}
		
		add(rectButton);
		add(ovalButton);
		add(polynomialButton);
		rectButton.addActionListener(this);
		ovalButton.addActionListener(this);
		polynomialButton.addActionListener(this);
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		g2d.drawImage(image, null, 350, 25);
		for(int i = 0; i < rectArray.size(); i++)
			g2d.draw(rectArray.get(i));
		
		for(int i = 0; i < ovalArray.size(); i++)
			g2d.drawOval(ovalArray.get(i)[0], ovalArray.get(i)[1], ovalArray.get(i)[2], ovalArray.get(i)[3]);;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		Graphics g = getGraphics();
		g2d = (Graphics2D) g;
		g2d.setXORMode( Color.white );
		
		if(arg0.getX() >= currArgs[0] && arg0.getY() >= currArgs[1])
		{
			if(currFigure == 1)
			{
				g2d.drawRect(currArgs[0], currArgs[1], currArgs[2] - currArgs[0], currArgs[3] - currArgs[1]);
				currArgs[2] = arg0.getX();
				currArgs[3] = arg0.getY();
				g2d.drawRect(currArgs[0], currArgs[1], currArgs[2] - currArgs[0], currArgs[3] - currArgs[1]);
			}
			else if(currFigure == 0)
			{
				g2d.drawOval(currArgs[0], currArgs[1], currArgs[2] - currArgs[0], currArgs[3] - currArgs[1]);
				currArgs[2] = arg0.getX();
				currArgs[3] = arg0.getY();
				g2d.drawOval(currArgs[0], currArgs[1], currArgs[2] - currArgs[0], currArgs[3] - currArgs[1]);
			}
		}
		else if(arg0.getX() < currArgs[0] && arg0.getY() >= currArgs[1])
		{
			if(currFigure == 1)
			{
				g2d.drawRect(currArgs[2], currArgs[1], currArgs[0] - currArgs[2], currArgs[3] - currArgs[1]);
				currArgs[2] = arg0.getX();
				currArgs[3] = arg0.getY();
				g2d.drawRect(currArgs[2], currArgs[1], currArgs[0] - currArgs[2], currArgs[3] - currArgs[1]);
			}
			else if(currFigure == 0)
			{
				g2d.drawOval(currArgs[2], currArgs[1], currArgs[0] - currArgs[2], currArgs[3] - currArgs[1]);
				currArgs[2] = arg0.getX();
				currArgs[3] = arg0.getY();
				g2d.drawOval(currArgs[2], currArgs[1], currArgs[0] - currArgs[2], currArgs[3] - currArgs[1]);
			}
		}
		else if(arg0.getX() < currArgs[0] && arg0.getY() < currArgs[1])
		{
			if(currFigure == 1)
			{
				g2d.drawRect(currArgs[2], currArgs[3], currArgs[0] - currArgs[2], currArgs[1] - currArgs[3]);
				currArgs[2] = arg0.getX();
				currArgs[3] = arg0.getY();
				g2d.drawRect(currArgs[2], currArgs[3], currArgs[0] - currArgs[2], currArgs[1] - currArgs[3]);
			}
			else if(currFigure == 0)
			{
				g2d.drawOval(currArgs[2], currArgs[3], currArgs[0] - currArgs[2], currArgs[1] - currArgs[3]);
				currArgs[2] = arg0.getX();
				currArgs[3] = arg0.getY();
				g2d.drawOval(currArgs[2], currArgs[3], currArgs[0] - currArgs[2], currArgs[1] - currArgs[3]);
			}
		}
		else if(arg0.getX() >= currArgs[0] && arg0.getY() < currArgs[1])
		{
			if(currFigure == 1)
			{
				g2d.drawRect(currArgs[0], currArgs[3], currArgs[2] - currArgs[0], currArgs[1] - currArgs[3]);
				currArgs[2] = arg0.getX();
				currArgs[3] = arg0.getY();
				g2d.drawRect(currArgs[0], currArgs[3], currArgs[2] - currArgs[0], currArgs[1] - currArgs[3]);
			}
			else if(currFigure == 0)
			{
				g2d.drawOval(currArgs[0], currArgs[3], currArgs[2] - currArgs[0], currArgs[1] - currArgs[3]);
				currArgs[2] = arg0.getX();
				currArgs[3] = arg0.getY();
				g2d.drawOval(currArgs[0], currArgs[3], currArgs[2] - currArgs[0], currArgs[1] - currArgs[3]);
			}
		}
		System.out.println(arg0.getX() + ", " + arg0.getY());
	}

	@Override
	public void mouseMoved(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) 
	{}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		Graphics g = getGraphics();
		g2d = (Graphics2D) g;
		
		currArgs[0] = arg0.getX();
		currArgs[1] = arg0.getY();
		currArgs[2] = arg0.getX();
		currArgs[3] = arg0.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		Graphics g = getGraphics();
		g2d = (Graphics2D) g;
		
		if(arg0.getX() >= currArgs[0] && arg0.getY() >= currArgs[1])
		{
			currArgs[2] = arg0.getX();
			currArgs[3] = arg0.getY();
			if(currFigure == 1)
			{
				g2d.drawRect(currArgs[0], currArgs[1], currArgs[2] - currArgs[0], currArgs[3] - currArgs[1]);
				rectArray.add(new Rectangle(currArgs[0], currArgs[1], currArgs[2] - currArgs[0], currArgs[3] - currArgs[1]));
			}
			else if(currFigure == 0)
			{
				g2d.drawOval(currArgs[0], currArgs[1], currArgs[2] - currArgs[0], currArgs[3] - currArgs[1]);
				int[] arr = new int[4];
				arr[0] = currArgs[0];
				arr[1] = currArgs[1];
				arr[2] = currArgs[2] - currArgs[0];
				arr[3] = currArgs[3] - currArgs[1];
				ovalArray.add(arr);
			}
		}
		else if(arg0.getX() < currArgs[0] && arg0.getY() >= currArgs[1])
		{
			currArgs[2] = arg0.getX();
			currArgs[3] = arg0.getY();
			if(currFigure == 1)
			{
				g2d.drawRect(currArgs[2], currArgs[1], currArgs[0] - currArgs[2], currArgs[3] - currArgs[1]);
			}
			else if(currFigure == 0)
			{
				g2d.drawOval(currArgs[2], currArgs[1], currArgs[0] - currArgs[2], currArgs[3] - currArgs[1]);
			}
		}
		else if(arg0.getX() < currArgs[0] && arg0.getY() < currArgs[1])
		{
			currArgs[2] = arg0.getX();
			currArgs[3] = arg0.getY();
			if(currFigure == 1)
			{
				g2d.drawRect(currArgs[2], currArgs[3], currArgs[0] - currArgs[2], currArgs[1] - currArgs[3]);
			}
			else if(currFigure == 0)
			{
				g2d.drawOval(currArgs[2], currArgs[3], currArgs[0] - currArgs[2], currArgs[1] - currArgs[3]);
			}
		}
		else if(arg0.getX() >= currArgs[0] && arg0.getY() < currArgs[1])
		{
			currArgs[2] = arg0.getX();
			currArgs[3] = arg0.getY();
			if(currFigure == 1)
			{
				g2d.drawRect(currArgs[0], currArgs[3], currArgs[2] - currArgs[0], currArgs[1] - currArgs[3]);
			}
			else if(currFigure == 0)
			{
				g2d.drawOval(currArgs[0], currArgs[3], currArgs[2] - currArgs[0], currArgs[1] - currArgs[3]);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
		if(source == ovalButton)
			currFigure = 0;
		if(source == rectButton)
			currFigure = 1;
		if(source == polynomialButton)
			currFigure = 2;
		repaint();
	}
}