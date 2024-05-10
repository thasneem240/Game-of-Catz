/**********************************************************************************
 * File: DSAGraph.java													  	  	  *
 * Author: Mr.A.S.M. Thasneem                          				              *
 * Date Created: 21/09/2021                            				              *
 * Date Modified: 17/10/2021                           				              *               
 * Purpose: Create DSAGraph class inorder to create graph						  *
 **********************************************************************************/
 
import java.util.*;
import java.io.*;
public class DSAGraph
{
	/* Attributes of DSAGraph*/
	
	private DSALinkedList vertices; // Vertices, edges as a Linked list 
	private DSALinkedList edges;
	private DSALinkedList nCodeList; // Lookup Table for Node code
	private DSALinkedList eCodeList; // Lookup Table for Edge code
	private String start;
	private String target;
	private int vertexCount;
	private int edgeCount;
	
	
	/* Default Constructor */
	public DSAGraph()
	{
		vertices = new DSALinkedList(); // Create a list of vetices 
		edges = new DSALinkedList(); // Create a list of edges
		nCodeList = new DSALinkedList();
		eCodeList = new DSALinkedList();
		vertexCount = 0;
		edgeCount = 0;
		start = null;
		target = null;
	}
	
	
	
	/* Add new vertex into the vertices list */
	
	public void addVertex( String label, String code ) throws MYException
	{
		int nodeWeight = 0;
		boolean hasCode = false;
		
		/* Traverse through nCodeList and assign the weight for this code */
		
		for(Object o : nCodeList )
		{
			Ncode n = (Ncode)o;
			
			if( ( n.getNodeCode() ).equals(code) )
			{
				nodeWeight = n.getNodeWeight();
				hasCode = true;
			}
		}
		
		/* Check the vertex is already in their or not and check the 
		   code is there in nCodeList */
		
		if( !hasVertex(label) && hasCode ) 
		{
			/* Traverse through nCodeList and assign the weight for this code */
						
			DSAGraphVertex vertex = new DSAGraphVertex( label, code, nodeWeight ); 
			vertices.insertLast( vertex ); // Insert vertex object into vertices linked list
			
			vertexCount = vertexCount + 1; // Increase Vertext Count
			
			sortVertices(); // Sort vertices list
		}
		else
		{
			// When the code is not in the list of nCodeList
			
			if(!hasCode)
			{
				throw new MYException("\n\t Error!!! NodeCode " + code 
								+ " is not in the list of Node code ");
			}
			else
			{
				throw new MYException("\n\t Error!!! Vertex " + label 
										+ " is already Exist. ");
			}
		}
	}
	
	
	
	/* Update the Node code of particular vertex */
	
	public void updateVertex( String label, String code ) throws MYException
	{
		int nodeWeight = 0;
		boolean hasCode = false;
		
		DSAGraphVertex vertex = getVertex(label);
		
		for(Object o : nCodeList )
		{
			Ncode n = (Ncode)o;
			
			if( ( n.getNodeCode() ).equals(code) )
			{
				nodeWeight = n.getNodeWeight();
				hasCode = true;
			}
		}
		
		
		/* check the code is there in nCodeList and check Whether 
		new code is equal to current code or not */
		if( hasCode && !(vertex.getCode() ).equals(code) )
		{
			/* Update the code and Weight */
			
			vertex.setCode(code);
			vertex.setValue(nodeWeight);
			
			System.out.println("\n\t Successfully updated the Node code. ");
		}
		else
		{
			if(!hasCode)
			{
				throw new MYException("\n\t Error!!! NodeCode " + code 
										+ " is not in the list of Node code");
			}
			else // When the current code is equal to new code
			{
				throw new MYException("\n\t Error!!! the code " + code 
									+ " is equal to current code don't need to update!! ");
			}
		}
		
	}
	
	
	
	/* Remove the selected vertex for vertices list */
	
