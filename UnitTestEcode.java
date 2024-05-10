/**********************************************************************************
 * File: UnitTestEcode.java													  	  *
 * Author: Mr.A.S.M. Thasneem                          				              *
 * Date Created: 10/10/2021                            				              *
 * Date Modified: 16/10/2021                           				              * 
 * Purpose: Test Harness for Ecode class					  					  *
 **********************************************************************************/
 
 import java.io.*;
 
public class UnitTestEcode
{
	public static void main(String args[])
	{
        // VARIABLE DECLARATIONS
        int iNumPassed = 0;
        int iNumTests = 0;
        Ecode e = null;
        String sTestString;
        int iTestWeight;
		
		
		e = new Ecode( "-", 1);
		
	//---------------------------------------------------------------------------

        System.out.println("\n\nTesting Getters ");
        System.out.println("=======================================");

        // TEST 1 : 
		
		try 
		{
            iNumTests++;
            System.out.print("Testing getEdgeCode() ");
            sTestString = e.getEdgeCode();
            if (sTestString != "-")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed   ");
        } 
		catch(Exception error) 
		{ 
			System.out.println("FAILED"); 
		}
		
		
		
        // TEST 2 : 
		
		try 
		{
            iNumTests++;
            System.out.print("Testing getEdgeWeight() ");
            iTestWeight = e.getEdgeWeight();
            if (iTestWeight != 1)
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed   ");
        } 
		catch(Exception error) 
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
            System.out.print("Testing setEdgeCode() ");
			e.setEdgeCode("L");
			
            sTestString = e.getEdgeCode();
            if (sTestString != "L")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed   ");
        } 
		catch(Exception error) 
		{ 
			System.out.println("FAILED"); 
		}
		
		
		
        // TEST 4 : 
		
		try 
		{
            iNumTests++;
            System.out.print("Testing setEdgeWeight() ");
			e.setEdgeWeight(12);
			
            iTestWeight = e.getEdgeWeight();
            if (iTestWeight != 12)
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed   ");
        } 
		catch(Exception error) 
		{ 
			System.out.println("FAILED"); 
		}
		
		//---------------------------------------------------------------------------

        // PRINT TEST SUMMARY
        System.out.print("\nNumber PASSED: " + iNumPassed + "/" + iNumTests);
        System.out.print(" -> " + (int)(double)iNumPassed/iNumTests*100 + "%\n");
		
	}
}