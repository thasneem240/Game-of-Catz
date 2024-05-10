/**********************************************************************************
 * File: gameofcatz.java													  	  *
 * Author: Mr.A.S.M. Thasneem                          				              *
 * Date Created: 09/10/2021                            				              *
 * Date Modified: 16/10/2021                           				              *               
 * Purpose: Build a representation of the world and explore the possible routes	  *
 *		through the world and rank them					  					  	  *
 **********************************************************************************/
import java.util.*;
import java.io.*;

public class gameofcatz
{
	/* Usage Message */
	private static void usage() 
    {
        System.out.println("\n Usage1: gameofcatz -i ");
		System.out.println(" \n		OR            \n");
		System.out.println(" Usage2: gameofcatz -s inFile saveFile \n");
        System.out.println("         Where");
        System.out.println("        -i: is interactive testing environment ");
		System.out.println("        -s: is simulation mode ");
		System.out.println("        inFile: is Input File ");
		System.out.println("        saveFile: is Output File ");
    }
	
	
	public static void main( String args[] )
	{
		DSAGraph graph = null;
		Routes[] routeArray = null;
		
		if (args.length == 0)
		{
			usage();
		}
		else
		{
			if(args.length == 1 && args[0].equals("-i") )
			{
				int choice = -1;
				String fileName;
			
				// Create a Scanner object for getting input from user
				
				Scanner input = new Scanner (System.in); 
				
				do
				{
					try
					{
						System.out.println("\n\n\n");
						displayMainMenu();
						
						System.out.print(" Please Enter your Choice: ");
						choice = input.nextInt();  // Get input from user
						
						
						switch(choice)
						{
							case 1:
								
								// Build the Graph and get details about Node code and Edge code
								graph = new DSAGraph();
								/* Get the input file */
			
								System.out.print("\n Please input the fileName : ");
								fileName = input.next();
								readFile(fileName, graph);
								
							break;
							
							case 2:
								doSubMenu01(graph);
							break;
							
							case 3:
								doSubMenu02(graph);
							break;
							
							case 4:
								doSubMenu03(graph);
							break;
							
							case 5:
								doSubMenu04(graph);
							break;
							
							case 6:
								doSubMenu05(graph);
							break;
							
							case 7:
								routeArray = graph.generateAllPaths();
								System.out.println("\n\t Successfully Generated All the Routes. ");
							break;
							
							case 8:
								doSubMenu06(graph, routeArray);
							break;
							
							case 9:
								System.out.print("\n Please input the fileName to Save the network : ");
								fileName = input.next();
								saveNetwork(fileName, graph);
							break;
							
							case 10:
								System.out.println("\n\t Exiting\n\n");
							break;
							
							//Print the Error message
							
							default: 
								System.out.println("\n\t Error: The choice must between 1 and 10 "); 
							break;
							
						}
						
					}
						
					catch(IllegalArgumentException e)
					{
						System.out.println( "\n\t Couldn't perform the Operation " + e.getMessage()  );
					}
					
					catch(InputMismatchException e)
					{
						System.out.println( "\n\t Error!! Please input integer value " + e);
						input.reset();
						input.next(); 
					}
					catch( NullPointerException e )
					{
						System.out.println( "\n\t Error!! The Graph is Empty " + e);
					}
					catch( NoSuchElementException e )
					{
						System.out.println( e.getMessage() );
					}
					
				
				}while( choice != 10 ); // Loop until user select exit option.
					
			}
			else
			{
				if( args.length == 3 && args[0].equals("-s")  )
				{
					try
					{
						graph = new DSAGraph();
						
						readFile(args[1], graph); // Load the file
						routeArray = graph.generateAllPaths(); // Generate the routes
						writePaths( args[2], graph, routeArray); // Save the paths into a file. 
					}
					catch( NullPointerException e )
					{
						System.out.println( "\n\t Error!! The Graph is Empty " + e);
					}
					catch( NoSuchElementException e )
					{
						System.out.println( e.getMessage() );
					}
					
				}
				else
				{
					System.out.println("\n Invalid Arguement!!! ");
					usage();
				}
			}
		}
	}
	
	
	
