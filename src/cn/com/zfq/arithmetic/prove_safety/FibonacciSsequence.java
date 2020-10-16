package cn.com.zfq.arithmetic.prove_safety;

/***
 * @ClassName: FibonacciSsequence
 * @Description:
 * 剑指 Offer 10- I. 斐波那契数列
 * 509. 斐波那契数 差别就是不需要取模
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 *
 * F(0) = 0, F(1)= 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：5
 *
 * 提示：
 *
 * 0 <= n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/10/12 17:55
 * @Version: v1.0
 * @Modified By: 
 */
public class FibonacciSsequence {

    /**
     * 循环的方法
     * 时间复杂度分析：O(N),遍历了N次
     * 空间复杂度分析：O(1)
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了96.11%的用户
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        // 如果n==0直接返回
        if (n < 2) {
            return n;
        }
        // 定义求和值
        int sum = 0;
        // 定义第一个初始值
        int re = 0;
        // 定义第二个初始值
        int ter = 1;
        // 对数进行遍历
        for (int i = 0; i < n - 1; i++) {
            // 计算当前第n项
            sum = (ter + re) % 1000000007;
            // 把上一次的第二项作为下一次的第一项
            re = ter;
            // 把当前的第n项作为下一次的第二项
            ter = sum;
        }

        return sum;
    }

    /**
     * 暴力递归
     * 时间复杂度分析：O(2^N)
     * 空间复杂度分析：O(N)
     * 超出时间限制
     *
     * @param n
     * @return
     */
    public int fib2(int n) {
        // 如果小于2直接返回当前的n即可
        if (n < 2) {
            return n;
        }
        return fib(n - 2) % 1000000007 + fib(n - 1) % 1000000007;
    }
}
