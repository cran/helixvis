import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class StaticVis
{
	//output passed sequence as a PNG wenxiang diagram
	protected static void outputWenxiang(Sequence seq, File output)
	{
		//check and make sure all variables are valid
		if (seq.length() > MAX_RESIDUES_WENXIANG)
			throw new IllegalArgumentException("Too many residues: "+seq.length()+" (MAX = "+MAX_RESIDUES_WENXIANG+")");
		else if (seq.length() <= 1)
			throw new IllegalArgumentException("Too few residues: "+seq.length()+" (MIN = "+MIN_RESIDUES+")");
		
		//initialize stuff
		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		
		//basic background stuff & sequence information
		g.setColor(BG_COLOR);
		g.fillRect(0, 0, bi.getWidth(), bi.getHeight());	//white background (instead of default transparent for PNGs)
		g.setColor(TEXT_COLOR);
		g.setFont(FONT_STUFF);
		g.drawString("Name:     " + seq.getName(), FONT_X_L1, FONT_Y_L1);
		g.drawString("Sequence: " + seq.toString(), FONT_X_L2, FONT_Y_L2);
		
		//draw arcs - only as many as needed and as much arc as needed
		g.setColor(LINE_COLOR);
		int numDegreesLeft = 100 * (seq.length() - 1),
			topLeftX = WIDTH / 2 - WENXIANG_GAP - WENXIANG_GAP / 2,
			topLeftY = WIDTH / 2 - WENXIANG_GAP / 2,
			currRadius = WENXIANG_GAP, temp,
			startDegree = 0;
		for (int i = 0; i < 10; i++)
		{
			temp = currRadius + currRadius;
			
			if (numDegreesLeft >= 180.0)
			{
				g.drawArc(topLeftX, topLeftY, temp, temp,
					startDegree, WENXIANG_SINGLE_ARC);
				if (i % 2 != 0)
					topLeftX -= WENXIANG_GAP;
				
				currRadius += WENXIANG_GAP / 2;
				topLeftY -= WENXIANG_GAP / 2;
				numDegreesLeft -= WENXIANG_SINGLE_ARC;
			}
			else
			{
				g.drawArc(topLeftX, topLeftY, temp, temp,
					startDegree, numDegreesLeft);
				break;
			}
			
			startDegree = (startDegree + 180) % 360; //keep switching between 0 and 180
		}
		
		//draw residues
		int cX = 300,
			cY = 300 + WENXIANG_GAP / 2,
			bigRadius = WENXIANG_GAP / 2,
			resRadius = WENXIANG_RESID_RADIUS,
			currSemi = 0;
		double angle = 0.0,
			   leftoverAngle = 100.0 * Math.PI / 180.0;
		int residX, residY;
		for (int i = 0; i < seq.length(); i++)
		{
			//if at a new semicircle, add half of WENXIANG_GAP to bigRadius
			if (angle % Math.PI < leftoverAngle && Math.abs(angle % Math.PI - leftoverAngle) > EPSILON)
			{
				//if even semicircle, subtract half WENXIANG_GAP from cX
				if (currSemi % 2 == 0)
					cX -= WENXIANG_GAP / 2;
				//if odd semicircle, add half WENXIANG_GAP to cX
				else
					cX += WENXIANG_GAP / 2;
				//add one to semicircle counter
				currSemi++;
				
				//add half WENXIANG_GAP to the radius of the semicircle
				bigRadius += WENXIANG_GAP / 2;
			}
			
			//draw residue
			residX = (int)(cX + bigRadius * Math.cos(angle) + 0.5);
			residY = (int)(cY - bigRadius * Math.sin(angle) + 0.5);
			Util.doCenterCircle(g, LINE_COLOR, seq.residAt(i).residueColor(),
				residX, residY, WENXIANG_RESID_RADIUS);
			
			//update angle
			angle += Math.PI * 100.0 / 180.0;
		}
		
		//output to png file
		try {
			ImageIO.write(bi, "PNG", output);
		} catch (IOException e) {
			throw new RuntimeException("Something went wrong with the input/output of the wenxiang diagram.");
		}
	}

	//output passed sequence as a PNG helical wheel diagram
	//600x600 pixels
	protected static void outputWheel(Sequence seq, File output)
	{
		//check and make sure all variables are valid
		if (seq.length() > MAX_RESIDUES_WHEEL)
			throw new IllegalArgumentException("Too many residues: "+seq.length()+" (MAX = "+MAX_RESIDUES_WHEEL+")");
		else if (seq.length() <= 1)
			throw new IllegalArgumentException("Too few residues: "+seq.length()+" (MIN = "+MIN_RESIDUES+")");
		
		//initialize stuff
		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		
		//basic background stuff & sequence information
		g.setColor(BG_COLOR);
		g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
		g.setColor(TEXT_COLOR);
		g.setFont(FONT_STUFF);
		g.drawString("Name:     " + seq.getName(), FONT_X_L1, FONT_Y_L1);
		g.drawString("Sequence: " + seq.toString(), FONT_X_L2, FONT_Y_L2);
		
		//draw lines
		g.setColor(LINE_COLOR);
		for (int i = 0; i < seq.length() - 1; i++)	//don't draw line connecting last and first residues
		{
			g.drawLine(WHEEL_RESID_CENTERS[i][0], WHEEL_RESID_CENTERS[i][1],
				WHEEL_RESID_CENTERS[i+1][0], WHEEL_RESID_CENTERS[i+1][1]);
		}
		
		//draw residues
		for (int i = 0; i < seq.length(); i++)
		{
			Util.doCenterCircle(g, LINE_COLOR, seq.residAt(i).residueColor(),
				WHEEL_RESID_CENTERS[i][0], WHEEL_RESID_CENTERS[i][1], WHEEL_RESID_RADIUS);
		}
		
		//output to png file
		try {
			ImageIO.write(bi, "PNG", output);
		} catch (IOException e) {
			throw new RuntimeException("Something went wrong with the input/output of the helical wheel.");
		}
	}
	
	//rotation method
	//parameter takes length-2 array (x then y coordinate)
	//returns in same format
	private static int[] rotatePoint(int[] point, double radians, int[] around)
	{
		double x = point[0] - around[0],
			   y = point[1] - around[1];
			   
		double xp = x * Math.cos(radians) - y * Math.sin(radians),
			   yp = y * Math.cos(radians) + x * Math.sin(radians);
			   
		xp += around[0];
		yp += around[1];
			   
		return new int[]{(int)(xp + 0.5), (int)(yp + 0.5)};
	}
	
	//static constants
	private static final int WIDTH = 600,
							 HEIGHT= 600,
							 MIN_RESIDUES = 2,
							 MAX_RESIDUES_WHEEL = 18,
							 MAX_RESIDUES_WENXIANG = 19,
							 CENTER_X = 300,
							 CENTER_Y = 325,
							 RADIUS = 225,
							 WHEEL_RESID_RADIUS = 37,
							 WENXIANG_RESID_RADIUS = 25,
							 START_X_CENTER = CENTER_X,
							 START_Y_CENTER = CENTER_Y - RADIUS,	//start with residue directly above center of wheel
							 NUM_RESIDUES = 18,
							 WENXIANG_GAP = 45,
							 WENXIANG_SINGLE_ARC = 180;
	private static final double ROTATE_ANGLE = 100.0 * Math.PI / 180.0,		//100 degrees converted to radians
								EPSILON = 1e-5;
	private static final int[] CENTER_POINT = {CENTER_X, CENTER_Y};
	private static final Color BG_COLOR = Color.WHITE,
							   LINE_COLOR=Color.BLACK,
							   TEXT_COLOR=Color.BLACK;
	private static final Font FONT_STUFF = new Font("Courier", Font.PLAIN, 20);
	private static final int FONT_X_L1 = 10, FONT_Y_L1 = 25,
							 FONT_X_L2 = 10, FONT_Y_L2 = 45;
							   
	//store residue center points so don't have to recalculate everytime
	private static int[][] WHEEL_RESID_CENTERS;
	static
	{
		WHEEL_RESID_CENTERS = new int[MAX_RESIDUES_WHEEL][2];	//2 is used b/c always 2-dimensional (don't need a dimensional constant here)
		WHEEL_RESID_CENTERS[0] = new int[]{START_X_CENTER, START_Y_CENTER};
		for (int i = 1; i < WHEEL_RESID_CENTERS.length; i++)
			WHEEL_RESID_CENTERS[i] = rotatePoint(WHEEL_RESID_CENTERS[i-1], ROTATE_ANGLE, CENTER_POINT);
	}
}