	private static void displayMainMenu()
	{
		System.out.println("\n======================================================================\n");
		System.out.println(" \tMENU ");
		System.out.println(" \n  1. Load input file. ");
		System.out.println("  2. Node Operations (find, insert, delete, update) ");
		System.out.println("  3. Edge Operations (find, add, remove, update) ");
		System.out.println("  4. Parameter tweaks (adjust mapping of codes to penalty/boost"
								+ "features,see sample input file)) ");
		System.out.println("  5. Display graph (weighted adjacency matrix, option to save) ");
		System.out.println("  6. Display world (counts of features, option to save) ");
		System.out.println("  7. Generate routes. ");
		System.out.println("  8. Display routes(ranked, option to save) ");
		System.out.println("  9. Save network. ");
		System.out.println("  10. Exit. \n");
		System.out.println("\n======================================================================\n");
	}
	
	
	
	private static void readFile(String fileName, DSAGraph graph)
	{
		int lineNum = 0; 
		FileInputStream fileStream = null;
		InputStreamReader rdr;
		BufferedReader bufRdr;
		String line;
		String[] splitLine;
		Ncode nCode;
		Ecode eCode;
		
		
		try
		{
			
			fileStream = new FileInputStream(fileName);
			rdr        = new InputStreamReader(fileStream);
			bufRdr     = new BufferedReader(rdr);
			lineNum    = 0;
			line       = bufRdr.readLine();
			
			
			while(line != null)
			{
				lineNum++; 
				splitLine = line.split(" ");
				
				String str = splitLine[0];
				
				if( str.equals("Ncode") )
				{
					String code = splitLine[1];
					int weight = Integer.parseInt(splitLine[2]);
					
					/* Create nCode Object in order to save the code and weight of the Node */
					
					nCode = new Ncode(code, weight);
					
					/* Insert nCode object into nCodeList linked list */
					
					( graph.getNcodeList() ).insertLast(nCode);
				}
				else
				{
					if( str.equals("Node") )
					{
						
						
						String label = splitLine[1];
						String code = splitLine[2];
						
						/* Add the vertex to the graph */
						
						graph.addVertex(label, code);
						
					}
					else
					{
						if( str.equals("Ecode") )
						{
							String code = splitLine[1];
							int weight = Integer.parseInt(splitLine[2]);
							
							/* Creatr eCode Object in order to save the code and weight of the Edge */
							
							eCode = new Ecode(code, weight);
							
							/* Insert eCode object into eCodeList linked list */
							
							( graph.getEcodeList() ).insertLast(eCode);
							
						}
						else
						{
							if( str.equals("Edge") )
							{
							
								String label1 = splitLine[1];
								String label2 = splitLine[2];
								String code = splitLine[3];
								
								
								/* Add the Edge to the graph */
								
								graph.addEdge(label1, label2, code);
							}
							else
							{
								if( str.equals("Start") )
								{
									String label = splitLine[1];
									graph.setStart(label);
								}
								else
								{
									if( str.equals("Target") )
									{
										String label = splitLine[1];
										graph.setTarget(label);
									}
								}
							}
						}
					}
					
					
				}
				
				line = bufRdr.readLine();	
			}
			
			fileStream.close();
			System.out.println(" \n\t Successfully Load the file and Build the graph. ");
			
			
		}
		catch(IOException errorDetails) 
		{
			if(fileStream != null) 
			{
				try 
				{
					fileStream.close(); // close the file
				}
				catch(IOException ex2) 
				{ }
			}
			System.out.println("\n\t Error in fileProcessing: " + errorDetails.getMessage());
		}
		catch( MYException e )
		{
			System.out.println( e.getMessage() );
		}
		catch( NoSuchElementException e )
		{
			System.out.println( e.getMessage() );
		}
		
	}
	
	
	
