/************************************************************************
 * File: UnitTestDSAStack.java											*
 * Author: Mr.A.S.M. Thasneem                          				    *
 * Date Created: 22/08/2021                            				    *
 * Date Modified: 23/08/2021                           				    *               
 * Purpose: Test DSAStack Class 		 								*
 ************************************************************************/
 
 /* This code comes from work done in DSA practical 4 */
 
import java.util.*;
public class UnitTestDSAStack
{
	public static void main ( String[] args )
	{
		test1(); // Calling test1
	}
	
	
	
/***************************************************************************************
 * SubModule: test1                    	 				   	       		   	   		   *
 * Import: none	   			 						  		   						   *  
 * Export: none							   	   		   	   							   *
 * Assertion: Test the push, top and count methods of DSAStack 		   				   *
 ***************************************************************************************/
 
	public static void test1()
	{
		
		int topValue1;
		
		Random r = new Random(); // Create Random Object to generate random numbers
		
		try
		{
			int[] x = new int[10]; // Create an array of integer value
			DSAStack stack1 = new DSAStack(); // creating stack 1 Object
		
			
			for(int i = 0; i < 10; i++)
			{
				x[i] = r.nextInt( 101 ); // Generate Random numbers (bound 0 - 100) to push into the stack2
			}
			
			
			System.out.println("\n TEST 01: Check the push for DSAStack\n");
			System.out.println("========================================================\n");
			
			for(int i = 0; i< x.length; i++)
			{
				stack1.push(x[i]);
				topValue1 = (int)stack1.top(); // convert object data type to real value
				System.out.println(" push( " + x[i] + " )	"  + "	top = " + topValue1);	
			}
			
			test2(stack1, 10); // Calling test2 
			
		}
		
		catch(MYException e)
		{
			System.out.println("\n\t Error!! " + e.getMessage() );
		}
		
	}
	
	
	
/***************************************************************************************
 * SubModule: test2                    	 				   	       		   	   		   *
 * Import: pStack(DSAStack), pStackSize(Integer)   			 						   *  
 * Export: none							   	   		   	   							   *
 * Assertion: Test the pop, top and count methods 		   						   	   *
 ***************************************************************************************/
 
	public static void test2(DSAStack pStack, int pStackSize)
	{
		int topValue;
		int popValue;
		
		try
		{
			System.out.print("\n TEST 02: Check the pop for DSAStack \n\n");
			System.out.println("========================================================\n");
			
			for(int i = 0; i< pStackSize; i++)
			{
				topValue = (int)pStack.top(); // convert object data type to integer value
				popValue = (int)pStack.pop();
				System.out.println(" top =  " + topValue +"	pop(  )	=  " + popValue);
			}
			
			
			System.out.println( " pop(  )" );
			popValue = (int)pStack.pop(); // wil get Stack Underflow Error
			
		}
		catch(MYException e)
		{
			System.out.println("\n\t Error!! " + e.getMessage() );
		}
	}
	
}