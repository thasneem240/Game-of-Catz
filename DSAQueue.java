/****************************************************************************
 * File: DSAQueue.java														*
 * Author: Mr.A.S.M. Thasneem                          				    	*
 * Date Created: 22/08/2021                            				    	*
 * Date Modified: 23/08/2021                           				    	*               
 * Purpose: Implement a Queue array of Object								*
 ****************************************************************************/
 
 import java.util.*; // for Iterator
 
 /* This code comes from work done in DSA practical 4 */
 
 public class DSAQueue implements Iterable
 {
	private DSALinkedList list; // List for queue
	
	
/****************************************************************************
 * Name: Default Constructor                              	 				*
 * Import: none		   			 								            *  
 * Export: none        	   													*
 * Assertion: Create an object with the default values						*					 							   					   		*
 ****************************************************************************/
 
	public DSAQueue()
	{
		this.list = new DSALinkedList();
	}
	
	
/****************************************************************************
 * Name: Accessor isEmpty                              	 					*
 * Import: nothing			   			 								    *  
 * Export: isEmp (Boolean)        	   										*
 ****************************************************************************/
 
	public boolean isEmpty()
	{
		return list.isEmpty();
	}
	
	
	public void enqueue(Object pValue ) 
	{
		list.insertLast(pValue);
	}
	
	
	public Object dequeue() throws MYException 
	{
		Object frontVal = list.peekFirst();
		list.removeFirst();
		
		return frontVal; 
	}
	
	
	public Object peek() throws MYException
	{
		Object frontVal;
		
		if( isEmpty() )
		{
			throw new MYException(" Queue UnderFlow ");
		}
		else
		{
			frontVal = list.peekFirst();
		}
		
		return frontVal;
	}
	
	public Iterator iterator()
	{
		return list.iterator(); // Expose list's iterator
	}
	
 }