	private static void doSubMenu01(DSAGraph graph)
	{
		int option = -1;
		String label;
		String code;
		
		Scanner input = new Scanner (System.in); 
		
		do
		{
			try
			{
				System.out.println("\n\n\n");
				
				System.out.println("\n============================\n");
				System.out.println(" \tSUB MENU 01: Node Operations ");
				System.out.println(" \n  1. Find Node ");
				System.out.println("  2. Insert Node ");
				System.out.println("  3. Delete Node ");
				System.out.println("  4. Update Node ");
				System.out.println("  5. Exit From SUB MENU 01 \n");
				System.out.println("\n============================\n");
				
				
				System.out.print("\n Please Enter your Choice: ");
				option = input.nextInt();  // Get input from user
				
				
				switch(option)
				{
					case 1:
					
						System.out.print("\n Please Enter the Node label to Find: ");
						label = input.next();
						
						graph.displayNode(label);
					
					break;
					
					
					case 2:
					
						System.out.println("\n--------------------------------------------\n");
						System.out.println(" Details of Node Code \n");
						
						/* Display the Details of Node code : */
						graph.displayNodeCode();
						System.out.println("\n--------------------------------------------\n");
						
						System.out.print("\n Please Enter the Node label to Insert : ");
						label = input.next();
						
						System.out.print("\n Please Choose the Node Code from Above : ");
						code = input.next();
						
						graph.addVertex(label, code); // Create the Node
						
						System.out.println("\n\t Successfully Created the Node Vertex ");
					
					
					break;
					
					
					case 3:
						
						System.out.print("\n Please Enter the Node label to Delete : ");
						label = input.next();
							
						
						graph.removeVertex(label); // Remove the Node and all Associated edges
						
						System.out.println("\n\t Successfully Removed the Node " + label 
											+ " And its all Associated edges from graph." );
					break;
					
					
					case 4:
					
						System.out.print("\n Please Enter the Node label to Update : ");
						label = input.next();
						
						System.out.print("\n\t Current details of the Node " + label + " :-\n" );
						graph.displayNode(label);
						System.out.println("\n--------------------------------------------\n");
						System.out.println(" Details of Node Code \n");
						
						/* Display the Details of Node code : */
						graph.displayNodeCode();
						System.out.println("\n--------------------------------------------\n");
						
						System.out.print("\n Please Choose the New Node Code from above to update : ");
						code = input.next();
						
						graph.updateVertex(label, code);
						
						System.out.print("\n\t Updated details of the Node " + label + " :-\n" );
						graph.displayNode(label);
						
					
					break;
					
					
					case 5:
						System.out.println("\n\t Exiting from SUB MENU 01");
					break;
					
					
					default: 
					System.out.println("\n\t Error: The choice must between 1 and 5 "); 
					break;
				}
				
			}
				
			catch(IllegalArgumentException e)
			{
				System.out.println( "\n\t Couldn't perform the Operation " + e.getMessage()  );
			}
					
			catch(InputMismatchException e)
			{
				System.out.println( "\n\t Error!! Please input integer value " + e);
				input.reset();
				input.next(); 
			}
			catch( NoSuchElementException e )
			{
				System.out.println( e.getMessage() );
			}
			catch( MYException e )
			{
				System.out.println( e.getMessage() );
			}
			catch( NullPointerException e )
			{
				System.out.println( "\n\t Error!! The Graph is Empty " + e);
			}
			
			
			
		}while( option != 5 );
		
	}
	
	
	
