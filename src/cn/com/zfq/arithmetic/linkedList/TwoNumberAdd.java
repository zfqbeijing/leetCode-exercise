package cn.com.zfq.arithmetic.linkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/***
 *
 * @Author : 张枫琴
 * @Description
 * 2. 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Version : v1.0.0
 * @Date : Created in 2020/8/30 17:04
 * @Modified By : 
 **/
public class TwoNumberAdd {

    /**
     * 对两个链表进行遍历按照位数进行相加
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.89%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了59.80%的用户
     *
     * @param l1 第一个链表
     * @param l2 第二个链表
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 定义一个新链表 用来存储计算结果，默认创建的是带头节点所以默认是0
        ListNode temp = new ListNode(0);
        // 记住第一个节点
        ListNode node = temp;
        // 进位 也就是 如果相加的是大于10 我们在下一位的时候加1，默认是0
        int carry = 0;
        // 对两个两个链表进行遍历
        while (l1 != null || l2 != null) {
            // 如果l1链表不为null 就取出l1的值，否则为0
            int x = (l1 != null) ? l1.val : 0;
            // 如果l2链表不为null 就取出l2的值，否则为0
            int y = (l2 != null) ? l2.val : 0;
            // 因为都是个位数相加 所以最大指是等于19的 ，得把进位加上
            int sum = x + y + carry;
            // 把得出的结果存入链表中，但是必须跟10取余 只要不管是否大于等于10 都会取得个位数的
            temp.next = new ListNode(sum % 10);
            // 指向下一个节点
            temp = temp.next;
            // 求进位 如果大于10 即可以求出进位为1 否则就是0
            carry = sum / 10;
            // 判断l1链表是否为空 如果不为空则指向下一个节点
            if (l1 != null) {
                l1 = l1.next;
            }
            // 判断l2链表是否为空 如果不为空则指向下一个节点
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 有可能存在一种情况 但两个链表最后的值计算 大于等于10 的情况且两个链表都为空了 ，得把进位放入最后一个节点
        if (carry > 0) {
            temp.next = new ListNode(carry);
            temp = temp.next;
        }
        // 因为默认创建的是带头节点 所以我们要从temp链表的下一个开始
        return node.next;
    }

    /**
     * 如果链表中的数字不是按逆序存储的呢？例如：
     * (3→4→2)+(4→6→5)=8→0→7
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersExt(ListNode l1, ListNode l2) {
        // 定义一个新链表 用来存储计算结果，默认创建的是带头节点所以默认是0
        ListNode temp = new ListNode(0);
        // 记住第一个节点
        ListNode node = temp;
        // 进位 也就是 如果相加的是大于10 我们在下一位的时候加1，默认是0
        int carry = 0;
        // 使用栈 接收l1链表的数据
        Stack<Integer> stackL1 = new Stack<>();
        // 使用栈 接收l2链表的数据
        Stack<Integer> stackL2 = new Stack<>();
        // 对两个两个链表进行遍历,并且加入栈中
        while (l1 != null || l2 != null) {
            // 判断l1链表是否为空 如果不为空则指向下一个节点
            if (l1 != null) {
                stackL1.push(l1.val);
                l1 = l1.next;
            }
            // 判断l2链表是否为空 如果不为空则指向下一个节点
            if (l2 != null) {
                stackL2.push(l2.val);
                l2 = l2.next;
            }
        }
        // 遍历两个栈
        while (!stackL1.empty() || !stackL2.empty()) {
            // 如果stackL1栈不为null 就弹栈，否则为0
            int x = (!stackL1.empty()) ? stackL1.pop() : 0;
            // 如果stackL2栈不为null 就弹栈，否则为0
            int y = (!stackL2.empty()) ? stackL2.pop() : 0;
            // 因为都是个位数相加 所以最大指是等于19的 ，得把进位加上
            int sum = x + y + carry;
            // 把得出的结果存入链表中，但是必须跟10取余 只要不管是否大于等于10 都会取得个位数的
            temp.next = new ListNode(sum % 10);
            // 指向下一个节点
            temp = temp.next;
            // 求进位 如果大于10 即可以求出进位为1 否则就是0
            carry = sum / 10;
        }
        // 有可能存在一种情况 但两个链表最后的值计算 大于等于10 的情况且两个链表都为空了 ，得把进位放入最后一个节点
        if (carry > 0) {
            temp.next = new ListNode(carry);
            temp = temp.next;
        }
        // 因为默认创建的是带头节点 所以我们要从temp链表的下一个开始
        return node.next;
    }

    /**
     * 测试打印类
     *
     * @param node
     */
    public void print(ListNode node) {
        while (true) {
            if (node.next == null) {
                System.out.print(node.val);
                break;
            }
            System.out.print(node.val + " -> ");
            node = node.next;
        }
    }

