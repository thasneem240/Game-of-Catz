/************************************************************************
 * File: UnitTestDSAQueue.java							   				*
 * Author: Mr.A.S.M. Thasneem                          				    *
 * Date Created: 22/08/2021                            				    *
 * Date Modified: 23/08/2021                           				    *               
 * Purpose: Test DSAQueue	 											*
 ************************************************************************/
 
 /* This code comes from work done in DSA practical 4 */
 
import java.util.*;
public class UnitTestDSAQueue
{
	public static void main(String[] args)
	{
		test1();
	}
	
	
	
	
/***************************************************************************************
 * SubModule: test1                    	 				   	       		   	   		   *
 * Import: none	   			 						  		   						   *  
 * Export: none							   	   		   	   							   *
 * Assertion: Test the enqueue, peek and count methods of DSAQueue 		   			   *
 ***************************************************************************************/
 
	public static void test1()
	{
		
		int frontValue1;
		
		
		Random r = new Random(); // Create Random Object to generate random numbers
		
			try
			{
				DSAQueue queue1 = new DSAQueue();
				
				int[] x = new int[15]; // Create an array of integer value
				
				
				for(int i = 0; i < x.length ; i++)
				{
					x[i] = r.nextInt( 101 ); // Generate Random numbers (bound 0 - 100) to enqueue into the queue1
				}
				
				
				System.out.println("\n \tTEST 01: Check the enqueue methods for DSAQueue \n");
				System.out.println("========================================================================\n");
				
				
				for(int i = 0; i < x.length; i++)
				{
					queue1.enqueue(x[i]);
					frontValue1 = (int)queue1.peek(); // convert object data type to integer value
					System.out.println(" enqueue( " + x[i] + " )	" +  "	      peek = " + frontValue1 );	
				}
				
				System.out.println("\n\n\n \tTEST 02: Check the dequeue methods for DSAQueue \n");
				System.out.println("========================================================================\n");
				
				test2(queue1, 15); // Calling test2 
				
			}
			catch(MYException e)
			{
				System.out.println("\n\t Error!! " + e.getMessage() );
			}
	}	
/***************************************************************************************
 * SubModule: test2                    	 				   	       		   	   		   *
 * Import: pQueue(DSAQueue), pQueueSize(Integer)   			 						   *  
 * Export: none							   	   		   	   							   *
 * Assertion: Test the dequeue, peek and count methods 		   						   *
 ***************************************************************************************/
 
	public static void test2(DSAQueue pQueue, int pQueueSize)
	{
		int frontValue, count1, count2;
		int dequeueValue;
		
		try
		{
			System.out.println("\n\n");
			
			for(int i = 0; i < pQueueSize; i++)
			{
				frontValue = (int)pQueue.peek(); // convert object data type to integer value
				dequeueValue = (int)pQueue.dequeue();
				System.out.println(" peek =  " + frontValue +  "	      dequeue( ) =  " + dequeueValue );
			}
			
			
			System.out.println( "\n dequeue(  )" );
			dequeueValue = (int)pQueue.dequeue(); // wil get Queue Underflow Error*/
			
		}
		catch(MYException e)
		{
			System.out.println("\n\t Error!! " + e.getMessage() );
		}
	}
}