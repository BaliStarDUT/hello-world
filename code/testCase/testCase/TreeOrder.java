// package com.tree.traverse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Caijh
 *
 * 2017年6月2日 下午7:21:10
 */

public class BuildTreePreOrderInOrder {

  /**
   *       1
   *       / \
   *      3  5
   *      /   \
   *     7    11
   *    /
   *   9
   */
  public static int treeNode = 0;//记录先序遍历节点的个数
  private List<Node> nodeList = new ArrayList<>();//层次遍历节点的队列
  public static void main(String[] args) {
    BuildTreePreOrderInOrder build = new BuildTreePreOrderInOrder();
    int[] preOrder = { 1, 3, 7, 9, 5, 11};
    int[] inOrder = { 9, 7, 3, 1, 5, 11};

    treeNode = preOrder.length;//初始化二叉树的节点数
    Node root = build.buildTreePreOrderInOrder(preOrder, 0, preOrder.length - 1, inOrder, 0, preOrder.length - 1);
    System.out.print("先序遍历：");
    build.preOrder(root);
    System.out.print("\n中序遍历：");
    build.inOrder(root);
    System.out.print("\n原二叉树：\n");
    build.prototypeTree(root);
  }

  /**
   * 分治法
   * 通过先序遍历结果和中序遍历结果还原二叉树
   * @param preOrder  先序遍历结果序列
   * @param preOrderBegin   先序遍历起始位置下标
   * @param preOrderEnd  先序遍历末尾位置下标
   * @param inOrder  中序遍历结果序列
   * @param inOrderBegin  中序遍历起始位置下标
   * @param inOrderEnd   中序遍历末尾位置下标
   * @return
   */
  public Node buildTreePreOrderInOrder(int[] preOrder, int preOrderBegin, int preOrderEnd, int[] inOrder, int inOrderBegin, int inOrderEnd) {
    if (preOrderBegin > preOrderEnd || inOrderBegin > inOrderEnd) {
      return null;
    }
    int rootData = preOrder[preOrderBegin];//先序遍历的第一个字符为当前序列根节点
    Node head = new Node(rootData);
    int divider = findIndexInArray(inOrder, rootData, inOrderBegin, inOrderEnd);//找打中序遍历结果集中根节点的位置
    int offSet = divider - inOrderBegin - 1;//计算左子树共有几个节点，节点数减一，为数组偏移量
    Node left = buildTreePreOrderInOrder(preOrder, preOrderBegin + 1, preOrderBegin + 1 + offSet, inOrder, inOrderBegin,inOrderBegin + offSet);
    Node right = buildTreePreOrderInOrder(preOrder, preOrderBegin + offSet + 2, preOrderEnd, inOrder, divider + 1, inOrderEnd);
    head.left = left;
    head.right = right;
    return head;
  }
  /**
   * 通过先序遍历找到的rootData根节点，在中序遍历结果中区分出：中左子树和右子树
   * @param inOrder  中序遍历的结果数组
   * @param rootData  根节点位置
   * @param begin  中序遍历结果数组起始位置下标
   * @param end  中序遍历结果数组末尾位置下标
   * @return return中序遍历结果数组中根节点的位置
   */
  public int findIndexInArray(int[] inOrder, int rootData, int begin, int end) {
    for (int i = begin; i <= end; i++) {
      if (inOrder[i] == rootData)
        return i;
    }
    return -1;
  }
  /**
   * 二叉树先序遍历结果
   * @param n
   */
  public void preOrder(Node n) {
    if (n != null) {
      System.out.print(n.val + ",");
      preOrder(n.left);
      preOrder(n.right);
    }
  }
  /**
   * 二叉树中序遍历结果
   * @param n
   */
  public void inOrder(Node n) {
    if (n != null) {
      inOrder(n.left);
      System.out.print(n.val + ",");
      inOrder(n.right);
    }
  }
  /**
   * 还原后的二叉树
   * 二叉数层次遍历
   * 基本思想：
   *   1.因为推导出来的二叉树是保存在Node类对象的子对象里面的，（类似于c语言的结构体）如果通过递归实现层次遍历的话，不容易实现
   *   2.这里采用List队列逐层保存Node对象节点的方式实现对二叉树的层次遍历输出
   *   3.如果父节点的位置为i,那么子节点的位置为，2i 和 2i+1;依据这个规律逐层遍历，通过保存的父节点，找到子节点。并保存，不断向下遍历保存。
   * @param tree
   */
  public void prototypeTree(Node tree){
    //用list存储层次遍历的节点
    if(tree !=null){
      if(tree!=null)
        nodeList.add(tree);
      nodeList.add(tree.left);
      nodeList.add(tree.right);
      int count=3;
      //从第三层开始
      for(int i=3;count<treeNode;i++){
        //第i层第一个子节点的父节点的位置下标
        int index = (int) Math.pow(2, i-1-1)-1;
        /**
         * 二叉树的每一层节点数遍历
         * 因为第i层的最大节点数为2的i-1次方个，
         */
        for(int j=1;j<=Math.pow(2, i-1);){
          //计算有效的节点的个数，和遍历序列的总数做比较，作为判断循环结束的标志
          if(nodeList.get(index).left!=null)
            count++;
          if(nodeList.get(index).right!=null)
            count++;
          nodeList.add(nodeList.get(index).left);
          nodeList.add(nodeList.get(index).right);
          index++;
          if(count>=treeNode)//当所有有效节点都遍历到了就结束遍历
            break;
          j+=2;//每次存储两个子节点，所以每次加2
        }
      }
      int flag=0,floor=1;
      for(Node node:nodeList){
        if(node!=null)
          System.out.print(node.val+" ");
        else
          System.out.print("# ");//#号表示空节点
        flag++;
        /**
         * 逐层遍历输出二叉树
         *
         */
        if(flag>=Math.pow(2, floor-1)){
          flag=0;
          floor++;
          System.out.println();
        }
      }
    }
  }
  /**
   * 内部类
   * 1.每个Node类对象为一个节点，
   * 2.每个节点包含根节点，左子节点和右子节点
   */
  class Node {
    Node left;
    Node right;
    int val;
    public Node(int val) {
      this.val = val;
    }
  }
}
