/**********************************************************************************
 * File: Ncode.java													  	  	  	  *
 * Author: Mr.A.S.M. Thasneem                          				              *
 * Date Created: 10/10/2021                            				              *
 * Date Modified: 16/10/2021                           				              * 
 * Purpose: Create Ncode class to save the nodeCode and Weight					  *
 *		(Lookup Table for Node Code)	  										  *
 **********************************************************************************/
 import java.io.*;
public class Ncode
{
	private String nodeCode;
	private int nodeWeight;
	
	
	public Ncode( String pCode, int pNodeWeight)
	{
		this.nodeCode = pCode;
		this.nodeWeight = pNodeWeight;
	}
	
	
	public String getNodeCode()
	{
		return nodeCode;
	}
	
	
	public int getNodeWeight()
	{
		return nodeWeight;
	}
	
	
	public void setNodeCode(String pCode)
	{
		nodeCode = pCode;
	}
	
	
	public void setNodeWeight(int pNodeWeight)
	{
		nodeWeight = pNodeWeight;
	}
	
	
	public void display()
	{
		String str;
		
		switch(nodeCode)
		{
			case "F":
				str = "Food";
			break;
			
			case "D":
				str = "Dog";
			break;
			
			case "T":
				str = "Toy";
			break;
			
			case "H":
				str = "Human";
			break;
			
			case "B":
				str = "Box or Block";
			break;
			
			case "S":
				str = "Sofa";
			break;
			
			case "t":
				str = "Table";
			break;
			
			case "C":
				str = "Counter";
			break;
			
			case "W":
				str = "Window";
			break;
			
			case "-":
				str = "Empty";
			break;
			
			default :
				str = nodeCode;
			break;
			
		}
		
		System.out.printf( " %15s  =  %-15s  Weight = %-10d\n", nodeCode, str, nodeWeight );
	}
	
	
	/* Write into file */
	public void display(PrintWriter pw)
	{
		String str;
		
		switch(nodeCode)
		{
			case "F":
				str = "Food";
			break;
			
			case "D":
				str = "Dog";
			break;
			
			case "T":
				str = "Toy";
			break;
			
			case "H":
				str = "Human";
			break;
			
			case "B":
				str = "Box or Block";
			break;
			
			case "S":
				str = "Sofa";
			break;
			
			case "t":
				str = "Table";
			break;
			
			case "C":
				str = "Counter";
			break;
			
			case "W":
				str = "Window";
			break;
			
			case "-":
				str = "Empty";
			break;
			
			default :
				str = nodeCode;
			break;
			
		}
		
		pw.printf( " %15s  =  %-15s  Weight = %-10d\n", nodeCode, str, nodeWeight );
	}
}