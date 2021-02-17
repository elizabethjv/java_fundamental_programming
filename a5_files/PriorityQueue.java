/*
* Name: Elizabeth Jossy Vellethara
* ID: V00883616
* Date:12/2/2018
* Filename: PriorityQueue.java
* Details: CSC115 Assignment 5
*/

import java.util.NoSuchElementException;

/**
 * The shell of the class, to be completed as part of the
 * CSC115 Assignment 5 : Emergency Room
 */

/**
 * Complete this class as per the Heap.html specification document.
 * Fill in any of the parts that have the comment:
 * ********  COMPLETE *******
 * Do not change method headers or code that has been supplied.
 * Delete all messages to you, including this one, before submitting.
 */

public class PriorityQueue<E extends Comparable<E>> {
	
	private Heap<E> heap;

	public PriorityQueue() {
		heap = new Heap<E>();
	}
	
    public E dequeue() throws NoSuchElementException{	
		if(isEmpty())
			throw new NoSuchElementException();
		else
			return heap.removeRootItem();
    }
	
 	public void enqueue(E item) {
        heap.insert(item);
    }

    public boolean isEmpty() {
		return heap.isEmpty();	
    }

    public E peek() throws NoSuchElementException{	
		if(isEmpty())
			throw new NoSuchElementException();
		else{
			return heap.getRootItem();
		}
		
    }

    public static void main(String args []) {
		
		PriorityQueue <ER_Patient> p1 = new PriorityQueue <ER_Patient> ();

		System.out.println("Is it empty ?"+p1.isEmpty());
		p1.enqueue(new ER_Patient("Walk-in"));
		p1.enqueue(new ER_Patient("Life-threatening"));
		System.out.println("The highest priority patient"+p1.peek());
		System.out.println("Is it empty ?"+p1.isEmpty());
		System.out.println("Removing "+p1.dequeue());
		System.out.println("The highest priority patient now "+p1.peek());
		/* you can add more tests */
    }
}
	
