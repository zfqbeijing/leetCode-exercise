package cn.com.zfq.arithmetic.strings;

/***
 * @ClassName: PalindromeNumber
 * @Description:
 * 9. 回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 示例 1:
 * 输入: 121
 * 输出: true
 *
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 进阶:
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/9/15 15:14
 * @Version: v1.0
 * @Modified By: 
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        PalindromeNumber p = new PalindromeNumber();
        System.out.println(p.isPalindrome2(12321));
        System.out.println(p.isPalindrome2(1221));
        System.out.println(p.isPalindrome2(123454322));
    }

    /**
     * 使用字符串的遍历进行判断
     * 时间复杂度：O(N),会遍历长度是N，也就是这个数的长度
     * 空间复杂度：O(N),字符串的大小空间是N
     * <p>
     * 执行用时：10 ms, 在所有 Java 提交中击败了67.77%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了60.18%的用户
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        // 转化为字符串
        String s = String.valueOf(x);
        // 定义一个从0开始的值
        int left = 0;
        // 定义一个从字符串长度减一的开始值
        int right = s.length() - 1;
        while (left < right) {
            // 遍历判断是否相等
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 进阶问题
     * 就是反转字符串的一半
     * 时间复杂度：O(logn) 对于每次迭代，我们会将输入除以 10，因此时间复杂度为 O(logn)。
     * 空间复杂度：O(1) 我们只需要常数空间存放若干变量。
     * <p>
     * 执行用时：9 ms, 在所有 Java 提交中击败了98.93%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了80.76%的用户
     *
     * @param x
     * @return
     */
    public boolean isPalindrome2(int x) {
        // x小于0的情况一定不是回文
        // x的最后一位为0看到也不是，但是x=0除外 0%10也定义0
        boolean b = x < 0 || (x % 10 == 0 && x != 0);
        if (b) {
            return false;
        }
        // 这里面放反转的那一半的数
        int num = 0;
        while (num < x) {
            // 对后面的那一半进行反转
            // 12321 一样 num = 1(0*10 + 12321%10)
            num = num * 10 + x % 10;
            // x也小于缩小10倍
            x /= 10;
        }
        // 这儿就是分别判断x是个数是奇数个还是偶数个的情况
        return x == num || x == num / 10;
    }


}
