package cn.com.zfq.arithmetic.tree;

import cn.com.zfq.arithmetic.utils.TreeNode;
import cn.com.zfq.arithmetic.utils.TreeOperation;

import java.util.Stack;

/***
 * @ClassName: ReconstructionBinaryTree
 * @Description:
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder =[9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/10/12 17:11
 * @Version: v1.0
 * @Modified By: 
 */
public class ReconstructionBinaryTree {

    public static void main(String[] args) {
        int[] inorder = {15, 9, 10, 3, 20, 5, 7, 8, 4};
        int[] postorder = {15, 10, 9, 5, 4, 8, 7, 20, 3};
        ReconstructionBinaryTree tree = new ReconstructionBinaryTree();
        TreeOperation.show(tree.buildTree(inorder, postorder));
    }

    /**
     * 暴力解法 迭代
     * 时间复杂度分析：O(N),我们对数组的遍历都是一遍
     * 空间复杂度分析：O(N),我们引入了栈结构，当二叉树最坏的情况下达到N，所以是N
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了97.91%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 判空和等于零就没有意义
        if (postorder == null || postorder.length == 0) {
            return null;
        }
        // 定义一个栈，用来记录遍历的节点
        Stack<TreeNode> stack = new Stack<>();
        // 定义一个根节点
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        // 并且把根节点加入栈中
        stack.push(root);
        // 定义一个临时节点
        TreeNode node = null;
        // 定义一个值记录中序遍历的下标
        int inorderIndex = inorder.length - 1;
        // 遍历后序遍历数组
        for (int i = postorder.length - 2; i >= 0; i--) {
            // 给临时节点赋值
            node = stack.peek();
            /**
             * 判断栈顶元素对应节点的值和中序遍历该位置的值是否相等
             * 如果不相等，则将下一个位置的值作为该节点的右节点
             * 如果相等：
             *  去循环栈，当栈为空或者栈顶元素的对应节点的值和中序遍历数组不相等时候结束
             *  在循环栈的时候，如果和中序遍历的值相等，并且记录当前节点，然后弹栈
             *  当不满足就添加当前节点的右节点，加入栈中
             */
            if (node.val == inorder[inorderIndex]) {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    //记录弹出的node
                    node = stack.pop();
                    inorderIndex--;
                }
                // 把值赋给左节点
                node.left = new TreeNode(postorder[i]);
                // 并且把这个左节点加入栈中
                stack.push(node.left);
            } else {
                // 赋值给右节点
                node.right = new TreeNode(postorder[i]);
                // 并且将该右节点加入栈中
                stack.push(node.right);
            }
        }

        return root;
    }
}
