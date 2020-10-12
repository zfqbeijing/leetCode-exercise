package cn.com.zfq.arithmetic.prove_safety;

import java.util.Stack;

/***
 * @ClassName: FromTailToHeadPrint
 * @Description:
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 * @Author: 张枫琴
 * @Date: 2020/10/12 15:41
 * @Version: v1.0
 * @Modified By: 
 */
public class FromTailToHeadPrint {

    /**
     * 利用栈的特性来进行，其实也可以利用其它结构
     * 时间复杂度分析：O(N),链表的长度
     * 空间复杂度分析：O(N)
     * 还可以写成
     *  Stack<ListNode> stack = new Stack<>();
     *  stack.push(head);
     *  p[i] = stack.pop().val;
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了42.10%的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了85.18%的用户
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        // 判空
        if (head==null){
            return new int[0];
        }
        // 定义一个栈来接收节点
        Stack<Integer> stack = new Stack<>();
        // 遍历数组
        while (head!=null){
            // 加入栈中
            stack.push(head.val);
            // 指向下一个节点
            head = head.next;
        }
        // 定义一个接收值的数组
        int[] p = new int[stack.size()];
        // 遍历栈,并加入数组中
        for (int i = 0; i < p.length; i++) {
            // 弹栈加入数组中
            p[i] = stack.pop();
        }
        return p;
    }

    /**
     * 链表节点
     */
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}



