/*
* Name: Elizabeth Jossy Vellethara
* ID: V00883616
* Date:12/2/2018
* Filename: Heap.java
* Details: CSC115 Assignment 5
*/

import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * The shell of the class, to be completed as part
 * of CSC115 Assignment 5: Emergency Room.
 */


/**
 * Complete this class as per the Heap.html specification document.
 * Fill in any of the parts that have the comment:
 * ********  COMPLETE ******
 * Do not change method headers or code that has been supplied.
 * Delete all messages to you, including this one, before submitting.
 */

public class Heap<E extends Comparable<E>> {

	private Vector<E> heapArray;
	public int parentIndex;
    public int childIndex;

	public Heap() {
		heapArray = new Vector<E>();
		heapArray.setSize(0);
	}

	public boolean isEmpty(){
		return (heapArray.isEmpty());
	}

	public int size(){
		return heapArray.size();
	}
	
	public void insert(E item){
		if(isEmpty())
			heapArray.add(0,item);
		else
			heapArray.add(size()-1,item);
	
		heapifyUp();
	}
	
	public E removeRootItem() throws NoSuchElementException{	
		if(isEmpty())
			throw new NoSuchElementException("No Patient");
		else{
			swapElements(0,size()-1);
			E root =  heapArray.remove(size()-1);
			heapifyDown();
			return root;
		}
	}
	
	public E getRootItem() throws NoSuchElementException{
		if(isEmpty())
			throw new NoSuchElementException("No patients");
		else
			return heapArray.get(0);
	}
	
	/******** Tool methods ********/
	private int indexOf(E p){
		for (int i = 1; i < heapArray.capacity(); i++) {
			if (heapArray.elementAt(i).equals(p))   {
				return i;
			}
		}
		return -1;
	}
	/******** Tool methods ********/
	private int getParentIndex(int childIndex){
		if(childIndex>0)
			return (childIndex-1)/2;
		
		return -1;
	
	}
	
	private int getLeftChildIndex(int parentIndex){
		return ((2*parentIndex)+1);
	}
		
	private int getRightChildIndex(int parentIndex){
		return ((2*parentIndex)+2);
	}
		
	private boolean hasLeftChildIndex(int index){
		return(getLeftChildIndex(index) < size());
	}		

	private boolean hasRightChildIndex(int index){
		return(getRightChildIndex(index) < size());
	}
		
	private boolean hasParentIndex(int index){
		return (getParentIndex(index) >= 0);
	}
		
	private E getLeftChild(int parentIndex){
		return heapArray.get(getLeftChildIndex(parentIndex));
	}
		
	private E getRightChild(int parentIndex){
		return heapArray.get(getRightChildIndex(parentIndex));
	}	

	private E getParent(int parentIndex){
		return heapArray.get(getParentIndex(parentIndex));
	}
		
	private void swapElements(int firstIndex, int secondIndex){
		E temp = heapArray.get(firstIndex);
		heapArray.setElementAt(heapArray.get(secondIndex),firstIndex);
		heapArray.setElementAt(temp,secondIndex);
	}
		
	private void heapifyUp(){
		int curr = size()-1;
		while(hasParentIndex((curr))&&(getParent(curr).compareTo(heapArray.elementAt(curr))<0)){//if it has a paenrt and if its value is greater than thelast value
		    swapElements(getParentIndex(curr),curr);// swap parent and curr item
			curr =  getParentIndex(curr);//if this line not there only one swap would happen, after swapping the parent will be the beginning curr item, so we are following that exact number throughtout the loop until the parents less
		}
	}

	private void heapifyDown(){// we CALL this after the rooyt is removed and the last item AT THE BOTTOM IS PUT ON the root
		int curr = 0;
		while(hasLeftChildIndex(curr)){
			int smallchild = getLeftChildIndex(curr);
				
			if(hasRightChildIndex(curr) && (heapArray.get(getRightChildIndex(curr)).compareTo(heapArray.get(smallchild))<0) )
				smallchild = getRightChildIndex(curr);
				
            if(heapArray.get(curr).compareTo(heapArray.get(smallchild))>0) 
                break;
			
            swapElements(curr, smallchild);
            curr = smallchild;////whyyy
		}
	}
	
	/********  DEBUG USE methods ********/
	public void print_vector() {
		System.out.println(" *************** Array is ***************");
		for (int i = 0; i < heapArray.size(); i++){
			System.out.println(heapArray.get(i));
		}
	}
	
	public static void main(String args []){
		Heap <ER_Patient> hp = new Heap <ER_Patient>();
		System.out.println("Is the heap empty?"+hp.isEmpty());
		System.out.println("What is its size ?"+hp.size()+"\n");
		hp.insert(new ER_Patient ("Life-threatening"));
		hp.insert(new ER_Patient ("Major fracture"));
		hp.insert(new ER_Patient ("Chronic"));
		hp.print_vector();
		System.out.println("\nIs the heap empty?"+hp.isEmpty());
		System.out.println("What is its size ?"+hp.size()+"\n");
		System.out.println("The highest priority patient is"+hp.getRootItem()+"\n");
		System.out.println("Removing"+hp.removeRootItem()+"from the heap"+"\n");
		hp.print_vector();
		System.out.println("\nThe highest priority patient is"+hp.getRootItem()+"\n");
		/* you can add more tests */
	}
}
