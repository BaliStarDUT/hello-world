import java.util.Deque;
import java.util.ArrayDeque;

public class BinaryTreeTest{
//   链接：https://www.nowcoder.com/questionTerminal/8a19cbe657394eeaac2f6ea9b0f6fcf6
// 来源：牛客网

 public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode root=reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }
    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    private TreeNode reConstructBinaryTree(int [] pre,int startPre,int endPre,int [] in,int startIn,int endIn) {

        if(startPre>endPre||startIn>endIn)
            return null;
        TreeNode root=new TreeNode(pre[startPre]);

        for(int i=startIn;i<=endIn;i++)
            if(in[i]==pre[startPre]){
                root.left=reConstructBinaryTree(pre,startPre+1,startPre+i-startIn,in,startIn,i-1);
                root.right=reConstructBinaryTree(pre,i-startIn+startPre+1,endPre,in,i+1,endIn);
            }

        return root;
    }
  public static TreeNode reConstructBinaryTree(int[] pre,int[] in){
      int length = pre.length;
      if(length==0){
        return null;
      }
      TreeNode rootNode = new TreeNode(pre[0]);
      // printInt(pre);
      System.out.println("length-->"+length);
      // printInt(in);
      if(length>3){
        int index = 0;
        for(;index<in.length;index++){
          if(rootNode.val==in[index])
            break;
        }
        int[] subPre = new int[index];
        // System.out.println(pre[0]);

        System.arraycopy(pre,1,subPre,0,index);
        // int[] subIn = new int[length-index-1];
        // System.arraycopy(in,index+1,subIn,0,length-index-1);
        int[] subIn = new int[index];
        System.arraycopy(in,0,subIn,0,index);
        printInt(subPre);
        System.out.println("index-->"+index);
        printInt(subIn);
        rootNode.left = reConstructBinaryTree(subPre,subIn);

        // if()
        int[] rsubPre = new int[length-index-1];
        System.arraycopy(pre,index+1,rsubPre,0,length-index-1);
        int[] rsubIn = new int[length-index-1];
        System.arraycopy(in,index+1,rsubIn,0,length-index-1);
        printInt(rsubPre);
        System.out.println("index-->"+index);
        printInt(rsubIn);
        rootNode.right = reConstructBinaryTree(rsubPre,rsubIn);
      }else{
        // System.out.println("length============="+length);
        // printInt(pre);
        // printInt(in);
        // System.out.println("rootNode==>"+rootNode.val);
        // System.out.println("length============="+length);
        if(length==1){
          rootNode.left=null;
          rootNode.right=null;
        }
        if(length==2){
          if(pre[0]==in[0]){
            rootNode.right=new TreeNode(pre[1]);
            rootNode.left=null;
          }else{
            rootNode.left=new TreeNode(pre[1]);
            rootNode.right=null;

          }
        }
        if(length==3){
          if(pre[0]==in[0]){
            rootNode.right=new TreeNode(pre[1]);
            rootNode.left=new TreeNode(pre[2]);
          }else{
            rootNode.left=new TreeNode(pre[1]);
            rootNode.right=new TreeNode(pre[2]);
          }
        }
      }
      return rootNode;
  }
  //递归遍历
  private static void recursiveDFS(TreeNode node){
    if(node!=null){
      System.out.println(node.val);
      recursiveDFS(node.left);
      recursiveDFS(node.right);
    }
  }
  //迭代遍历
  private static void iterativeDFS(TreeNode root){
    Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
    stack.push(root);
    while(!stack.isEmpty()){
      //注意语句运行顺序
      TreeNode node = stack.pop();
      System.out.println(node.val);

      if(node.right!=null)
        stack.push(node.right);
      if(node.left!=null)
        stack.push(node.left);

    }
  }
  private static void printInt(int[] list){
    int index = 0;
    for(;index<list.length;index++){
      System.out.println(list[index]);
    }
  }
  public static void main(String[] args){
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);
    TreeNode node8 = new TreeNode(8);
    TreeNode node9 = new TreeNode(9);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node3.left = node6;
    node3.right = node7;
    node4.left = node8;
    node4.right = node9;
    // recursiveDFS(node1);
    // iterativeDFS(node1);
    // int[] pre = {1,2,4,7,3,5,6,8};
    // int[] in = {4,7,2,1,5,3,8,6};
    int[] pre = {1,2,3,4};
    int[] in = {4,3,2,1};

    TreeNode node = reConstructBinaryTree(pre,in);
    recursiveDFS(node);
  }
}

class TreeNode {
     int val;
     TreeNode left = null;
     TreeNode right = null;
     TreeNode(int x) { val = x; }
}
