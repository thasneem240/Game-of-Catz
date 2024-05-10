/**********************************************************************************
 * File: MYException.java													  	  *
 * Author: Mr.A.S.M. Thasneem                          				              *
 * Date Created: 09/10/2021                            				              *
 * Date Modified: 16/10/2021                           				              *               
 * Purpose: Throw an exception when things goes wrong 				  			  *
 **********************************************************************************/
 public class MYException extends Exception
{
	private String error;
	
	public MYException(String message)
	{
		super(message);
	}
}