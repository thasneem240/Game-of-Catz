/**********************************************************************************
 * File: Routes.java													  	  	  *
 * Author: Mr.A.S.M. Thasneem                          				              *
 * Date Created: 10/10/2021                            				              *
 * Date Modified: 17/10/2021                           				              *               
 * Purpose: Create Routes class to save the path and cost of the route	  		  *
 **********************************************************************************/
public class Routes
{
	private String path;
	private int nodeTotal;
	private int edgeTotal;
	private int totalCost;
	
	
	
	public Routes( String path, int nodeTotal, int edgeTotal )
	{
		this.path = path;
		this.nodeTotal = nodeTotal;
		this.edgeTotal = edgeTotal;
		this.totalCost = nodeTotal + edgeTotal;
	}
	
	
	public String getPath()
	{
		return path;
	}
	
	
	public int getNodeTotal()
	{
		return nodeTotal;
	}
	
	
	public int getEdgeTotal()
	{
		return edgeTotal;
	}
	
	
	public int getTotalCost()
	{
		return totalCost;
	}
	
	
	public String toString()
	{
		String str = "  totalCost =   " + totalCost + "   nodeTotal =   "  + nodeTotal 
						+ "  edgeTotal =   " + edgeTotal + "   Path :	" + path + "\n";

		return str;
	}
}