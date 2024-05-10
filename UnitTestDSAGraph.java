/**********************************************************************************
 * File: UnitTestDSAGraph.java												  	  *
 * Author: Mr.A.S.M. Thasneem                          				              *
 * Date Created: 15/10/2021                            				              *
 * Date Modified: 17/10/2021                           				              *               
 * Purpose: Test DSAGraph class				  				  					  *
 **********************************************************************************/
 
import java.util.*;
import java.io.*;
public class UnitTestDSAGraph
{
	public static void main( String[] args )
	{
		DSAGraph graph = new DSAGraph();
		Ncode n;
		Ecode e;
		
		/* Ncode list */
		n = new Ncode("F", -1);
		( graph.getNcodeList() ).insertLast(n);
		n = new Ncode("D", 100);
		( graph.getNcodeList() ).insertLast(n);
		n = new Ncode("T", 1);
		( graph.getNcodeList() ).insertLast(n);
		n = new Ncode("-", 0);
		( graph.getNcodeList() ).insertLast(n);
		
		
		/* Ecode List */
		e = new Ecode("D", 1);
		( graph.getEcodeList() ).insertLast(e);
		e = new Ecode("J", 2);
		( graph.getEcodeList() ).insertLast(e);
		e = new Ecode("H", 1);
		( graph.getEcodeList() ).insertLast(e);
		e = new Ecode("-", 0);
		( graph.getEcodeList() ).insertLast(e);
		
		
		test01(graph);
		System.out.println("\n");
		
		test02(graph);
		System.out.println("\n");
		
		test03(graph);
		System.out.println("\n");
		
		test04(graph);
		System.out.println("\n");
		
		test05(graph);
		System.out.println("\n");
		
		test06(graph);
		System.out.println("\n");
		
		test07(graph);
		System.out.println("\n");
		
		test08(graph);
		System.out.println("\n");
		
		test09(graph);
		System.out.println("\n");
		
		test10(graph);
		System.out.println("\n");
		
		test11(graph);
		System.out.println("\n");
		
		test12(graph);
		System.out.println("\n");
		
		test13(graph);
		System.out.println("\n");
		
		test14(graph);
		System.out.println("\n");
		
		test15(graph);
		System.out.println("\n");
		
		
	}
		
	
		public static void test01(DSAGraph graph)
		{
			try
			{
				System.out.println("\n\nTesting 01 addVertex() method");
				System.out.println("=======================================");
				
				graph.addVertex("F", "F");
				graph.addVertex("B", "D");
				graph.addVertex("C", "F");
				graph.addVertex("D", "T");
				graph.addVertex("E", "-");
				graph.addVertex("G", "D");
				graph.addVertex("A", "-");
				
				graph.displayAsList();
			
			}
			catch( MYException e )
			{
				System.out.println( e.getMessage() );
			}	
			
		}
		
		
		public static void test02(DSAGraph graph)
		{
			try
			{
				System.out.println("\nTesting 02 addEdge() method");
				System.out.println("=======================================");
				
				graph.addEdge("A", "E", "D");
				graph.addEdge("A", "B", "J");
				graph.addEdge("A", "D", "H");
				graph.addEdge("A", "C", "-");
				graph.addEdge("B", "E", "-");
				graph.addEdge("C", "D", "J");
				graph.addEdge("D", "F", "H");
				graph.addEdge("E", "F", "D");
				graph.addEdge("E", "G", "D");
				graph.addEdge("F", "G", "-");
				
				graph.displayAsList();
			
			}
			catch( MYException e )
			{
				System.out.println( e.getMessage() );
			}	
			
		}
		
		
		public static void test03(DSAGraph graph)
		{
			try
			{
				System.out.println("\n\nTesting 03 Find Node ");
				System.out.println("=======================================");
				
				System.out.println("Find A :");
				graph.displayNode("A");
				
				System.out.println("Find Z :");
				graph.displayNode("Z");
			}
			catch( NoSuchElementException e )
			{
				System.out.println( e.getMessage() );
			}
				
		}
		
		
		
