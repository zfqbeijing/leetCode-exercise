package cn.com.zfq.arithmetic.strings;

/***
 * @ClassName: Atoi
 * @Description:
 * 8. 字符串转换整数 (atoi)
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要
 * 进行转换，即无法进行有效转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 *
 * 提示：
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。
 * 如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 * 示例 1:
 * 输入: "42"
 * 输出: 42
 *
 * 示例 2:
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 *
 * 示例 3:
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 *
 * 示例 4:
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 *      因此无法执行有效的转换。
 *
 * 示例 5:
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
 *      因此返回 INT_MIN (−231) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/9/15 13:43
 * @Version: v1.0
 * @Modified By: 
 */
public class Atoi {

    public static void main(String[] args) {
        String s = "1234";
        Atoi a = new Atoi();
        int i = a.myAtoi(s);
        System.out.println(i);
    }

    /**
     * 使用普通的遍历字符串的方法
     * 时间复杂度：O(N) 最长指遍历一遍字符串
     * 空间复杂度：O(1) 没有引入新的空间
     * <p>
     * 执行用时：3 ms, 在所有 Java 提交中击败了47.65%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了99.48%的用户
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        if (str == null || str.isEmpty()) {
            try {
                throw new Exception("不是数字类型");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 去除字符串前面的空格
        str = str.trim();
        // 判断全是空格的极端情况
        if (str.length() < 1) {
            return 0;
        }
        // 定义一个flag用来判断是正数还是负数
        int flag = 0;
        // 循环的下标
        int i = 0;
        // 当第一个字符是 '+'或者没有的时候是正数 flag状态是1或者0，当当第一个字符是 '-' 表示是负数 flag状态是-1
        if (str.charAt(0) == '+') {
            flag = 1;
            i = 1;
        } else if (str.charAt(0) == '-') {
            flag = -1;
            i = 1;
        }
        // 初始化一个num
        int num = 0;
        // int的最大临界值
        int max = Integer.MAX_VALUE;
        // int 的最小临界值
        int min = Integer.MIN_VALUE;
        // 循环字符串
        while (i < str.length()) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                // 判断是否超出最大的临界值
                boolean p = num > max / 10 || (num == max / 10 && str.charAt(i) > '7');
                // 判断是否超出最小的临界值
                boolean n = num > -(min / 10) || (num == -(min / 10) && str.charAt(i) > '7');
                // 超出最大临界值返回最大值
                if (flag != -1 && p) {
                    return max;
                }
                // 超出最小临界值返回最小临界值
                if (flag == -1 && n) {
                    return min;
                }
                // 计算当前的值
                num = num * 10 + (str.charAt(i) - '0');
                i++;
            } else {
                break;
            }
        }
        // 当时负数的情况返回负数
        if (flag == -1) {
            return flag * num;
        }
        // 默认是正数返回
        return num;
    }
}