	private static void doSubMenu02(DSAGraph graph)
	{
		int option = -1;
		String label1;
		String label2;
		String code;
		
		Scanner input = new Scanner (System.in); 
		
		do
		{
			try
			{
				System.out.println("\n\n\n");
				
				System.out.println("\n============================\n");
				System.out.println(" \tSUB MENU 02: Edge Operations ");
				System.out.println(" \n  1. Find Edge ");
				System.out.println("  2. Insert Edge ");
				System.out.println("  3. Delete Edge ");
				System.out.println("  4. Update Edge ");
				System.out.println("  5. Exit From SUB MENU 02 \n");
				System.out.println("\n============================\n");
				
				
				System.out.print(" Please Enter your Choice: ");
				option = input.nextInt();  // Get input from user
				
				
				switch(option)
				{
					case 1:
						System.out.println("\n Please Enter Label 1 and Label 2 of the Edge to Find ");
						
						System.out.print("\n Please Enter the Label 1: ");
						label1 = input.next();
						
						System.out.print("\n Please Enter the Label 2: ");
						label2 = input.next();
						
						graph.displayEdge(label1, label2);
					break;
					
					
					case 2:
						System.out.println("\n--------------------------------------------\n");
						System.out.println(" Details of Edge Code \n");
						
						/* Display the Details of Edge code : */
						graph.displayEdgeCode();
						System.out.println("\n--------------------------------------------\n");
						
						
						System.out.println("\n Please Enter Label 1 and Label 2 of the Edge to Insert ");
						
						System.out.print("\n Please Enter the Label 1: ");
						label1 = input.next();
						
						System.out.print("\n Please Enter the Label 2: ");
						label2 = input.next();
						
						
						System.out.print("\n Please Choose the Edge Code from Above : ");
						code = input.next();
						
						graph.addEdge(label1, label2, code); // Create the Edge
						
						System.out.println("\n\t Successfully Created the Edge ");
						
					break;
					
					
					case 3:
						System.out.println("\n Please Enter Label 1 and Label 2 of the Edge to Remove ");
							
						System.out.print("\n Please Enter the Label 1: ");
						label1 = input.next();
							
						System.out.print("\n Please Enter the Label 2: ");
						label2 = input.next();
						
						String label = label1 + label2; // Original label of the particular Edge
						
						graph.removeEdge(label); // Remove the edge from the graph
						
						System.out.println("\n\t Successfully Removed the Edge " + label + " from the Graph " );
					break;
					
					
					case 4:
						System.out.println("\n Please Enter Label 1 and Label 2 of the Edge to Update ");
						
						System.out.print("\n Please Enter the Label 1: ");
						label1 = input.next();
						
						System.out.print("\n Please Enter the Label 2: ");
						label2 = input.next();
						
						System.out.print("\n\t Current details of the Edge " + label1 + label2 + " :-\n" );
						graph.displayEdge(label1, label2);
						System.out.println("\n--------------------------------------------\n");
						System.out.println(" Details of Edge Code \n");
						
						/* Display the Details of Edge code : */
						graph.displayEdgeCode();
						System.out.println("\n--------------------------------------------\n");
						
						System.out.print("\n Please Choose the New EdgeCode from above to update : ");
						code = input.next();
						
						graph.updateEdge(label1, label2, code);
						
						System.out.print("\n\t Updated details of the Edge " + label1 + label2 + " :-\n" );
						graph.displayEdge(label1, label2);
					
					break;
					
					
					case 5:
						System.out.println("\n\t Exiting from SUB MENU 02");
					break;
					
					
					default: 
					System.out.println("\n\t Error: The choice must between 1 and 5 "); 
					break;
				}
				
			}
				
			catch(IllegalArgumentException e)
			{
				System.out.println( "\n\t Couldn't perform the Operation " + e.getMessage()  );
			}
					
			catch(InputMismatchException e)
			{
				System.out.println( "\n\t Error!! Please input integer value " + e);
				input.reset();
				input.next(); 
			}
			catch( NoSuchElementException e )
			{
				System.out.println( e.getMessage() );
			}
			catch( MYException e )
			{
				System.out.println( e.getMessage() );
			}
			catch( NullPointerException e )
			{
				System.out.println( "\n\t Error!! The Graph is Empty " + e);
			}
			
			
			
		}while( option != 5 );
		
	}
	
	
	