		public static void test04(DSAGraph graph)
		{
			try
			{
				System.out.println("\n\nTesting 04 Insert Node ");
				System.out.println("=======================================");
				
				System.out.println("Insert Z Node:");
				System.out.println("graph.addVertex(Z, D); ");
				graph.addVertex("Z", "D");
				
				System.out.println("Find Z :");
				graph.displayNode("Z");
			}
			catch( NoSuchElementException e )
			{
				System.out.println( e.getMessage() );
			}
			catch( MYException e )
			{
				System.out.println( e.getMessage() );
			}
				
		}
		
		
		public static void test05(DSAGraph graph)
		{
			try
			{
				System.out.println("\n\nTesting 05 Delete Node ");
				System.out.println("=======================================");
				
				
				System.out.println("Find B :");
				graph.displayNode("B");
				
				
				System.out.println("\nDelete B Node:");
				System.out.println("graph.removeVertex(B); ");
				graph.removeVertex("B");
				
				System.out.println("Find B :");
				graph.displayNode("B");
			}
			catch( NoSuchElementException e )
			{
				System.out.println( e.getMessage() );
			}
		
				
		}
		
		
		public static void test06(DSAGraph graph)
		{
			try
			{
				System.out.println("\n\nTesting 06 Update Node ");
				System.out.println("=======================================");
				
				
				System.out.println("Before Update :");
				graph.displayNode("A");
				
				
				System.out.println("\ngraph.updateVertex(A, F); ");
				graph.updateVertex("A", "F");
				
				System.out.println("\nAfter Update :");
				graph.displayNode("A");
			}
			catch( NoSuchElementException e )
			{
				System.out.println( e.getMessage() );
			}
			catch( MYException e )
			{
				System.out.println( e.getMessage() );
			}
		
				
		}
		
		
		public static void test07(DSAGraph graph)
		{
			try
			{
				System.out.println("\n\nTesting 07 Find Edge ");
				System.out.println("=======================================");
				
				System.out.println("Find CD :");
				graph.displayEdge("C","D");
				
				System.out.println("Find DC :");
				graph.displayEdge("D","C");
			}
			catch( NoSuchElementException e )
			{
				System.out.println( e.getMessage() );
			}
				
		}
		
		
		public static void test08(DSAGraph graph)
		{
			try
			{
				System.out.println("\n\nTesting 08 Insert Edge ");
				System.out.println("=======================================");
				
				System.out.println("Insert DC Edge:");
				System.out.println("graph.addEdge(D, C, -); ");
				graph.addEdge("D", "C", "-");
				
				System.out.println("\nFind DC :");
				graph.displayEdge("D", "C");
			}
			catch( NoSuchElementException e )
			{
				System.out.println( e.getMessage() );
			}
			catch( MYException e )
			{
				System.out.println( e.getMessage() );
			}
				
		}
		
		
		
		public static void test09(DSAGraph graph)
		{
			try
			{
				System.out.println("\n\nTesting 09 Delete Edge ");
				System.out.println("=======================================");
				
				
				System.out.println("Find DC:");
				graph.displayEdge("D","C");
				
				
				System.out.println("\nDelete DC Edge:");
				System.out.println("graph.removeEdge(DC); ");
				graph.removeEdge("DC");
				
				System.out.println("Find DC :");
				graph.displayEdge("D","C");
			}
			catch( NoSuchElementException e )
			{
				System.out.println( e.getMessage() );
			}
		
				
		}
		
		
		
		public static void test10(DSAGraph graph)
		{
			try
			{
				System.out.println("\n\nTesting 10 Update Edge ");
				System.out.println("=======================================");
				
				
				System.out.println("Before Update :");
				graph.displayEdge("D", "F");
				
				
				System.out.println("\ngraph.updateEdge(D, F, J); ");
				graph.updateEdge("D", "F", "J");
				
				System.out.println("\nAfter Update :");
				graph.displayEdge("D", "F");
			}
			catch( NoSuchElementException e )
			{
				System.out.println( e.getMessage() );
			}
			catch( MYException e )
			{
				System.out.println( e.getMessage() );
			}
		
				
		}
		
		
		public static void test11(DSAGraph graph)
		{
			try
			{
				System.out.println("\n\nTesting 11 Update Weight of Node Code ");
				System.out.println("=======================================");
				
				
				System.out.println("Before Update :\n");
				graph.displayNodeCode();
				System.out.println();
				graph.displayAsList();
				
				
				System.out.println("\ngraph.updateNodeCodeWeight(F, -45); ");
				graph.updateNodeCodeWeight("F", -45);
				
				System.out.println("\nAfter Update :\n");
				graph.displayNodeCode();
				System.out.println();
				graph.displayAsList();
			}
			catch( NoSuchElementException e )
			{
				System.out.println( e.getMessage() );
			}
			catch( MYException e )
			{
				System.out.println( e.getMessage() );
			}
		
				
		}
		
		
		
