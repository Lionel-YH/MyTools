package book.interview.programmer;
/**
 *
 * @author lionel
 *
 * @source 174题
 *
 */
class Node{
	Node next;
	Object data;
	public Node(Object data){
		this.data=data;
	}
}

public class MyLinkedList {
	
	private Node head;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyLinkedList myLinkedList = new MyLinkedList();
		System.out.println(myLinkedList.isEmpty());
		myLinkedList.insert("111");
		myLinkedList.insert("222");
		myLinkedList.insert("333");
		myLinkedList.insert("444");
		System.out.println(myLinkedList.isEmpty());
		System.out.println(myLinkedList.size());
		System.out.println(myLinkedList.get(myLinkedList.size()-1).data.toString());
		myLinkedList.travel();
	}
	
	public MyLinkedList(){
		this.head=null;
	}
	
	public int size(){
		int size =0;
		Node pos=head;
		while(pos!=null){
			pos=pos.next;
			size++;
		}
		return size;
	}
	
	public boolean isEmpty(){
		return head==null;
	}
	
	public void insert(Object data){
		Node node = new Node(data);
		if(head==null){
			head=node;
		}else{
			node.next=head;
			head=node;
		}
	}
	
	public Node get(int pos){
		if(pos<0||pos>=this.size()){
			return null;
		}
		Node node = head;
		if(pos==0){
			return head;
		}else{
			for(int i=0;i<pos;i++){
				node=node.next;
			}
			return node;
		}
	}
	
	public void travel(){
		while(head!=null){
			System.out.println(head.data.toString());
			head=head.next;
		}
	}
	
	public void printNode(Node node){
		System.out.println(node.data.toString());
	}
	
}
