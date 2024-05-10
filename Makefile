##
 # File: Makefile
 # File Created: 15, October 2021
 # Author: A.S.M. Thasneem
 # -----
 # Last Modified: 17, October 2021
 # Modified By: A.S.M. Thasneem
 # -----
 # Purpose: A make file to compile
##


# Makefile Variables
JCC = javac
JFLAGS = -g
default: DSAGraph.class DSALinkedList.class DSAQueue.class DSAStack.class Ncode.class Ecode.class Routes.class MYException.class gameofcatz.class UnitTestDSALinkedList.class UnitTestDSAStack.class UnitTestDSAQueue.class UnitTestNcode.class UnitTestEcode.class UnitTestRoutes.class UnitTestDSAGraph.class


DSAGraph.class: DSAGraph.java DSALinkedList.java DSAQueue.java DSAStack.java Ecode.java Ncode.java Routes.java MYException.java
		$(JCC) $(JFLAGS) DSAGraph.java
	
DSALinkedList.class: DSALinkedList.java MYException.java
		$(JCC) $(JFLAGS) DSALinkedList.java
	
DSAQueue.class: DSAQueue.java DSALinkedList.java MYException.java
		$(JCC) $(JFLAGS) DSAQueue.java
	
DSAStack.class: DSAStack.java DSALinkedList.java MYException.java
		$(JCC) $(JFLAGS) DSAStack.java
		
Ncode.class: Ncode.java 
		$(JCC) $(JFLAGS) Ncode.java
		
Ecode.class: Ecode.java 
		$(JCC) $(JFLAGS) Ecode.java
		
Routes.class: Routes.java 
		$(JCC) $(JFLAGS) Routes.java
		
MYException.class: MYException.java 
		$(JCC) $(JFLAGS) MYException.java
		
gameofcatz.class: gameofcatz.java DSAGraph.java DSALinkedList.java Ecode.java Ncode.java Routes.java MYException.java
		$(JCC) $(JFLAGS) gameofcatz.java


### UnitTest Classes ###
		
UnitTestDSALinkedList.class : UnitTestDSALinkedList.java DSALinkedList.java
		$(JCC) $(JFLAGS) UnitTestDSALinkedList.java
		
UnitTestDSAStack.class : UnitTestDSAStack.java DSALinkedList.java MYException.java
		$(JCC) $(JFLAGS) UnitTestDSAStack.java
		
UnitTestDSAQueue.class : UnitTestDSAQueue.java DSALinkedList.java MYException.java
		$(JCC) $(JFLAGS) UnitTestDSAQueue.java
		
UnitTestNcode.class : UnitTestNcode.java
		$(JCC) $(JFLAGS) UnitTestNcode.java
		
UnitTestEcode.class : UnitTestEcode.java
		$(JCC) $(JFLAGS) UnitTestEcode.java
		
UnitTestRoutes.class : UnitTestRoutes.java
		$(JCC) $(JFLAGS) UnitTestRoutes.java
		
UnitTestDSAGraph.class : UnitTestDSAGraph.java DSALinkedList.java MYException.java
		$(JCC) $(JFLAGS) UnitTestDSAGraph.java
		
clean :
		$(RM) *.class