	public void removeVertex(String label)
	{
		String edgeLabel;
		
		DSALinkedList list = new DSALinkedList();
		
		
		DSAGraphVertex vertex = getVertex(label); // Get the vertex
		
		
		/* Traverse through vertices linked list and skip the particular vertex */
		
		for(Object o : vertices )
		{
			DSAGraphVertex ve = (DSAGraphVertex)o;
			
			if( !( ve.getLabel() ).equals(label) )
			{
				list.insertLast(ve);
			}
		}
		
		vertices = list; // Replace vertices Linked list
		
		vertexCount = vertexCount - 1; // Decrease Vertext Count
		
		
		/* Remove All Associated Edges of this particular vertex */
		
		for(Object o : edges )
		{
			DSAGraphEdge e = (DSAGraphEdge)o;
			
			if( ( ( e.getFrom() ).getLabel() ).equals(label) )
			{
				edgeLabel = e.getLabel(); // Get the label of the Edge
				removeEdge(edgeLabel); // Remove that edge from graph
			}
			else
			{
				if( ( ( e.getTo() ).getLabel() ).equals(label) )
				{
					edgeLabel = e.getLabel(); // Get the label of the Edge
					removeEdge(edgeLabel); // Remove that edge from graph
				}
			}
		}
		
	}
	
	
	
	/* Add a new edge to edges list */
	
	public void addEdge( String label1, String label2, String code ) throws MYException
	{
		int edgeWeight = 0;
		boolean hasCode = false;
		
		// label1 = A, label = B, so Edge label is AB
		
		String label = label1 + label2; 
		
		
		/* Traverse through eCodeList and assign the weight for this code */
								
		for(Object o : eCodeList )
		{
			Ecode e = (Ecode)o;
			
			if( ( e.getEdgeCode() ).equals(code) )
			{
				edgeWeight = e.getEdgeWeight();
				hasCode = true;
			}
		}
		
		 // Check the edge is already in their or not 
		 
		if( !hasEdge(label) && hasCode )
		{
			DSAGraphVertex vertex1 = getVertex( label1 );
			DSAGraphVertex vertex2 = getVertex( label2 );
			
			// Create an edge Object
			
			DSAGraphEdge edge = new DSAGraphEdge( vertex1, vertex2, code, edgeWeight); 
			edges.insertLast(edge); // Insert edge object into edges linked list
			
			
			edgeCount = edgeCount + 1; // Increase Edge Count
			
			sortEdges(); // Sort edges list
		}
		else
		{
			// When the code is not in the list of eCodeList
			if(!hasCode)
			{
				throw new MYException("\n\t Error!!! EdgeCode " + code 
									+ " is not in the list of Edge code");
			}
			else
			{
				throw new MYException("\n\t Error!!! Edge " + label 
										+ " is already Exist. ");
			}
			
		}
	}
	
	
	
	/* Update the Edge code of particular edge */
	
	public void updateEdge(String label1, String label2, String code) throws MYException
	{
		String label = label1 + label2; // label of the particular Edge
		int edgeWeight = 0;
		boolean hasCode = false;
		
		DSAGraphEdge edge = getEdge(label); // Get the edge
		
		for(Object o : eCodeList )
		{
			Ecode e = (Ecode)o;
			
			if( ( e.getEdgeCode() ).equals(code) )
			{
				edgeWeight = e.getEdgeWeight();
				hasCode = true;
			}
		}
		
		
		/* check the code is there in eCodeList and check Whether 
			new code is equal to current code or not*/
			
		if( hasCode && !(edge.getCode() ).equals(code) )
		{
			/* Update the code and Weight */
			
			edge.setCode(code);
			edge.setValue(edgeWeight);
			
			System.out.println("\n\t Successfully updated the Edge code. ");
		}
		else
		{
			if(!hasCode)
			{
				throw new MYException("\n\t Error!!! EdgeCode " + code 
										+ " is not in the list of Edge code ");
			}
			else // When the current code is equal to new code
			{
				throw new MYException("\n\t Error!!! the code " + code 
									 + " is equal to current code don't need to update!! ");
			}
		}
	}
	
	
	
	/* Remove  particular edge from edge Linked List */
	
	public void removeEdge(String label)
	{
		DSALinkedList list = new DSALinkedList();
		
		// check the particular edge is exist or not
		
		DSAGraphEdge edge = getEdge(label); 
		
		/* Traverse through edge linked list and skip the particular edge */
		
		for(Object o : edges )
		{
			DSAGraphEdge ed = (DSAGraphEdge)o;
			
			if( !( ed.getLabel() ).equals(label) )
			{
				list.insertLast(ed);
			}
		}
		
		edges = list; // Replace edges Linked list
		
		edgeCount = edgeCount - 1; // Decrease Edge Count
	}
	
	
	
