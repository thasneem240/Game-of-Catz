/****************************************************************************
 * File: DSALinkedList.java													*
 * Author: Mr.A.S.M. Thasneem                          				    	*
 * Date Created: 30/08/2021                            				    	*
 * Date Modified: 31/08/2021                           				    	*               
 * Purpose: Implement a DSALinkedList										*
 ****************************************************************************/

import java.util.*;
import java.io.*; 
 
/* This code comes from work done in DSA practical 4 */
 
public class DSALinkedList implements Iterable
{
	
	// Private inner class
	private class DSAListNode 
	{
		private Object value;
		private DSAListNode next;
		private DSAListNode prev;
		
		
		public DSAListNode( Object pValue )
		{
			this.value = pValue;
			this.next = null;
			this.prev = null;
		}
		
		
		public Object getValue()
		{
			return this.value;
		}
		
		
		public void setValue( Object pValue )
		{
			this.value = pValue;
		}
		
		
		public DSAListNode getNext()
		{
			return this.next;
		}
		
		
		public void setNext( DSAListNode pNext )
		{
			this.next = pNext;
		}
		
		
		public DSAListNode getPrev()
		{
			return this.prev;
		}
		
		
		public void setPrev( DSAListNode pPrev )
		{
			this.prev = pPrev;
		}
		
		
	} // End of private inner class DSAListNode 
	
	

	// Attribute of the outer class
	
	private DSAListNode head;
	private DSAListNode tail;
	private int count;
	
	
	
	// Default Constructor
	
	public DSALinkedList()
	{
		this.head = null;
		this.tail = null;
		this.count = 0;
	}
	
	
	
	public int getCount()
	{
		return count;
	}
	
	
	
	public void insertFirst( Object newValue )
	{
		// create a DSAListNode Object
		
		DSAListNode newNode = new DSAListNode( newValue ); 
		
		if( isEmpty() )
		{
			head = newNode;
			tail = newNode;
		}
		else
		{
			newNode.setNext( head );
			head.setPrev( newNode );
			head = newNode;
			
		}
		
		count = count + 1;
		
	}
	
	
	
	public void insertLast( Object newValue )
	{
		DSAListNode newNode = new DSAListNode( newValue );
		
		if( isEmpty() )
		{
			head = newNode;
			tail = newNode;
		}
		else
		{
			tail.setNext(newNode); 
			newNode.setPrev(tail);
			tail = newNode;
		}
		
		count = count + 1;
	}
	
	
	
	public boolean isEmpty()
	{
		boolean empty = false;
		
		if ( head == null && tail == null )
		{
			empty = true;
		}
		
		return empty;
	}
	
	
	
	public Object peekFirst() throws MYException
	{
		Object nodeValue;
		
		if( isEmpty() )
		{
			throw new MYException(" Linked List is Empty!! ");
		}
		else
		{
			nodeValue = head.getValue();
		}
		
		return nodeValue;
	}
	
	
	
	public Object peekLast() throws MYException
	{
		Object nodeValue;
		
		if( isEmpty() )
		{
			throw new MYException(" Linked List is Empty!! ");
		}
		else
		{
			nodeValue = tail.getValue(); 
		}
		
		return nodeValue;
	}
	
	
	
	public Object removeFirst() throws MYException
	{
		Object nodeValue;
		
		if( isEmpty() )
		{
			throw new MYException(" Linked List is Empty!! ");
		}
		else
		{
			nodeValue = head.getValue();
			head = head.getNext();
			
			
			// special case ( one item list since it is both the first and last node )
			
			if(head == null)
			{
				tail = null; 
			}
			else
			{
				head.setPrev(null);
			}
			
			count = count - 1;
			
		}
		
		return nodeValue;
	}
	
	
	
	public Object removeLast() throws MYException
	{
		Object nodeValue;
		
		if( isEmpty() )
		{
			throw new MYException(" Linked List is Empty!! ");
		}
		else
		{
			DSAListNode prevNode;
			
			nodeValue = tail.getValue();
			prevNode = tail.getPrev();
			if( prevNode != null )
			{
				prevNode.setNext(null);
			}
			else
			{
				// set head as null when previus node is null
				
				head = prevNode;
			}
			
			tail = prevNode;
			
			count = count - 1;
			
		}
		
		return nodeValue;
	}
		
	
	
	public Iterator iterator() 
	{
		return new  DSALinkedListIterator(this);
	}
	
	
	
	/* DSALinkedListIterator private inner class */
	
	private class DSALinkedListIterator implements Iterator
	{
		private DSAListNode iterNext;
		
		
		public DSALinkedListIterator( DSALinkedList theList )
		{
			iterNext = theList.head;
			
		}
		
		
		public boolean hasNext()
		{
			return ( iterNext != null );
		}
		
		
		public Object next()
		{
			Object value;
			
			if( iterNext == null )
			{
				value = null;
			}
			else
			{
				// Get the value in the node
				
				value = iterNext.getValue();
				
				//  Ready for subsequent calls to next()
				
				iterNext = iterNext.getNext();
			}
			
			return value;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException("Not supported");
		}
		
		
	} // End of private inner class DSALinkedListIterator
	
	
	
}