    public void printTemplate(ListNode l1, ListNode l2) {
        System.out.print("(");
        print(l1);
        System.out.print(") + (");
        print(l2);
        System.out.print(") 计算结果:");
        System.out.println();
    }

    /**
     * 做测试数据
     */
    public List<ListNode> testManufacturingData() {
        List<ListNode> list = new ArrayList<>();

        /**
         * 测试用例1：(2 -> 4 -> 3) + (5 -> 6 -> 4)
         * 预测结果：7 -> 0 -> 8
         */
        ListNode test1_l1 = new ListNode(2);
        test1_l1.next = new ListNode(4);
        test1_l1.next.next = new ListNode(3);
        ListNode test1_l2 = new ListNode(5);
        test1_l2.next = new ListNode(6);
        test1_l2.next.next = new ListNode(4);
        list.add(test1_l1);
        list.add(test1_l2);
        /**
         * 测试用例2：(9 -> 3 -> 5) + (8 -> 5 -> 4 -> 1)
         * 预测结果：7 -> 9 -> 9 -> 1
         */
        ListNode test2_l1 = new ListNode(9);
        test2_l1.next = new ListNode(3);
        test2_l1.next.next = new ListNode(5);
        ListNode test2_l2 = new ListNode(8);
        test2_l2.next = new ListNode(5);
        test2_l2.next.next = new ListNode(4);
        test2_l2.next.next.next = new ListNode(1);

        list.add(test2_l1);
        list.add(test2_l2);
        /**
         * 测试用例3：(6 -> 2 -> 8 -> 7) + (5 -> 6 -> 1)
         * 预测结果：1 -> 9 -> 9 -> 7
         */
        ListNode test3_l1 = new ListNode(6);
        test3_l1.next = new ListNode(2);
        test3_l1.next.next = new ListNode(8);
        test3_l1.next.next.next = new ListNode(7);
        ListNode test3_l2 = new ListNode(5);
        test3_l2.next = new ListNode(6);
        test3_l2.next.next = new ListNode(1);
        list.add(test3_l1);
        list.add(test3_l2);
        /**
         * 测试用例4：(1 -> 1 -> 6 ) + (3 -> 0 -> 4)
         * 预测结果：4 -> 2 -> 0 -> 1
         */
        ListNode test4_l1 = new ListNode(1);
        test4_l1.next = new ListNode(1);
        test4_l1.next.next = new ListNode(6);
        ListNode test4_l2 = new ListNode(3);
        test4_l2.next = new ListNode(1);
        test4_l2.next.next = new ListNode(4);
        list.add(test4_l1);
        list.add(test4_l2);
        return list;
    }

    /**
     * 计算
     */
    public void calculate() {
        List<ListNode> list = testManufacturingData();
        // 测试 addTwoNumbers
        for (int i = 0; i < (list.size()) / 2; i++) {
            printTemplate(list.get(i), list.get(i + 1));
            print(addTwoNumbers(list.get(i), list.get(i+1)));
            System.out.println();
        }

        for (int i = 0; i < (list.size()) / 2; i++) {
            printTemplate(list.get(i), list.get(i + 1));
            print(addTwoNumbersExt(list.get(i), list.get(i + 1)));
            System.out.println();
        }
    }


    public static void main(String[] args) {
        TwoNumberAdd add = new TwoNumberAdd();
        add.calculate();
    }

}

/**
 * 定义一个链表
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        this.val = x;
    }
}
