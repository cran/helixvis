import java.awt.*;
import java.io.*;
import java.util.*;

//first residue is N-terminus; last residue is C-terminus of subsequence
public class Sequence
{
	//member variables
	private ArrayList<Residue> seq;
	private String name;

	//constructor
	public Sequence()
	{
	  this("");
	}
	public Sequence(String setSeq)
	{
		this(setSeq, "Unnamed sequence");
	}
	public Sequence(String setSeq, String setName)
	{
		seq = new ArrayList<Residue>();
		seq.ensureCapacity(setSeq.length());

		Residue temp;
		for (int i = 0; i < setSeq.length(); i++)
		{
			temp = new Residue(setSeq.charAt(i));
			seq.add(temp);
		}

		name = setName;
	}

	//static method for rJava to use (helixvis package)
	public static String drawStuff(String seqVal, String seqName, String outputFile, String type)
	{
		String message = "";
		try {
			File output = new File(outputFile);
			Sequence s = new Sequence(seqVal, seqName);

			//need to draw wenxiang
			if (type.equalsIgnoreCase("WENXIANG"))
				s.wenxiangPNG(output);
			//need to draw wheel
			else if (type.equalsIgnoreCase("WHEEL"))
				s.wheelPNG(output);
			//error: invalid diagram type
			else
				throw new IllegalArgumentException("`" + type + "` is not a valid type of diagram to draw (either `wheel` or `wenxiang`)");

			message = "Success";
		} catch (Exception e) {
			message = "Exception thrown: " + e.getMessage();
		} finally {
			return message;
		}
	}

	//NEED TO INCLUDE ERROR HANDLING HERE
	public Residue residAt(int index)
	{
		return seq.get(index);
	}
	//NEED TO INCLUDE ERROR HANDLING HERE
	public char charAt(int index)
	{
		return residAt(index).oneLetterCode();
	}
	public String getSeq()
	{
		return toString();
	}
	public int length()
	{
		return seq.size();
	}
	public String getName()
	{
		return name;
	}

	//methods that output visualizations for sequences
	public void wheelPNG(File output)
	{
		StaticVis.outputWheel(this, output);
	}
	public void wenxiangPNG(File output)
	{
		StaticVis.outputWenxiang(this, output);
	}

	//convenience methods
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (Residue curr : seq)
			sb.append(curr.oneLetterCode());

		return sb.toString();
	}

	//need to include for rJava - doesn't do anything
	public static void main(String[] args) {}
}