	/* Check the given vertex is exist or not in the vertices lsit */
	
	public boolean hasVertex( String label )
	{
		boolean hasVer = false;
		
		for( Object o : vertices )
		{
			DSAGraphVertex v = (DSAGraphVertex)o;
			
			if ( ( v.getLabel() ).equals( label ) )
			{
				hasVer = true;
			}
		}
		
		return hasVer;
	}
	
	
	
	/* Check the given edge is exist or not in the edges list */
	
	public boolean hasEdge( String label )
	{
		boolean hasEdg = false;
		
		for( Object o : edges )
		{
			DSAGraphEdge e = (DSAGraphEdge)o;
			
			if ( ( e.getLabel() ).equals( label ) )
			{
				hasEdg = true;
			}
		}
		
		return hasEdg;
	}
	
	
	
	public int getVertexCount()
	{
		return vertexCount;
	}
	
	
	
	public int getEdgeCount()
	{
		return edgeCount;
	}
	
	
	
	public String getStart()
	{
		return start;
	}
	
	
	
	public String getTarget()
	{
		return target;
	}
	
	
	
	public void setStart( String label )
	{
		start = label;
	}
	
	
	
	public void setTarget( String label )
	{
		target = label;
	}
	
	
	
	public DSALinkedList getNcodeList()
	{
		return nCodeList;
	}
	
	
	
	public DSALinkedList getEcodeList()
	{
		return eCodeList;
	}
	
	
	
	public DSALinkedList getVerticesList()
	{
		return vertices;
	}
	
	
	
	public DSALinkedList getEdgesList()
	{
		return edges;
	}
	
	
	
	
	/* Get the vertex of given label */
	
	public DSAGraphVertex getVertex( String label )
	{
		DSAGraphVertex vertex = null;
		
		for( Object o : vertices )
		{
			DSAGraphVertex v = (DSAGraphVertex)o;
			
			if ( ( v.getLabel() ).equals(label)  )
			{
				vertex = v;
			}
		}
			
		if( vertex == null )
		{
			throw new NoSuchElementException( "\n \t Label " + label + " not found");
		}
		
		return vertex;
	}
	
	
	/* Get the Edge of given label */
	
	public DSAGraphEdge getEdge(String label)
	{
		DSAGraphEdge edge = null;
		
		for( Object o : edges )
		{
			DSAGraphEdge e = (DSAGraphEdge)o;
			
			if ( ( e.getLabel() ).equals(label)  )
			{
				edge = e;
			}
		}
			
		if( edge == null )
		{
			throw new NoSuchElementException( "\n \t Edge " + label + " not found");
		}
		
		return edge;
	}
	
	
	/* Get the Adjacency vertex list of given label */
	
	public DSALinkedList getAdjacent( String label )
	{
		
		// Create a new linked list
		
		DSALinkedList adjList = new DSALinkedList(); 
		
		for( Object o : edges )
		{
			DSAGraphEdge e = (DSAGraphEdge)o;
			DSAGraphVertex vertexFrom = e.getFrom();
			
			if ( ( vertexFrom.getLabel() ).equals(label)  )
			{
				// Insert Adjacent vertex into list;
				
				adjList.insertLast(e.getTo() ); 
			}
		}
		
		return adjList;
	}
	
	
	/* Get the Adjacency edge list of given label */
	
	public DSALinkedList getAdjacentEdge( String label )
	{
		// Create a new linked list
		
		DSALinkedList adjEdgeList = new DSALinkedList();
		
		for( Object o : edges )
		{
			DSAGraphEdge e = (DSAGraphEdge)o;
			DSAGraphVertex vertexFrom = e.getFrom(); 
			
			if ( ( vertexFrom.getLabel() ).equals(label)  )
			{
				// Insert Adjacent edges into the list;
				
				adjEdgeList.insertLast(e); 
			}
		}
		
		return adjEdgeList;
	}
	
	
	
	/* Check the given two vertex are adjacent or not */
	