	private static void doSubMenu03(DSAGraph graph)
	{
		int option = -1;
		String code;
		int weight;
		
		Scanner input = new Scanner (System.in); 
		
		do
		{
			try
			{
				System.out.println("\n\n\n");
				
				System.out.println("\n============================\n");
				System.out.println(" \tSUB MENU 03: Parameter tweaks ");
				System.out.println("(Adjust mapping of codes to penalty/boost features) ");
				System.out.println(" \n  1. Update Weight of Node Code ");
				System.out.println("  2. Update Weight of Edge Code  ");
				System.out.println("  3. Exit From SUB MENU 03 \n");
				System.out.println("\n============================\n");
				
				
				System.out.print(" Please Enter your Choice: ");
				option = input.nextInt();  // Get input from user
				
				
				switch(option)
				{
					case 1:
						System.out.println("\n--------------------------------------------\n");
						System.out.println(" Details of Node Code \n");
						
						/* Display the Details of Node code : */
						
						graph.displayNodeCode();
						System.out.println("\n--------------------------------------------\n");
						
						System.out.print("\n Please Enter the Node Code to Update penalty or boost features: ");
						code = input.next();
						
						System.out.print("\n Please Enter the penalty or boost features(Weight) of Node code " + code + " : ");
						weight = input.nextInt();
						
						/* Update the weight of the Node code */
						graph.updateNodeCodeWeight(code, weight);
						
						System.out.println("\n\t Successfully Updated the Weight of Node Code, "
											+ " And its all Associated Nodes." );
						
					break;
					
					case 2:
						System.out.println("\n--------------------------------------------\n");
						System.out.println(" Details of Edge Code \n");
						
						/* Display the Details of Edge code : */
						
						graph.displayEdgeCode();
						System.out.println("\n--------------------------------------------\n");
						
						System.out.print("\n Please Enter the Edge Code to Update penalty or boost features: ");
						code = input.next();
						
						System.out.print("\n Please Enter the penalty or boost features(Weight) of Edge code " + code + " : ");
						weight = input.nextInt();
						
						/* Update the weight of the Edge code */
						graph.updateEdgeCodeWeight(code, weight);
						
						System.out.println("\n\t Successfully Updated the Weight of Edge Code, "
											+ " And its all Associated Edges." );
					break;
					
					case 3:
						System.out.println("\n\t Exiting from SUB MENU 03");
					break;
					
					
					default: 
					System.out.println("\n\t Error: The choice must between 1 and 3 "); 
					break;
				}
				
			}
				
			catch(IllegalArgumentException e)
			{
				System.out.println( "\n\t Couldn't perform the Operation " + e.getMessage()  );
			}
					
			catch(InputMismatchException e)
			{
				System.out.println( "\n\t Error!! Please input integer value " + e);
				input.reset();
				input.next(); 
			}
			catch( NoSuchElementException e )
			{
				System.out.println( e.getMessage() );
			}
			catch( MYException e )
			{
				System.out.println( e.getMessage() );
			}
			catch( NullPointerException e )
			{
				System.out.println( "\n\t Error!! The Graph is Empty " + e);
			}
			
			
			
		}while( option != 3 );
		
	}
	
	
	
