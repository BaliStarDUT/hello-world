/**
*输入一个链表，从尾到头打印链表每个节点的值。
* 参考：https://www.nowcoder.com/questionTerminal/d0267f7f55b3412ba93bd35cfa8e8035
*/
import java.util.ArrayList;

public class LinkedListFromTailToHead{
  public static ArrayList<Integer> list = new ArrayList<Integer>();
  public static void main(String[] args){
    ListNode rootNode = new ListNode(0);
    // for(int i = 1;i<10;i++){
      ListNode listNode1 = new ListNode(1);
      ListNode listNode2 = new ListNode(2);
      ListNode listNode3 = new ListNode(3);

      rootNode.setNext(listNode1);
      listNode1.setNext(listNode2);
      listNode2.setNext(listNode3);
      listNode3.setNext(null);

      printListFromTailToHead(rootNode);
      System.out.println(list);

    // }
  }
  public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
      // list.add(listNode.getVal()); //正向输出
      if(listNode.getNext() != null){
        printListFromTailToHead(listNode.getNext());
      }
      list.add(listNode.getVal());//反向输出
      return list;
  }

}
class ListNode {
      private int val;
      private ListNode next = null;

      ListNode(int val) {
          this.val = val;
      }
      public int getVal(){
        return this.val;
      }
      public void setVal(int val){
        this.val = val;
      }
      public ListNode getNext(){
        return this.next;
      }
      public void setNext(ListNode node){
        this.next = node;
      }
      public String toString(){
        return "ListNode("+val+")";
      }
  }
