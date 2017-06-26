import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

//common methods for all classes in project to use
public class Util
{
	//method to draw circle given center location and radius
	protected static void drawCenterCircle(Graphics2D g, Color outline, int cX, int cY, int radius)
	{
		//save old Color and save new one
		Color old = g.getColor();
		g.setColor(outline);
		
		//draw circle
		int topLeftX = cX - radius,
			topLeftY = cY - radius,
			width = radius + radius,
			height = width;
		g.drawOval(topLeftX, topLeftY, width, height);
		
		//set back to old Color
		g.setColor(old);
	}
	
	//method to fill circle given center location and radius
	protected static void fillCenterCircle(Graphics2D g, Color fill, int cX, int cY, int radius)
	{
		//save old Color and save new one
		Color old = g.getColor();
		g.setColor(fill);
		
		//fill circle
		int topLeftX = cX - radius,
			topLeftY = cY - radius,
			width = radius + radius,
			height = width;
		g.fillOval(topLeftX, topLeftY, width, height);
		
		//set back to old Color
		g.setColor(old);
	}
	
	//method to draw circle with fill and outline given center location and radius
	public static void doCenterCircle(Graphics2D g, Color outline, Color fill, int cX, int cY, int radius)
	{
		fillCenterCircle(g, fill, cX, cY, radius);
		drawCenterCircle(g, outline, cX, cY, radius);
	}
	
	//print the output of a Graphics2D class to a file
	//not needed anymore
	/*public void outputPNG(Graphics2D g, File output)
	{
		BufferedImage bi;
		g.drawImage(bi, null, 0, 0);
		
		ImageIO.write(bi, "PNG", output);
	}*/
}