	public boolean isAdjacent( String label1, String label2 )
	{
		boolean isAdj = false;
		
		DSAGraphVertex vertex1 = getVertex( label1 );
		DSAGraphVertex vertex2 = getVertex( label2 );
		
		DSALinkedList list;
		
		// Get the Adjacent vertex of label 1
		
		list = getAdjacent(label1); 
		
		for( Object o : list )
		{
			DSAGraphVertex v = (DSAGraphVertex)o;
			
			if (  v.equals(vertex2)  )
			{
				isAdj = true;
			}
		}
		
		return isAdj;
	}
	
	
	
	/* Display as Adjacent List */
	
	public void displayAsList()
	{
		DSALinkedList list;
		
		for( Object o : vertices ) 
		{
			DSAGraphVertex v1 = (DSAGraphVertex)o;
			System.out.printf( "Label :  %-10s   Node Code :  %-15s   Node Weight:  %-5d   AdjacentList : ",
						v1.getLabel(), v1.getCode(), v1.getValue() );
			
			// Get the adjacency vertex of v1
			
			list = getAdjacent( v1.getLabel() ); 
			
			for( Object c : list )
			{
				DSAGraphVertex v2 = (DSAGraphVertex)c;
				
				System.out.print(v2.getLabel() + " ");
			}
			
			System.out.println();
			
		}
		
	}
	
	
	/* Write as a Adjacent list into file*/
	
	public void WriteAsList(PrintWriter pw)
	{
		DSALinkedList list;
		
		for( Object o : vertices ) 
		{
			DSAGraphVertex v1 = (DSAGraphVertex)o;
			pw.printf( "Label :  %-10s   Node Code :  %-15s   Node Weight:  %-5d   AdjacentList : ",
						v1.getLabel(), v1.getCode(), v1.getValue() );
			
			// Get the adjacency vertex of v1
			
			list = getAdjacent( v1.getLabel() );
			
			for( Object c : list )
			{
				DSAGraphVertex v2 = (DSAGraphVertex)c;
				
				pw.print(v2.getLabel() + " ");
			}
			
			pw.println();
			
		}
		
	}
	
	
	
	/* Return Adjacency Metrix */
	
	public int[][] getGraphAsMatrix()
	{
		int size, i, j;
		int[][] adjMatrix;
		String[] array;
		String label;
		String space = "";
		
		
		// Get the Node count
		
		size = vertices.getCount(); 
		adjMatrix = new int[size+1][size+1];
		
		// Get lookup table
		
		array = getLabelLookup(); 
		
		
		/* Fill the 2d Array with Default value 0 */
		
		for( i = 0; i < size+1; i++)
		{
			for( j = 0; j < size+1; j++)
			{
				if( i != 0 && j == 0 )
				{
					adjMatrix[i][j] = i; 
				}
				else
				{
					if( i == 0 && j != 0 )
					{
						adjMatrix[i][j] = j; 
					}
					else
					{
						adjMatrix[i][j] = 0;
					}
				}
			}
		}
		
		
		/* When edge is available replace the edge weight with 0 */
		
		for( i = 1; i < adjMatrix.length; i++)
		{
			for( j = 1; j < adjMatrix.length; j++)
			{
				label = array[i] + array[j];
				
				if( hasEdge(label) )
				{
					DSAGraphEdge edge = getEdge(label);
					adjMatrix[i][j] = edge.getValue();
				}
			}
		}
		
		
		return adjMatrix;
	
	}
	
	
	
	/* Generate look up tabel for Adjacency Matrix */
	
	public String[] getLabelLookup()
	{
		int i, size;
		int count = 1;
		
		size = vertices.getCount(); // Get the Node count
		
		String[] array = new String[size+1];
		
		for( Object o : vertices ) 
		{
			DSAGraphVertex v1 = (DSAGraphVertex)o;
			
			array[count] = v1.getLabel();
			count++;
		}
		
		
		
		return array;
		
	}
	
	
	
	/* Display the Details of particular Vertex */
	
	public void displayNode(String label)
	{
		DSAGraphVertex vertex;
		
		vertex = getVertex(label);
		
		System.out.println("\n\t" + vertex.toString() );
	}
	
	
	
	/* Display the Details of particular Edge */
	
	public void displayEdge(String label1, String label2)
	{
		// The Original Label of the Edge
		
		String label = label1 + label2;
		
		DSAGraphEdge edge;
		
		edge = getEdge(label);
		
		System.out.println("\n\t" + edge.toString() );
	}
	
	
	
