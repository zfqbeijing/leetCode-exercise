package cn.com.zfq.arithmetic.strings;

/***
 * @ClassName: IntegerTurnRomanNumber
 * @Description:
 * 12. 整数转罗马数字
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，
 * 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 *
 * 示例 1:
 * 输入: 3
 * 输出: "III"
 *
 * 示例 2:
 * 输入: 4
 * 输出: "IV"
 *
 * 示例 3:
 * 输入: 9
 * 输出: "IX"
 *
 * 示例 4:
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 *
 * 示例 5:
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-to-roman
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: 张枫琴
 * @Date: 2020/10/2 23:36
 * @Version: v1.0
 * @Modified By: 
 */
public class IntegerTurnRomanNumber {

    /**
     * 定义一个罗马字组合
     */
    private final static String[] KEYS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * 罗马简单组合表对应的值
     */
    private final static int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    /**
     * 时间复杂度分析：O(1) 但是数组的长度是有限制的，
     * 空间复杂度分析：O(1) 只用到一个字符串只是向后追加
     * <p>
     * 执行用时：4 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了58.53%的用户
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        // 定义一个字符串用来接收最后的数字
        StringBuilder sb = new StringBuilder();
        // 循环数组
        for (int i = 0; i < VALUES.length && num >= 0; i++) {
            /**
             * 对num进行判断当大于的时候，键当前最大的数，并把对应的罗马追加给字符串，
             * 当小于的时候 在比num小的最大的数
             */
            while (num >= VALUES[i]) {
                // 减一个当前的最大值
                num -= VALUES[i];
                // 把对应的罗马追加上去
                sb.append(KEYS[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new IntegerTurnRomanNumber().intToRoman(58));
    }
}