	private static void doSubMenu04(DSAGraph graph)
	{
		int[][] adjMatrix;
		String[] lookupTable;
		String fileName, space = "";
		int option = -1;
		int i, j, k;
	
		Scanner input = new Scanner (System.in); 
		adjMatrix = graph.getGraphAsMatrix(); // Get the Array of Matrix
		lookupTable = graph.getLabelLookup(); // Get the Array of lookup Table
		
		do
		{
			try
			{
				System.out.println("\n\n\n");
				
				System.out.println("\n============================\n");
				System.out.println(" \tSUB MENU 04: Display Graph ");
				System.out.println(" \n  1. Display as Weighted Adjacency Metrix ");
				System.out.println("  2. Save to File ");
				System.out.println("  3. Exit From SUB MENU 04 \n");
				System.out.println("\n============================\n");
				
				
				System.out.print("\n Please Enter your Choice: ");
				option = input.nextInt();  // Get input from user
				
				
				switch(option)
				{
					case 1:
					
						System.out.println("\n--------------------------------------------\n");
						System.out.println(" Label Lookup \n");
						
						for( i = 1; i < lookupTable.length; i++)
						{
							System.out.printf( "  %3d   %3s\n", i, lookupTable[i]);
						}
						
						System.out.println("\n--------------------------------------------\n");
						
						System.out.println("\t Graph As Weighted Adjacency Metrix :\n");
						
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
					
					break;
					
					case 2:
					
						System.out.print("\n Please input the fileName to Save :");
						fileName = input.next();
						
						writeAsMatrix(fileName, adjMatrix, lookupTable); // Calling the write function
						
					break;
					
					case 3:
						System.out.println("\n\t Exiting from SUB MENU 04");
					break;
					
					
					default: 
					System.out.println("\n\t Error: The choice must between 1 and 3 "); 
					break;
				}
				
			}
				
			catch(IllegalArgumentException e)
			{
				System.out.println( "\n\t Couldn't perform the Operation " + e.getMessage()  );
			}	
			catch(InputMismatchException e)
			{
				System.out.println( "\n\t Error!! Please input integer value " + e);
				input.reset();
				input.next(); 
			}
			catch( NullPointerException e )
			{
				System.out.println( "\n\t Error!! The Graph is Empty " + e);
			}
			
			
			
		}while( option != 3 );
		
	}
	
	
	/* Save Adjacency matrix representation into a File */
	private static void writeAsMatrix( String fileName, int[][] adjMatrix, String[] lookupTable)
	{
		
		FileOutputStream fileStrm = null;
		PrintWriter pw;
		
		try
		{
			fileStrm = new FileOutputStream(fileName);
			pw = new PrintWriter(fileStrm);
			int i, j, k;
			String space = "";
			
			
			
			pw.println("--------------------------------------------\n");
			pw.println(" Label Lookup \n");
			
			for( i = 1; i < lookupTable.length; i++)
			{
				pw.printf( "  %3d   %3s\n", i, lookupTable[i]);
			}
			
			pw.println("\n--------------------------------------------\n");
			
			pw.println("\t Graph As Weighted Adjacency Metrix :\n");
			
			for( i = 0; i < adjMatrix.length; i++)
			{
				for( j = 0; j < adjMatrix.length; j++)
				{
					if( i == 0 && j == 0)
					{
						pw.printf("  %2s ", space);  
					}
					else
					{
						pw.printf("  %2d ", adjMatrix[i][j]);  
					}
				}
				
				pw.println();
			}
			
			System.out.println("\n\t Successefully write the matrix into file and " 
								+ fileName + " created. \n"); 
			
			pw.close(); // close the Writer
		}
		
		catch(IOException e)
		{
			if (fileStrm != null)
			{
				try
				{
					fileStrm.close();
				}
				
				catch(IOException ex2)
				{ }
			}
			System.out.println("\n\t Error in writing to file: " + e.getMessage());
		}
	}
	
	
	
	private static void doSubMenu05(DSAGraph graph)
	{
		int option = -1;
		String fileName;
		
		Scanner input = new Scanner (System.in); 
		
		do
		{
			try
			{
				System.out.println("\n\n\n");
				
				System.out.println("\n============================\n");
				System.out.println(" \tSUB MENU 05: Display World ");
				System.out.println(" \n  1. Display as Adjacency List ");
				System.out.println("  2. Save to File ");
				System.out.println("  3. Exit From SUB MENU 05 \n");
				System.out.println("\n============================\n");
				
				
				System.out.print("\n Please Enter your Choice: ");
				option = input.nextInt();  // Get input from user
				
				
				switch(option)
				{
					case 1:
					
						
						System.out.println("\n---------------------------------------------------------------------\n");
						
						System.out.println("\t Graph As Adjacency List and Features of the Graph :\n");
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
							
						System.out.println("\n---------------------------------------------------------------------\n");
					
					break;
					
					case 2:
						System.out.print("\n Please input the fileName to Save :");
						fileName = input.next();
						
						writeAsList(fileName, graph);
					
					break;
					
					case 3:
						System.out.println("\n\t Exiting from SUB MENU 05");
					break;
					
					
					default: 
					System.out.println("\n\t Error: The choice must between 1 and 3 "); 
					break;
				}
				
			}
				
			catch(IllegalArgumentException e)
			{
				System.out.println( "\n\t Couldn't perform the Operation " + e.getMessage()  );
			}	
			catch(InputMismatchException e)
			{
				System.out.println( "\n\t Error!! Please input integer value " + e);
				input.reset();
				input.next(); 
			}
			catch( NullPointerException e )
			{
				System.out.println( "\n\t Error!! The Graph is Empty " + e);
			}
			
			
			
		}while( option != 3 );
		
	}
	
	
	