	/* Display the Legend of Node Code */
	
	public void displayNodeCode()
	{
		String str1, str2, str3;
		
		str1 = "#NodeCode";
		str2 = "#NodeType";
		str3 = "#NodeWeight";
		
		System.out.printf( " %15s     %-15s  %10s\n\n",str1 , str2, str3 );
		for(Object o : nCodeList )
		{
			Ncode n = (Ncode)o;
			
			n.display();
		}
		
	}
	
	
	/* Write the Legend of Node Code into a File */
	
	public void WriteNodeCode(PrintWriter pw)
	{
		String str1, str2, str3;
		
		str1 = "#NodeCode";
		str2 = "#NodeType";
		str3 = "#NodeWeight";
		
		pw.printf( " %15s     %-15s  %10s\n\n",str1 , str2, str3 );
		for(Object o : nCodeList )
		{
			Ncode n = (Ncode)o;
			
			n.display(pw);
		}
		
	}
	
	
	/* Display the Legend of Edge Code */
	
	public void displayEdgeCode()
	{
		String str1, str2, str3;
		
		str1 = "#EdgeCode";
		str2 = "#EdgeType";
		str3 = "#EdgeWeight";
		
		System.out.printf( " %15s     %-15s  %10s\n\n",str1 , str2, str3 );
		for(Object o : eCodeList )
		{
			Ecode e = (Ecode)o;
			e.display();
		}
	}
	
	
	/* Write the Legend of Edge Code into a File */
	
	public void WriteEdgeCode(PrintWriter pw)
	{
		String str1, str2, str3;
		
		str1 = "#EdgeCode";
		str2 = "#EdgeType";
		str3 = "#EdgeWeight";
		
		pw.printf( " %15s     %-15s  %10s\n\n",str1 , str2, str3 );
		for(Object o : eCodeList )
		{
			Ecode e = (Ecode)o;
			e.display(pw);
		}
	}
	
	
	/* Adjust mapping of Node codes */
	
	public void updateNodeCodeWeight(String code, int weight) throws MYException
	{
		boolean hasCode = false;
		Ncode nCode = null;
		
		
		for(Object o : nCodeList )
		{
			Ncode n = (Ncode)o;
			
			if( ( n.getNodeCode() ).equals(code) )
			{
				hasCode = true;
				nCode = n;
			}
		}
		
		
		if( hasCode )
		{
			/* Update weight of the code */
			
			nCode.setNodeWeight(weight);

			for(Object o : vertices)
			{
				DSAGraphVertex v = (DSAGraphVertex)o;
				
				/* Update weight of all associated Nodes */
				
				if( ( v.getCode() ).equals(code) )
				{
					v.setValue(weight);
				}
			}
		}
		else
		{
			throw new MYException("\n\t Error!!! NodeCode " + code 
									+ " is not in the list of Node code ");
		}
	}
	
	
	
	/* Adjust mapping of Edge codes */
	
	public void updateEdgeCodeWeight(String code, int weight) throws MYException
	{
		boolean hasCode = false;
		Ecode eCode = null;
		
	
		for(Object o : eCodeList )
		{
			Ecode e = (Ecode)o;
			
			if( ( e.getEdgeCode() ).equals(code) )
			{
				hasCode = true;
				eCode = e;
			}
		}
		
		
		if( hasCode )
		{
			/* Update weight of the code */
			
			eCode.setEdgeWeight(weight);

			for(Object o : edges)
			{
				DSAGraphEdge e = (DSAGraphEdge)o;
				
				/* Update weight of all associated Edges */
				
				if( ( e.getCode() ).equals(code) )
				{
					e.setValue(weight);
				}
			}
		}
		else
		{
			throw new MYException("\n\t Error!!! EdgeCode " + code 
								+ " is not in the list of Edge code ");
		}
	}
	
	
	
	/* Write Nodes and labels into the file when Saving the network */
	
	public void defineNodes(PrintWriter pw)
	{
		for(Object o : vertices )
		{
			DSAGraphVertex v = (DSAGraphVertex)o;
			pw.println("Node " + v.getLabel() + " " + v.getCode() );
		}
	}
	
	
	
	/* Write Edges and labels into the file when Saving the network */
	
