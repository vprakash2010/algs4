
/**
 * @author vijay
 *
 */
public class Queue<Item> {

	/**
	 * class variables
	 */
	private Node first = null;
	private Node last = null;
	
	private class Node{
		Item item;
		Node next;
	}
	/**
	 * 
	 */
	public Queue() {
		// TODO Auto-generated constructor stub
	}
	
	public void enqueue(Item item){
		Node oldLast =null;
		oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		
		if(isEmpty())
		{
			first = last;
		}
		else
		{
			oldLast.next = last;		
		}
		
	}
	
	public Item dequeue(){
		if(isEmpty())
			return null;
		Item item = first.item;

		first = first.next;
		
		return item;
	}
	
	public boolean isEmpty(){
		if(first == null)
			return true;
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
