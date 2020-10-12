package cn.com.zfq.arithmetic.prove_safety;

import java.util.Stack;

/***
 * @ClassName: StackImplementQueue
 * @Description:
 * 剑指 Offer 09. 用两个栈实现队列
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead操作返回 -1 )
 *
 * 示例 1：
 *
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 *
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 *
 * 1 <= values <= 10000
 * 最多会对appendTail、deleteHead 进行10000次调用
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/10/12 17:16
 * @Version: v1.0
 * @Modified By: 
 */
public class StackImplementQueue {

    /**
     * 定义第一个栈
     */
    private Stack<Integer> s1 = new Stack<>();

    /**
     * 定义第二个栈
     */
    private Stack<Integer> s2 = new Stack<>();

    public void appendTail(int value) {
        s1.push(value);
    }

    /**
     * 时间复杂度分析：O(1)
     * 空间复杂度分析：O(N)
     * <p>
     * 执行用时：56 ms, 在所有 Java 提交中击败了65.72%的用户
     * 内存消耗：46.9 MB, 在所有 Java 提交中击败了43.60%
     * 的用户
     *
     * @return
     */
    public int deleteHead() {
        // 调用一次swap方法把s1中添加到s2中
        if (s2.isEmpty()) {
            swap();
        }
        //
        if (s2.isEmpty()) {
            return -1;
        } else {
            return s2.pop();
        }

    }

    private void swap() {
        // 如果s2当前不为空就结束
        if (!s2.isEmpty()) {
            return;
        }
        // 遍历s1栈
        while (!s1.isEmpty()) {
            // s1弹栈，s2进栈
            s2.push(s1.pop());
        }
    }

    public static void main(String[] args) {
        StackImplementQueue s = new StackImplementQueue();
        s.appendTail(3);
        System.out.println(s.deleteHead());
        System.out.println(s.deleteHead());
        System.out.println(s.deleteHead());
    }
}