	public void defineEdges(PrintWriter pw)
	{
		DSAGraphVertex from;
		DSAGraphVertex to;
		
		for(Object o : edges )
		{
			DSAGraphEdge e = (DSAGraphEdge)o;
			
			// Get the from and to Vertex
			
			from = e.getFrom(); 
			to = e.getTo();
			
			pw.println("Edge " + from.getLabel() + " " 
						+ to.getLabel() + " " + e.getCode() );
		}
	}
	
	
	
	public void depthFirstSearch() throws MYException
	{
		DSAGraphVertex vertex = null;
		DSAGraphVertex w = null;
		DSALinkedList list;
		int count = 0;
		DSAStack stack = new DSAStack();
		DSAQueue queue = new DSAQueue();
		boolean isPop = false;
		
		
		for( Object o : vertices ) 
		{
			DSAGraphVertex v1 = (DSAGraphVertex)o;
			v1.clearVisited(); // Mark all vertices new;
			
			if( count == 0 )
			{
				vertex = v1; // Get the starting vertex
			}
			
			count++;
		}
		
		stack.push( vertex );
		
		// mark vertex to old ( Alpha Order )
		
		vertex.setVisited(); 
		
		
		while( !stack.isEmpty() )
		{
			boolean isNewVertx = false;
			count = 0;
			
			if(isPop)
			{
				// backtracking
				
				vertex = (DSAGraphVertex)stack.pop(); 
			}
			
			// Get the adjacency vertex list
			
			list = getAdjacent( vertex.getLabel() ); 
			
			for( Object o : list ) 
			{
				DSAGraphVertex v1 = (DSAGraphVertex)o;
				if( !v1.getVisited() && count == 0 )
				{
					isNewVertx = true;
					w = v1;
					
					count++;
				}
				
				
			}
			
			if( isNewVertx )
			{
				String T = "(" + vertex.getLabel() +"," + w.getLabel() +")" ;
				queue.enqueue(T);
				w.setVisited();
				stack.push(w);
				vertex = w;
				
				isPop = false; // Don't pop the stack
			}
			else
			{
				isPop = true; // pop the stack
			}
			
		}
		
		
		while( !queue.isEmpty() )
		{
			System.out.print(" " + queue.dequeue() + " ");
		}
		
		
	}
	
	
	
	public void breadthFirstSearch() throws MYException 
	{
		DSAGraphVertex vertex = null;
		int count = 0;
		String T = " ";
		DSAQueue queue = new DSAQueue(); // Create queue
		
		for( Object o : vertices ) 
		{
			DSAGraphVertex v1 = (DSAGraphVertex)o;
			v1.clearVisited(); // Mark all vertices new;
			
			if( count == 0 )
			{
				vertex = v1;
			}
			
			count++;
		}
		
		// mark vertex to old ( Starting with Vertex A)
		
		vertex.setVisited();
		queue.enqueue(vertex);
		
		while( !queue.isEmpty() )
		{
			DSAGraphVertex w = null;
			
			vertex = (DSAGraphVertex)queue.dequeue();
			
			// Get the adjacency vertex list
			
			DSALinkedList list = getAdjacent( vertex.getLabel() ); 
			
			for( Object o : list ) 
			{
				DSAGraphVertex v1 = (DSAGraphVertex)o;
			
				if( !v1.getVisited() )
				{
					w = v1;
					T = T + "(" + vertex.getLabel() +"," + w.getLabel() +")" + "  " ;
					w.setVisited();
					queue.enqueue( w );
					
				}
				
			}
			
		}
		
		if( queue.isEmpty() )
		{
			System.out.println(T);
		}
		
	}
	
	
	
	public Routes[] generateAllPaths()
	{
		DSALinkedList list2 = new DSALinkedList();
		
		for( Object o : vertices ) 
		{
			DSAGraphVertex v1 = (DSAGraphVertex)o;
			v1.clearVisited(); // Mark all vertices new;
			
			if( ( v1.getLabel() ).equals(start))
			{
				v1.setVisited(); // Mark begin node as visited
			}
			
		}
		
		generateAllPathsRec(start, target, "", list2);
		
		int size = list2.getCount();
		int i = 0;
		
		// Create array of route object in order to sort the object.
		
		Routes[] routeArray = new Routes[size]; 
		
		/* Traverse through the list and fill the array */
		
		for( Object o : list2 ) 
		{
			Routes r = (Routes)o;
			routeArray[i] = r;
			
			i++;
		}
		
		// Sort the array of Object by total cost
		
		insertionSort(routeArray); 
		
		return routeArray;
		
	}
	
	
	
