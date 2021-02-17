/*
* Name: Elizabeth Jossy Vellethara
* ID: V00883616
* Date:11/4/2018
* Filename: Node.java
* Details: CSC115 Assignment 3
*/

public class Node{
	
	String item;
	Node next;
	
	public Node(){
		item = null;
		next = null;
	}
	
	public Node (String n){	
		item = n;
		next = null;
	}
	
	public Node (String n, Node nextNode){
		item = n;
		next = nextNode;	
	}
	
}