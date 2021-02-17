/*
* Name: Elizabeth Jossy Vellethara
* ID: V00883616
* Date: 9/23/2018
* Filename: Course.java
* Details: CSC115 Assignment 1
*/ 
public class Course 
{
    private static final int INITIAL_SIZE = 2;

    private String    code;
    private String    instructor;
    private int       size;
    private Student[] roster;

    //
    // Purpose:
    //  Initialize a new instance of Course. The initial size of
    //  the array *must* be the same as INITIAL_SIZE (i.e., you are
    //  *not* permitted to create an array that is the maximum size
    //  needed to pass all tests in A1test.java).
    //
    public Course(String code, String instructor)
    {
		this.code = code;
		this.instructor = instructor;
		roster = new Student [INITIAL_SIZE];
		size = 0;
    }


    //
    // Purpose:
    //  Return the course code used when creating this class instance.
    //
    public String getCode() 
	{
        return code;
    }    


    //
    // Purpose:
    //  Return the instructor used when creating this class instance.
    //
    public String getInstructor()
	{
        return instructor;
    }

    
    //
    // Purpose:
    //  return the number of students in the roster
    //
    // Returns:
    //  the number of students stored in the roster array
    //
    // Examples:
    //
    // If roster in c is {"V0333:Alice Miller:18", "V0211:Bob Smith:4.5"}
    // then c.getSize() returns 2.
    // 
    // If roster in c is {} then c.getSize() returns 0.
    //
    public int getSize() 
	{
        return size;
    }


    //
    // Purpose:
    //  return the Student in the roster at position index
    //
    // Pre-Conditions:
    //  for a Course c
    //  index >= 0 AND
    //  index < c.size
    //
    // Examples:
    //
    // If roster in c is {"V0333:Alice Miller:18", "V0211:Bob Smith:4.5"}
    // then:
    //   c.at(0) returns "V0333:Alice Miller:18"
    //   c.at(1) returns "V0211:Bob Smith:4.5"
    //   the result of calling c.at(3) is undefined
    //
    public Student at (int index)
    {
		return roster[index];
    }


    //
    // Purpose:
    //  drop (remove) the Student at position index in the roster
    //
    // Pre-Conditions:
    //  for a Course c where
    //      index >= 0 AND
    //      index < c.size
    //
    // If roster in c is {"V0333:Alice Miller:18", "V0211:Bob Smith:4.5"}
    // then after c.drop(0), roster is {"V0211:Bob Smith:4.5"}
    //
    public void drop (int index)
    {
		/* The following for loop starts from the index we want to delete
		 * The index is overwritten by the Student at the next index, and so the loop goes till the end
		 * At the end, the last student will be there two time; this can be solved by reducing the size by 1
		 */
		for( int i = index; i < roster.length-1; i++){
			roster[i] = roster[i+1];	
		}
				
		size --;
    }



    //
    // Purpose:
    //  enroll (add) the Student into the course by adding the Student
    //  object at the end of the current roster
    //
    // Comments:
    //
    //  {The array you allocated to store Students may
    //  get full, but you are still required to add this
    //  Student (until the JVM runs out of memory!)
    //
    //  This means that you should check to see if the array
    //  is currently full.  If it is, allocate a new array
    //  that is twice as big, then copy the values over
    //  and update the storage reference to be the new array
    //  Finally, insert the new student.
    //
    // Examples:
    //
    // If roster in c is {"V0333:Alice Miller:18"} and s 
    // is "V0211:Bob Smith:4.5", then the value of roster after 
    // c.enroll(s) is {"V0333:Alice Miller:18", "V0211:Bob Smith:4.5"}.
    //
    public void enroll (Student s) 
    {
		if ( roster.length-1 == size ) // checks if the array is full
		{
			Student[] temp = new Student[roster.length*2]; // a new array which is twice as big is created
			
			for ( int i =0; i < size; i++){
				temp[i] = roster[i]; // copies every student to the temp array
			}
			
			roster = temp; //changes the address of roster to temp
		}
		
		roster[size] = s; // adds element at the end of the array
		size ++; //increases the sizee by 1
    }


    //
    // Purpose:
    //  return the index where S# is in the roster, -1 otherwise
    //
    // Pre-Conditions:
    //  none
    //
    // Returns:
    //  position of s in the roster - an integer between 0 and size() - 1
    //  -1 if s is not in the roster
    //
    // Examples:
    //
    // If roster in c is {"V0333:Alice Miller:18", "V0211:Bob Smith:4.5"}
    //
    //  c.search("V0333") returns 0
    //  
    //  String t = "V0211"
    //  c.search(t) returns 1
    //
    //  c.search("V0444") returns -1
    //
    public int search (String snum)
    {
        for ( int i = 0; i < size; i++){
			if ( snum.equals(roster[i].getId()) ) // checks every students id and see if its equal to snum 
				return i;
		}
				return -1;	
    }
}
