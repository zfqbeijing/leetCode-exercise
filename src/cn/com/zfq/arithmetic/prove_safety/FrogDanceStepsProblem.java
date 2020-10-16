package cn.com.zfq.arithmetic.prove_safety;

/***
 * @ClassName: FrogDanceStepsProblem
 * @Description:
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 * 70. 爬楼梯
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：1
 * 提示：
 *
 * 0 <= n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/10/12 18:53
 * @Version: v1.0
 * @Modified By: 
 */
public class FrogDanceStepsProblem {

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了84.18%的用户
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        // 如果n==0直接返回
        if (n == 0) {
            return 1;
        } else if (n <= 2) {
            return n;
        }
        // 定义求和值
        int res = 2;
        // 定义第二个初始值
        int pre = 1;
        // 定义第一个初始值
        int temp = 0;
        // 对数进行遍历
        for (int i = 3; i <= n; i++) {
            // 把当前的第n项作为下一次的第二项
            temp = res;
            // 计算当前第n项
            res = (temp + pre) % 1000000007;
            // 把上一次的第二项作为下一次的第一项
            pre = temp;

        }

        return res;
    }

    public int fib2(int n) {
        // 如果小于2直接返回当前的n即可
        if (n == 1 || n == 2) {
            return n;
        } else if (n == 0) {
            return 1;
        }
        return fib(n - 2) % 1000000007 + fib(n - 1) % 1000000007;
    }

    public static void main(String[] args) {
        System.out.println(new FrogDanceStepsProblem().fib(7));
    }
}
