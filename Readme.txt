
# How to Run : make (Use make file)	


DSAGraph.java:
	Dependencies : DSALinkedList.java DSAQueue.java DSAStack.java Ecode.java Ncode.java Routes.java MYException.java
 
DSALinkedList.java: 
	Dependencies : MYException.java
	
DSAQueue.java: 
	Dependencies : DSALinkedList.java MYException.java
	
DSAStack.java : 
	Dependencies :	DSALinkedList.java MYException.java
		
Ncode.java :
		Dependencies : Nothing
		
Ecode.java 
		Dependencies : Nothing
		
Routes.java 
		Dependencies : Nothing
		
MYException.java : 
		Dependencies : Nothing
		
gameofcatz.java : 
		Dependencies : DSAGraph.java DSALinkedList.java Ecode.java Ncode.java Routes.java MYException.java	




### UnitTest Classes

UnitTestDSALinkedList.java :
		Dependencies: DSALinkedList.java
		
UnitTestDSAStack.java :
		Dependencies: DSALinkedList.java MYException.java
		
UnitTestDSAQueue.java :
		Dependencies: DSALinkedList.java MYException.java
		
UnitTestNcode.java :
		Dependencies: Nothing
		
UnitTestEcode.java :
		Dependencies: Nothing
		
UnitTestRoutes.java :
		Dependencies: Nothing
		
UnitTestDSAGraph.java :
		Dependencies: DSALinkedList.java MYException.java
			