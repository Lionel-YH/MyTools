package coding.leetcode;

public class AddTwoNumbers {
    //My Solution
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0;
        ListNode result = new ListNode(0);
        ListNode flag = result;
        while(l1!=null||l2!=null){
            if(l1!=null){
                sum+=l1.val;
                l1 = l1.next;
            }
            if(l2!=null){
                sum+=l2.val;
                l2 = l2.next;
            }
            flag.next = new ListNode(sum%10);
            flag = flag.next;
            sum/=10;
        }
        if(sum==1) flag.next = new ListNode(1);
        return result.next;
    }

    public static void main(String[] args){
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode l1 = new ListNode(2);l1.next = new ListNode(4);l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);l2.next = new ListNode(6);l2.next.next = new ListNode(4);
        ListNode result = addTwoNumbers.addTwoNumbers(l1,l2);
        System.out.print(result.val);
        System.out.print(",");
        System.out.print(result.next.val);
        System.out.print(",");
        System.out.print(result.next.next.val);
    }
}

class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
}

