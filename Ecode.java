/**********************************************************************************
 * File: Ecode.java													  	  	  	  *
 * Author: Mr.A.S.M. Thasneem                          				              *
 * Date Created: 10/10/2021                            				              *
 * Date Modified: 16/10/2021                           				              *               
 * Purpose: Create Ecode class to save the EdgeCode and Weight					  *
 *			(Lookup Table for Edge Code)										  *
 **********************************************************************************/
 import java.io.*;
public class Ecode
{
	private String edgeCode;
	private int edgeWeight;
	
	
	public Ecode( String pCode, int pWeight)
	{
		this.edgeCode = pCode;
		this.edgeWeight = pWeight;
	}
	
	
	public String getEdgeCode()
	{
		return edgeCode;
	}
	
	
	public int getEdgeWeight()
	{
		return edgeWeight;
	}
	
	
	public void setEdgeCode(String pCode)
	{
		 edgeCode = pCode;
	}
	
	
	public void setEdgeWeight(int pWeight)
	{
		edgeWeight = pWeight;
	}
	
	
	public void display()
	{
		String str;
		
		switch(edgeCode)
		{
			case "P":
				str = "Portal";
			break;
			
			case "J":
				str = "Jump";
			break;
			
			case "H":
				str = "HighJump";
			break;
			
			case "D":
				str = "Drop";
			break;
			
			case "L":
				str = "Ladder";
			break;
			
			case "S":
				str = "Snake";
			break;
			
			case "-":
				str = "Empty";
			break;
			
			default :
				str = edgeCode;
			break;
			
		}
		
		System.out.printf( " %15s  =  %-15s  Weight = %-10d\n", edgeCode, str, edgeWeight );
	}
	
	
	/* Write into file */
	public void display(PrintWriter pw)
	{
		String str;
		
		switch(edgeCode)
		{
			case "P":
				str = "Portal";
			break;
			
			case "J":
				str = "Jump";
			break;
			
			case "H":
				str = "HighJump";
			break;
			
			case "D":
				str = "Drop";
			break;
			
			case "L":
				str = "Ladder";
			break;
			
			case "S":
				str = "Snake";
			break;
			
			case "-":
				str = "Empty";
			break;
			
			default :
				str = edgeCode;
			break;
			
		}
		
		pw.printf( " %15s  =  %-15s  Weight = %-10d\n", edgeCode, str, edgeWeight );
	}
}