	/* Save world into a File */
	
	private static void writeAsList( String fileName, DSAGraph graph) 
	{
		
		FileOutputStream fileStrm = null;
		PrintWriter pw;
		
		try
		{
			fileStrm = new FileOutputStream(fileName);
			pw = new PrintWriter(fileStrm);
			
			pw.println("---------------------------------------------------------------------\n");
						
			pw.println("\t Graph As Adjacency List and Features of the Graph :\n");
			graph.WriteAsList(pw);
			
			pw.print("\n Vertex Count  : ");
			pw.print( graph.getVertexCount() );
			pw.print( "\n Edge Count    : " );
			pw.print( graph.getEdgeCount() + "\n" );
			pw.println("\n=============================================================\n");
			pw.println(" Details of Node Code \n");
			
			/* Write the Details of Node code : */
			
			graph.WriteNodeCode(pw);
			
			pw.println("\n=============================================================\n");
			pw.println(" Details of Edge Code \n");
			
			/* Write the Details of Edge code : */
			
			graph.WriteEdgeCode(pw);
				
			pw.println("\n---------------------------------------------------------------------\n");
				
			
			System.out.println("\n\t Successefully write the world into file and " 
								+ fileName + " created. "); 
			
			pw.close(); // close the Writer
		}
		
		catch(IOException e)
		{
			if (fileStrm != null)
			{
				try
				{
					fileStrm.close();
				}
				
				catch(IOException ex2)
				{ }
			}
			System.out.println("\n\t Error in writing to file: " + e.getMessage());
		}
	}
	
	
	
	private static void doSubMenu06(DSAGraph graph, Routes[] routeArray)
	{
		int option = -1;
		String fileName;
		
		Scanner input = new Scanner (System.in); 
		
		do
		{
			try
			{
				System.out.println("\n\n\n");
				
				System.out.println("\n============================\n");
				System.out.println(" \tSUB MENU 06: Display Routes ");
				System.out.println(" \n  1. Display paths With the Rank ");
				System.out.println("  2. Save to File ");
				System.out.println("  3. Exit From SUB MENU 06 \n");
				System.out.println("\n============================\n");
				
				
				System.out.print("\n Please Enter your Choice: ");
				option = input.nextInt();  // Get input from user
				
				
				switch(option)
				{
					case 1:
					
						
						System.out.println("\n---------------------------------------------------------------------\n");
						
						System.out.println("\t All the paths from " + graph.getStart() + " to " + graph.getTarget() + " :\n");
						
						for( int i = 0; i < routeArray.length ; i++)
						{
							System.out.printf( "Rank %-4d     TotalCost = %-4d      TotalNodeValue = %-4d      TotalEdgeValue = %-4d     PATH : %s \n",
												(i+1), routeArray[i].getTotalCost(), routeArray[i].getNodeTotal(), 
												routeArray[i].getEdgeTotal(), routeArray[i].getPath() );
						}	
						
						System.out.println("\n---------------------------------------------------------------------\n");
					
					break;
					
					case 2:
						System.out.print("\n Please input the fileName to Save the Routes :");
						fileName = input.next();
						
						writePaths(fileName, graph, routeArray);
					
					break;
					
					case 3:
						System.out.println("\n\t Exiting from SUB MENU 06");
					break;
					
					
					default: 
					System.out.println("\n\t Error: The choice must between 1 and 3 "); 
					break;
				}
				
			}
				
			catch(IllegalArgumentException e)
			{
				System.out.println( "\n\t Couldn't perform the Operation " + e.getMessage()  );
			}	
			catch(InputMismatchException e)
			{
				System.out.println( "\n\t Error!! Please input integer value " + e);
				input.reset();
				input.next(); 
			}
			catch( NullPointerException e )
			{
				System.out.println( "\n\t Error!! The Graph is Empty " + e);
			}
			
			
			
		}while( option != 3 );
		
	}
	
	
	
