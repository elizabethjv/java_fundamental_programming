/*
* Name: Elizabeth Jossy Vellethara
* ID: V00883616
* Date:11/4/2018
* Filename: StringStack.java
* Details: CSC115 Assignment 3
*/
public class StringStack {

	public Node head;
	
	public StringStack(){
		head = null;
	}

	public boolean isEmpty() {
		return(head == null);
	}

	public String pop() throws StackEmptyException {
		if(head == null)
			throw new StackEmptyException("Stack is empty! Cannot remove");
		else{
			String item = head.item;
			head = head.next;
			return item;
		}
	}

	public String peek()throws StackEmptyException {
		if(head == null)
			throw new StackEmptyException("Stack is empty! Cannot peek");
		else
			return head.item;
	}

	public void push(String item) {
		Node newNode = new Node(item,head);
		head = newNode;
	}

	public void popAll() {
		head = null;
	}
	
	public static void main(String[] args) {
		StringStack stack = new StringStack();
		stack.push("1");
		System.out.println(stack.peek());
		stack.pop();
		System.out.println(stack.isEmpty());
	}
}
