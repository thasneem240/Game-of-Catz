/****************************************************************************
 * File: DSAStack.java														*
 * Author: Mr.A.S.M. Thasneem                          				    	*
 * Date Created: 30/08/2021                            				    	*
 * Date Modified: 31/08/2021                           				    	*               
 * Purpose: Implement a Stack with linked list								*
 ****************************************************************************/
 
 import java.util.*; // for Iterator
 
 /* This code comes from work done in DSA practical 4 */
 
public class DSAStack implements Iterable
{
	private DSALinkedList list; // List for stack
	
	
/****************************************************************************
 * Name: Default Constructor                              	 				*
 * Import: none		   			 								            *  
 * Export: none        	   													*
 * Assertion: Create an object with the default values						*					 							   					   		*
 ****************************************************************************/
 
	public DSAStack()
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
	
	
 
/****************************************************************************
 * Name: Mutator push                             	 				   		*
 * Import: pValue (Object)			   			 							*  
 * Export: none																*
 * Assertion: add a new item to the top of the stack					    *
 ****************************************************************************/
 
	public void push( Object pValue ) 
	{
		list.insertFirst(pValue);
	}
	
	
/****************************************************************************
 * Name: Mutator top                             	 				   		*
 * Import: none			   			 										*  
 * Export: topVal															*
 * Assertion: look at the top-most item, but leave it on the stack			*
 ****************************************************************************/
 
	public Object top() throws MYException 
	{
		Object topVal;
		
		if( isEmpty() )
		{
			throw new MYException(" Stack UnderFlow ");
		}
		else
		{
			topVal = list.peekFirst();
		}
		
		return topVal;
	}
	
	
/****************************************************************************
 * Name: Mutator pop                             	 				   		*
 * Import: none			   			 										*  
 * Export: topVal															*
 * Assertion: take the top-most item from the stack					   		*
 ****************************************************************************/
 
	public Object pop() throws MYException 
	{
		
		Object topVal = list.peekFirst();
		list.removeFirst();
		
		return topVal; 
	}
	
	
	public Iterator iterator()
	{
		return list.iterator(); // Expose list's iterator
	}
	
	
}