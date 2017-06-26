import java.util.*;
import java.awt.Color;

//for a single residue - currently immutable
public class Residue
{
	//member variables
	private char let;
	private int type;
	private int indexVal;
	
	//constructor
	public Residue(char oneLetter)
	{
		//find residue value
		int index;
		for (index = 0; index < ONE_LETTERS.length; index++)
			if (oneLetter == ONE_LETTERS[index])
			{
				let = oneLetter;
				type = TYPES[index];
				indexVal = index;
				break;
			}
			
		//not a valid residue
		if (index >= THREE_LETTERS.length)
			throw new IllegalArgumentException("Not a valid residue: "+oneLetter);
	}
	public Residue(String threeLetter)
	{
		//find residue value
		int index;
		for (index = 0; index < THREE_LETTERS.length; index++)
			if (threeLetter.equals(THREE_LETTERS[index]))
			{
				let = ONE_LETTERS[index];
				type = TYPES[index];
				indexVal = index;
				break;
			}
			
		//not a valid residue
		if (index >= THREE_LETTERS.length)
			throw new IllegalArgumentException("Not a valid residue: "+threeLetter);
	}
	
	//getter methods
	public char oneLetterCode()
	{
		return let;
	}
	public String threeLetterCode()
	{
		return THREE_LETTERS[indexVal];
	}
	public String name()
	{
		return NAMES[indexVal];
	}
	public int residueType()
	{
		return type;
	}
	public Color residueColor()
	{
		return TYPE_COLOR[residueType()];
	}
	
	//static variables
	protected static final Color[] TYPE_COLOR = {Color.RED, Color.GRAY, Color.YELLOW, Color.MAGENTA, Color.BLUE};
	protected static final int NEG_CHARGE = 0,
							   NEG_PHILIC = 1,
							   HYDROPHOBIC= 2,
							   POS_PHILIC = 3,
							   POS_CHARGE = 4;
	protected static final Color NEG_CHARGE_COL = TYPE_COLOR[NEG_CHARGE],
								 NEG_PHILIC_COL = TYPE_COLOR[NEG_PHILIC],
								 HYDROPHOBIC_COL= TYPE_COLOR[HYDROPHOBIC],
								 POS_PHILIC_COL = TYPE_COLOR[POS_PHILIC],
								 POS_CHARGE_COL = TYPE_COLOR[POS_CHARGE];
	private static char[] ONE_LETTERS = {
										 'A',
										 'R',
										 'N',
										 'D',
										 'C',
										 'E',
										 'Q',
										 'G',
										 'H',
										 'I',
										 'L',
										 'K',
										 'M',
										 'F',
										 'P',
										 'S',
										 'T',
										 'W',
										 'Y',
										 'V'
										};
	private static String[] THREE_LETTERS = {
											 "ALA",
											 "ARG",
											 "ASN",
											 "ASP",
											 "CYS",
											 "GLU",
											 "GLN",
											 "GLY",
											 "HIS",
											 "ILE",
											 "LEU",
											 "LYS",
											 "MET",
											 "PHE",
											 "PRO",
											 "SER",
											 "THR",
											 "TRP",
											 "TYR",
											 "VAL"
											},
							NAMES = {
									 "ALANINE",
									 "ARGININE",
									 "ASPARAGINE",
									 "ASPARTIC ACID",
									 "CYSTEINE",
									 "GLUTAMIC ACID",
									 "GLUTAMINE",
									 "GLYCINE",
									 "HISTIDINE",
									 "ISOLEUCINE",
									 "LEUCINE",
									 "LYSINE",
									 "METHIONINE",
									 "PHENYLALANINE",
									 "PROLINE",
									 "SERINE",
									 "THREONINE",
									 "TRYPTOPHAN",
									 "TYROSINE",
									 "VALINE"
									};
	private static int[] TYPES = {
								  HYDROPHOBIC,
								  POS_CHARGE,
								  POS_PHILIC,
								  NEG_CHARGE,
								  NEG_PHILIC,
								  NEG_CHARGE,
								  POS_PHILIC,
								  HYDROPHOBIC,
								  POS_CHARGE,
								  HYDROPHOBIC,
								  HYDROPHOBIC,
								  POS_CHARGE,
								  HYDROPHOBIC,
								  HYDROPHOBIC,
								  POS_PHILIC,
								  NEG_PHILIC,
								  NEG_PHILIC,
								  HYDROPHOBIC,
								  HYDROPHOBIC,
								  HYDROPHOBIC
								 };
	
	//convenience methods
	private char getOneLetter(String threeLetter)
	{
		int index;
		for (index = 0; index < THREE_LETTERS.length; index++)
			if (threeLetter.equalsIgnoreCase(THREE_LETTERS[index]))
				return ONE_LETTERS[index];
				
		throw new IllegalArgumentException("Not a valid residue: "+threeLetter);
	}
}