	private void generateAllPathsRec( String begin, String end, 
									String path, DSALinkedList list2)
	{
		DSAGraphVertex v1 = null;
		
		String newPath = path + begin + " " ;
		
		v1 = getVertex(begin); 
		v1.setVisited(); // Mark start vertex as visited
		
		DSALinkedList list = getAdjacentEdge( begin );
		
		for( Object o : list ) 
		{
			DSAGraphEdge e = (DSAGraphEdge)o;
			
			DSAGraphVertex from = e.getFrom();
			DSAGraphVertex to = e.getTo();
			
			if( !( ( to.getLabel() ).equals(end) ) && !( to.getVisited() ) )
			{
				generateAllPathsRec( to.getLabel(), end, newPath, list2);
			}
			else
			{
				if( ( to.getLabel() ).equals(end) )
				{
					String str = new String();
					str = newPath + to.getLabel();
					if( !str.equals(" ") )
					{
						/* call the method to store the paths into list */
						storePaths(str, list2);
						
					}
					
				}
			}
		}
		
		
		v1 = getVertex(begin);
		v1.clearVisited();
		
		
	}
	
	
	
	/* Insert route object into linked list */
	
	private void storePaths(String str, DSALinkedList list2)
	{
		int totalNodeValue = 0;
		int totalEdgeValue = 0;
		int totalCost = 0;
		DSAGraphVertex vertex;
		DSAGraphEdge edge;
		String label, label1, label2;
		String path = "";
		
		// Split the path inorder to get the label of nodes
		
		String[] strArray = str.split(" ");
		
		
		for(int i = 1; i < strArray.length; i++ )
		{
			label1 = strArray[i-1];
			label2 = strArray[i];
			label = label1 + label2;
			
			vertex = getVertex(label1); // Get vertex
			edge = getEdge(label); // Get edge
			
			totalNodeValue = totalNodeValue + vertex.getValue();
			totalEdgeValue = totalEdgeValue + edge.getValue();
			
			// Add the value of last Node into totalNodeValue
			
			if( i == (strArray.length -1 ) )
			{
				vertex = getVertex(label2); // last node
				totalNodeValue = totalNodeValue + vertex.getValue();
			}
		}
		
		for(int i = 0; i < strArray.length; i++ )
		{
			path = path + strArray[i]; 
			
			if( i != (strArray.length -1 ) )
			{
				path = path + " -> ";
			}
		}
		
		totalCost = totalNodeValue + totalEdgeValue;
		
		// Create a route object
		
		Routes r = new Routes(path, totalNodeValue, totalEdgeValue);
		list2.insertLast(r);
		
	}
	
	
	
	/* Assertion : Sort the vertices list in Alpha Order */
	
	private void sortVertices()
	{
		int arrayLength;
		int i = 0;
		
		DSALinkedList list2 = new DSALinkedList();
		arrayLength = vertices.getCount();
		
		DSAGraphVertex[] vertexArr = new DSAGraphVertex[arrayLength];
		
		for( Object o : vertices )
		{
			DSAGraphVertex v1 = (DSAGraphVertex)o;
			vertexArr[i] = v1;
			
			i++;
		}
		
		insertionSort(vertexArr); // Sort vertices Array ;
		
		// Regenerate linked list from sorted array
		
		for( i = 0; i < arrayLength; i++ )
		{
			list2.insertLast( vertexArr[i] );
		}
		
		// Replace vertices list with regenerated linked list
		
		vertices = list2;
	}
	
	
	private void sortEdges()
	{
		int arrayLength;
		int i = 0;
		
		DSALinkedList list2 = new DSALinkedList();
		arrayLength = edges.getCount();
		
		DSAGraphEdge[] edgeArr = new DSAGraphEdge[arrayLength];
		
		for( Object o : edges )
		{
			DSAGraphEdge e1 = (DSAGraphEdge)o;
			edgeArr[i] = e1;
			
			i++;
		}
		
		insertionSort(edgeArr); // Sort edge Array ;
		
		// Regenerate linked list from sorted array
		
		for( i = 0; i < arrayLength; i++ )
		{
			list2.insertLast( edgeArr[i] );
		}
		
		// Replace edges list with regenerated linked list
		
		edges = list2;
	}
	
	
	
