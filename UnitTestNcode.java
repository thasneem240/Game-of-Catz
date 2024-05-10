/**********************************************************************************
 * File: UnitTestNcode.java													  	  *
 * Author: Mr.A.S.M. Thasneem                          				              *
 * Date Created: 10/10/2021                            				              *
 * Date Modified: 16/10/2021                           				              * 
 * Purpose: Test Harness for Ncode class					  					  *
 **********************************************************************************/
 
 import java.io.*;
public class UnitTestNcode
{
	public static void main(String args[])
	{
        // VARIABLE DECLARATIONS
        int iNumPassed = 0;
        int iNumTests = 0;
        Ncode n = null;
        String sTestString;
        int iTestWeight;
		
		
		n = new Ncode( "D", 100);
		
	//---------------------------------------------------------------------------

        System.out.println("\n\nTesting Getters ");
        System.out.println("=======================================");

        // TEST 1 : 
		
		try 
		{
            iNumTests++;
            System.out.print("Testing getNodeCode() ");
            sTestString = n.getNodeCode();
            if (sTestString != "D")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed   ");
        } 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}
		
		
		
        // TEST 2 : 
		
		try 
		{
            iNumTests++;
            System.out.print("Testing getNodeWeight() ");
            iTestWeight = n.getNodeWeight();
            if (iTestWeight != 100)
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed   ");
        } 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}
		
		
		
		//---------------------------------------------------------------------------

        System.out.println("\n\nTesting Setters ");
        System.out.println("=======================================");

        // TEST 3 : 
		
		try 
		{
            iNumTests++;
            System.out.print("Testing setNodeCode() ");
			n.setNodeCode("A");
			
            sTestString = n.getNodeCode();
            if (sTestString != "A")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed   ");
        } 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}
		
		
		
        // TEST 4 : 
		
		try 
		{
            iNumTests++;
            System.out.print("Testing setNodeWeight() ");
			n.setNodeWeight(50);
			
            iTestWeight = n.getNodeWeight();
            if (iTestWeight != 50)
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed   ");
        } 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}
		
		//---------------------------------------------------------------------------

        // PRINT TEST SUMMARY
        System.out.print("\nNumber PASSED: " + iNumPassed + "/" + iNumTests);
        System.out.print(" -> " + (int)(double)iNumPassed/iNumTests*100 + "%\n");
		
	}
}