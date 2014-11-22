package uk.ac.aber.dcs.chh57.cs21120.assignment1;

public class ArrayQueue { 
	 Object[] queue; 
	 int head, tail, length; 
	 public ArrayQueue(int startsize) { 
	 queue = new Object[startsize]; 
	 head = tail = length = 0; 
}
	 
	 public void enQ(Object o) { 
		 if (length==queue.length) { 
		 // could copy to a (2x) larger array 
		 // or throw an exception 
		 } 
		 queue[tail++]=o; 
		 length++; 
		 if (tail == queue.length) tail=0; 
		 }
	 
	 public Object deQ() {  
		 Object o = queue[head]; 
		 queue[head]=null; 
		 head++; 
		 if (head==queue.length) head=0; 
		 length--; 
		 return o; 
		 }
	 	 
	 public Object front() throws QueueEmptyException { 
		 if (isEmpty()) throw new QueueEmptyException(); 
		 return queue[head]; 
		} 
		public int length() { return length; } 
		public boolean isEmpty() { return length==0; } 
		public void clear() { 
		 length=0; 
		 queue = new Object[queue.length]; 
		 head = tail = 0; 
		} 
	 
}