	/* Save world into a File */
	
	private static void writePaths( String fileName, DSAGraph graph, Routes[] routeArray) 
	{
		
		FileOutputStream fileStrm = null;
		PrintWriter pw;
		
		try
		{
			
			fileStrm = new FileOutputStream(fileName);
			pw = new PrintWriter(fileStrm);
			
			pw.println("\n---------------------------------------------------------------------\n");
			
			pw.println("\t All the paths from " + graph.getStart() + " to " + graph.getTarget() + " :\n");
			
			for( int i = 0; i < routeArray.length ; i++)
			{
				pw.printf( "Rank %-4d     TotalCost = %-4d      TotalNodeValue = %-4d      TotalEdgeValue = %-4d     PATH : %s \n",
						(i+1), routeArray[i].getTotalCost(), routeArray[i].getNodeTotal(), 
						routeArray[i].getEdgeTotal(), routeArray[i].getPath() );
			}	
			
			pw.println("\n---------------------------------------------------------------------\n");
			
			System.out.println("\n\t Successefully write the Paths into file and " 
								+ fileName + " created. "); 
								
			pw.close(); // close the Writer
		}
		
		catch(IOException e)
		{
			if (fileStrm != null)
			{
				try
				{
					fileStrm.close();
				}
				
				catch(IOException ex2)
				{ }
			}
			System.out.println("\n\t Error in writing to file: " + e.getMessage());
		}
	}
	
	
	
	private static void saveNetwork(String fileName, DSAGraph graph)
	{
		FileOutputStream fileStrm = null;
		PrintWriter pw;
		DSALinkedList list;
		
		try
		{
			fileStrm = new FileOutputStream(fileName);
			pw = new PrintWriter(fileStrm);
			
			
			pw.println("#---------------------------------------------------------------------#");
						
			pw.println("# \tSave Network as the File that can be Re readable by the program #");
			pw.println("#---------------------------------------------------------------------#");
			pw.println("# File Format: ");
			pw.println("#");
			pw.println("# Node label code");
			pw.println("# Edge label label code");
			pw.println("# Ncode code weight");
			pw.println("# Ecode code weight");
			pw.println("# Start label");
			pw.println("# Target label");
			pw.println("#");
			pw.println("# DEFINE Ncode");
			
			list = graph.getNcodeList(); // Get the list of Node code;
			
			for(Object o : list )
			{
				Ncode n = (Ncode)o;
				pw.println("Ncode " + n.getNodeCode() + " " + n.getNodeWeight() );
			}
			pw.println("#");
			
			pw.println("# DEFINE Nodes Label and Code");
			graph.defineNodes(pw); // Write Nodes label and code into file.
			pw.println("#");
			
			pw.println("# DEFINE Ecode");
			list = graph.getEcodeList(); // Get the list of Edge code;
			
			for(Object o : list )
			{
				Ecode e = (Ecode)o;
				pw.println("Ecode " + e.getEdgeCode() + " " + e.getEdgeWeight() );
			}
			pw.println("#");
			
			pw.println("# DEFINE Edges Labels and Code");
			graph.defineEdges(pw); // Write Edges labels and code into file.
			pw.println("#");
			
			pw.println("# DEFINE Start and Target");
			pw.println("Start " + graph.getStart() );
			pw.println("Target " + graph.getTarget() );
			
			
			System.out.println("\n\t Successefully Save the Network into file and " + fileName + " created. "); 
			
			pw.close(); // close the Writer
		}
		
		catch(IOException e)
		{
			if (fileStrm != null)
			{
				try
				{
					fileStrm.close();
				}
				
				catch(IOException ex2)
				{ }
			}
			System.out.println("\n\t Error in writing to file: " + e.getMessage());
		}
		
	}
	
	
}