	/* Assertion: Sorting an array of vertex */
	
	private void insertionSort(DSAGraphVertex[] vertex)
	{
		for(int n = 1; n < vertex.length; n++)
		{
			int i = n;
			DSAGraphVertex temp = vertex[i];
			
			while( i > 0  &&  ( vertex[i-1].getLabel() ).compareTo ( temp.getLabel() ) > 0 )
			{
				vertex[i] = vertex[i-1];
				i = i - 1;
			}
			
			vertex[i] = temp;
		}
		
	} 
	
	
	
	/* Assertion: Sorting an array of edge */
	
	private void insertionSort(DSAGraphEdge[] edge)
	{
		for(int n = 1; n < edge.length; n++)
		{
			int i = n;
			DSAGraphEdge temp = edge[i];
			
			while( i > 0  &&  ( edge[i-1].getLabel() ).compareTo ( temp.getLabel() ) > 0 )
			{
				edge[i] = edge[i-1];
				i = i - 1;
			}
			
			edge[i] = temp;
		}
		
	} 
	
	
	
	private void insertionSort(Routes[] route)
	{
		for(int n = 1; n < route.length; n++)
		{
			int i = n;
			Routes temp = route[i];
			
			while( i > 0  &&  ( route[i-1].getTotalCost() > temp.getTotalCost() ) )
			{
				route[i] = route[i-1];
				i = i - 1;
			}
			
			route[i] = temp;
		}
		
	} 
	
	
	
	
	
	/* DSAGraphVertex Inner class */
	
	private class DSAGraphVertex
	{
		private String label;
		private int value;
		private String code;
		private boolean visited;
		
		
		public DSAGraphVertex( String pLabel, String code, int pValue )
		{
			this.label = pLabel;
			this.value = pValue;
			this.code = code;
			this.visited = false;
		}
		
		
		public String getLabel()
		{
			return label;
		}
		
		
		public int getValue()
		{
			return value;
		}
		
		
		public String getCode()
		{
			return code;
		}
		
		
		public void setCode(String code)
		{
			this.code = code;
		}
		
		
		public void setValue(int pValue)
		{
			this.value = pValue;
		} 
		
		
		public void setVisited()
		{
			visited = true;
		}
		
		
		public void clearVisited()
		{
			visited = false;
		}
		
		
		public boolean getVisited()
		{
			return visited;
		}
		
		
		public String toString()
		{
			String str = " Vertex Label =  " + label + "   code =  " 
							+ code + "   VertexWeight =  " + value;
			
			return str;
		}
		
		
	} /*  End of InnerClass DSAGraphVertex */





	/* DSAGraphEdge Inner class */
	
	private class DSAGraphEdge
	{
		private DSAGraphVertex from;
		private DSAGraphVertex to;
		private String label;
		private String code;
		private int value;
		
		
		public DSAGraphEdge( DSAGraphVertex fromVertex, DSAGraphVertex toVertex,
							String code, int value )
		{
			this.from = fromVertex;
			this.to = toVertex;
			
			// A, B vertex Edge Label = AB
			
			this.label = fromVertex.getLabel() + toVertex.getLabel();
			this.code = code;
			this.value = value;
		}
		
		
		public DSAGraphVertex getFrom()
		{
			return from;
		}
		
		
		public DSAGraphVertex getTo()
		{
			return to;
		}
		
		
		public String getLabel()
		{
			return label;
		}
		
		
		public String getCode()
		{
			return code;
		}
		
		
		public void setCode(String code)
		{
			this.code = code;
		}
		
		
		public int getValue()
		{
			return value;
		}
		
		
		public void setValue(int pValue)
		{
			this.value = pValue;
		} 
		
		
		public String toString()
		{
			String str = " Edge Label =  " + label + "   code =  " 
						+ code + "   EdgeWeight =  " + value;
			
			return str;
		}
		
		
	} /*  End of InnerClass DSAGraphEdge */
	
	
	
	
}/* End of DSAGraph outer class */