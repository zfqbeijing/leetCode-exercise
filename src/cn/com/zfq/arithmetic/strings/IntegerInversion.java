package cn.com.zfq.arithmetic.strings;

/***
 * @ClassName: IntegerInversion
 * @Description:
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 示例 1:
 * 输入: 123
 * 输出: 321
 *
 * 示例 2:
 * 输入: -123
 * 输出: -321
 *
 * 示例 3:
 * 输入: 120
 * 输出: 21
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/9/13 19:24
 * @Version: v1.0
 * @Modified By: 
 */
public class IntegerInversion {

    /**
     * 相当于对弹出一个数检查一个数
     * 时间复杂度：O(log(x))
     * 空间复杂度：O(1)。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了94.99%的用户
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        // 定义一个数来接收这个反转的数
        int rs = 0;
        // int的边界值
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        // 循环对这个数进行运算
        while (x != 0) {
            // 求出这个X的最后一位
            int pop = x % 10;
            // 对这个数缩小10倍
            x /= 10;
            // 当前rs和最大值的10去判断 如果比这个数大直接返回0不存在这个数，当前这个数和214748364相等的时候
            // 这个时候在去判断这个数是不是比7大如果大直接返回0
            if (rs > max / 10 || (rs == max / 10 && pop > 7)) {
                return 0;
            }
            // 同理
            if (rs < min / 10 || (rs == min / 10 && pop < -8)) {
                return 0;
            }
            rs = rs * 10 + pop;
        }
        return rs;
    }

    public static void main(String[] args) {
        IntegerInversion ii = new IntegerInversion();
        //v2147483648
        int reverse = ii.reverse(1463847412);
        System.out.println(reverse);
    }
}