		public static void test12(DSAGraph graph)
		{
			try
			{
				System.out.println("\n\nTesting 12 Update Weight of Edge Code ");
				System.out.println("=======================================");
				
				
				System.out.println("Before Update :\n");
				graph.displayEdgeCode();
			
				
				System.out.println("\ngraph.updateEdgeCodeWeight(J, 20); ");
				graph.updateEdgeCodeWeight("J", 20);
				
				System.out.println("\nAfter Update :\n");
				graph.displayEdgeCode();
			}
			catch( NoSuchElementException e )
			{
				System.out.println( e.getMessage() );
			}
			catch( MYException e )
			{
				System.out.println( e.getMessage() );
			}
		
				
		}
		
		
		public static void test13(DSAGraph graph)
		{
			try
			{
				int i, j;
				String space = "";
				int[][] adjMatrix = graph.getGraphAsMatrix(); // Get the Array of Matrix
				String[] lookupTable = graph.getLabelLookup();
				
				System.out.println("\n\n Testing 13 Display as Adjacency Matrix");
				System.out.println("=======================================");
				
				for( i = 0; i < adjMatrix.length; i++)
				{
					for( j = 0; j < adjMatrix.length; j++)
					{
						if( i == 0 && j == 0)
						{
							System.out.printf("  %2s ", space);  
						}
						else
						{
							System.out.printf("  %2d ", adjMatrix[i][j]);  
						}
					}
					
					System.out.println();
				}
				
				
			}
			catch( NoSuchElementException e )
			{
				System.out.println( e.getMessage() );
			}
			
		}
		
		
		
		public static void test14(DSAGraph graph)
		{
			try
			{
				
				
				System.out.println("\n\n Testing 14 Display World ");
				System.out.println("=======================================");
				
				graph.displayAsList();
						
				System.out.print("\n Vertex Count  : ");
				System.out.print( graph.getVertexCount() );
				System.out.print( "\n Edge Count    : " );
				System.out.print( graph.getEdgeCount() + "\n" );
				System.out.println("\n=============================================================\n");
				System.out.println(" Details of Node Code \n");
						
				/* Display the Details of Node code : */
				
				graph.displayNodeCode();
				
				System.out.println("\n=============================================================\n");
				System.out.println(" Details of Edge Code \n");
				
				/* Display the Details of Edge code : */
				
				graph.displayEdgeCode();
				
			}
			catch( NoSuchElementException e )
			{
				System.out.println( e.getMessage() );
			}
			
		}
		
		
		
		public static void test15(DSAGraph graph)
		{
			try
			{
				graph.setStart("A");
				graph.setTarget("G");
				
				Routes[] routeArray = graph.generateAllPaths();
				
				System.out.println("\n\n Testing 15 Display paths ");
				System.out.println("=======================================");
				
				System.out.println("\t All the paths from " + graph.getStart() + " to " + graph.getTarget() + " :\n");
						
				for( int i = 0; i < routeArray.length ; i++)
				{
					System.out.printf( "Rank %-4d     TotalCost = %-4d      TotalNodeValue = %-4d      TotalEdgeValue = %-4d     PATH : %s \n",
										(i+1), routeArray[i].getTotalCost(), routeArray[i].getNodeTotal(), 
										routeArray[i].getEdgeTotal(), routeArray[i].getPath() );
				}	
			
				
			}
			catch( NoSuchElementException e )
			{
				System.out.println( e.getMessage() );
			}
			
		}
		
		
}