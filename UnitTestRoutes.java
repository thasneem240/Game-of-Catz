/**********************************************************************************
 * File: UnitTestRoutes.java													  *
 * Author: Mr.A.S.M. Thasneem                          				              *
 * Date Created: 10/10/2021                            				              *
 * Date Modified: 16/10/2021                           				              * 
 * Purpose: Test Harness for Routes class					  					  *
 **********************************************************************************/
 
 import java.io.*;
public class UnitTestRoutes
{
	public static void main(String args[])
	{
        // VARIABLE DECLARATIONS
        int iNumPassed = 0;
        int iNumTests = 0;
        Routes r = null;
        String sTestString;
        int iTestTotal;
		
		
		r = new Routes( "A->B->C", 60, 40);
		
	//---------------------------------------------------------------------------

        System.out.println("\n\nTesting Getters ");
        System.out.println("=======================================");

        // TEST 1 : 
		
		try 
		{
            iNumTests++;
            System.out.print("Testing getPath() ");
            sTestString = r.getPath();
            if (sTestString != "A->B->C")
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
            System.out.print("Testing getNodeTotal() ");
            iTestTotal = r.getNodeTotal();
            if (iTestTotal != 60)
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed   ");
        } 
		catch(Exception e) 
		{ 
			System.out.println("FAILED"); 
		}
		
		
		
		 // TEST 3 : 
		
		try 
		{
            iNumTests++;
            System.out.print("Testing getEdgeTotal() ");
            iTestTotal = r.getEdgeTotal();
            if (iTestTotal != 40)
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
            System.out.print("Testing getTotalCost() ");
            iTestTotal = r.getNodeTotal() + r.getEdgeTotal();
            if (iTestTotal != 100)
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