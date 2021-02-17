/*
* Name: Elizabeth Jossy Vellethara
* ID: V00883616
* Date:10/7/2018
* Filename: TaskListLL.java
* Details: CSC115 Assignment 2
*/
public class TaskListLL implements TaskList {
	
	TaskListNode head=null;
	int listLength=0;
	
    public int getLength() {
		int countLoop=0;
		listLength = 0;
        TaskListNode curr = head;
		if(head== null){ // if list is empty
			return listLength = 0;
		}
		while(curr!= null ){ 
			listLength++;
			curr= curr.next;
			countLoop++;
		}
		this.listLength = listLength;
		return this.listLength;
    }


    public boolean isEmpty() {
		if(head== null){
			return true;
		}
        return false;
    }


    public Task removeHead() {
		
		TaskListNode curr = head;
		TaskListNode temp = head;
        if(head== null){
			return null;
		}
		
		temp = head;
		head = curr.next;
		curr = head;
        
        return temp.task;
    }

    
    public Task remove(int number) {
		int taskNumber = number;
		
		if(head == null){ // case where list is empty.
			return null;
		}
		
		TaskListNode curr = head.next;
		TaskListNode prev = head;
		TaskListNode temp;

		
		while(prev != null){
			if( taskNumber == prev.task.getNumber()){
				temp = prev;
				prev = curr;
				if(prev == null){ // the case where the list had only one element.
					head=null;
				}
				return temp.task;
			} // end if
			prev = curr;
			curr = curr.next;
		} // end while
		
        return null;
    }


    public boolean insert(int priority, int number) {
		
		// Make an new instance of class Task.
		Task tempTask = new Task(priority, number);
		TaskListNode newNode = new TaskListNode(tempTask);
		TaskListNode curr = head;
		TaskListNode temp= null;
		TaskListNode prev = head;
		
		// First. Check if the list is empty. if the list is empty then insert the task at the first position.
		if(head==null){
			head = new TaskListNode(tempTask); // insert task at the first position
			return true;
		} // end if 
		
		while(curr.next!= null){
				if(curr.task.getNumber() == tempTask.getNumber()){ // if two tasks have same task number then return false.
					return false;
				}
				curr = curr.next;
			}// end while
		
			
			// At this point we know that the list is not empty and there is no similar task in the list.
			// Now, Insert the task in the list at appropriate position.
			
			curr= head; // resetting the value of curr to initial position.
			prev = curr;
			while(curr!= null){
				
				
				
				
				if(curr.task.compareTo(tempTask) < 0 ){ // if priority of tempTask is more than the current task
					// we will place the tempTask before curr task	----------------------------------
					if(curr == head){//for the case when node is being inserted at the head.
						temp = head;
						newNode.next = temp; 
						head = newNode;
						curr = head;
						return true;
						}
						
						temp  = curr;
						curr = newNode;
						newNode.next = temp;
						return true;   
					}// end  1st if
			
					
					
					if(curr.task.compareTo(tempTask)>0){
						while( (curr.task.compareTo(tempTask)>0) || ((curr.task.compareTo(tempTask)==0) && (curr.task.getNumber() < tempTask.getNumber()) ) ){ //find the smaller node than the reference node before the null
							prev = curr;
							curr=curr.next;
							
							if(curr==null){		
							prev.next = newNode; 
							newNode.next = null;
							return true;
							}
							}
						
						prev.next = newNode;
						newNode.next = curr;
						return true;
					}
					
					
					
					
					 // if temptask has equal priority 
					while( (curr!= null) && (curr.task.compareTo(tempTask) == 0) ){
						if(temp.task.getNumber() < curr.task.getNumber()){
							
							if(prev==null){
								newNode.next = head;
								head = newNode;
								return true;
							}
							// add node
							prev.next = newNode;
							newNode.next = curr;
							return true;
						}
						prev = curr;
						curr = curr.next;
					}// end while
					
					prev = curr;
					curr = curr.next;
					}// end while
								
				
				
			this.listLength++; //increment the list length by 1;
			return false;
			
			
	}// end method insert


    public Task retrieve(int pos) { 
		TaskListNode curr = head;
		if(pos<0 || pos>=(this.listLength) || head==null){  // if pos is undefined or list is empty
			return null;
		}
        for(int i=0; i<pos; i++){ // i<pos because if i<= pos then curr = curr.next will run for i= pos which we dont want. As curr will have the next value of pos+1;
			curr = curr.next;
		}// end for
		return curr.task;
    }// end method retrieve
	
}// end class
