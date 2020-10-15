package cn.com.zfq.arithmetic.tree;

import cn.com.zfq.arithmetic.utils.TreeNode;

import java.util.Stack;

/***
 * @ClassName: ReconstructionBinaryTree
 * @Description:
 * 剑指 Offer 07. 重建二叉树
 * 105. 从前序与中序遍历序列构造二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * 例如，给出
 *
 * 前序遍历 preorder =[3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/10/12 17:11
 * @Version: v1.0
 * @Modified By: 
 */
public class ReconstructionBinaryTreeII {

    public static void main(String[] args) {

    }

    /**
     * 暴力求解 迭代
     * 时间复杂度分析：O(N),我们对数组的遍历都是一遍
     * 空间复杂度分析：O(N),我们引入了栈结构，当二叉树最坏的情况下达到N，所以是N
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了98.17%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了99.96%的用户
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 判空和长度判断
        if (inorder == null || inorder.length == 0) {
            return null;
        }
        // 定义一个棵二叉树
        TreeNode root = new TreeNode(preorder[0]);
        // 定义一个栈用来存储 节点
        Stack<TreeNode> stack = new Stack<>();
        // 将第一个节点加入栈中
        stack.push(root);
        // 记录中序遍历数组下标
        int inorderIndex = 0;
        // 定义临时节点方便记录
        TreeNode node = null;
        // 遍历先序数组
        for (int i = 1; i < preorder.length; i++) {
            // 记录当前节点
            node = stack.peek();
            /**
             * 当前节点的值与中序遍历的值相等的情况
             * 如果不相等，下一个节点就作为该节点的左节点，并且把该下一个节点加入栈中
             * 如果相等情况：
             *  去循环栈，当栈为空或者栈顶元素的对应节点的值和中序遍历数组不相等时候结束
             *  在循环栈的时候，如果和中序遍历的值相等，并且记录当前节点，然后弹栈
             *  当不满足就添加当前节点的右节点，加入栈中
             */
            if (node.val == inorder[inorderIndex]) {
                // 去循环栈找到与与中序遍历相等的值
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    // 把弹出的节点赋值给node
                    node = stack.pop();
                    inorderIndex++;
                }
                // 把值赋值给右节点
                node.right = new TreeNode(preorder[i]);
                // 加入栈中
                stack.push(node.right);
            } else {
                // 添加二叉树的左节点
                node.left = new TreeNode(preorder[i]);
                // 把该节点添加到栈中
                stack.push(node.left);
            }
        }
        return root;
    }
}
