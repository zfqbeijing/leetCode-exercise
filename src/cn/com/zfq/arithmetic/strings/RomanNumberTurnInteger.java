package cn.com.zfq.arithmetic.strings;

import java.util.HashMap;

/***
 * @ClassName: RomanNumberTurnInteger
 * @Description:
 * 13. 罗马数字转整数
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
 * 示例 1:
 * 输入: "III"
 * 输出: 3
 *
 * 示例 2:
 * 输入: "IV"
 * 输出: 4
 *
 * 示例 3:
 * 输入: "IX"
 * 输出: 9
 *
 * 示例 4:
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 *
 * 示例 5:
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/roman-to-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/10/3 0:01
 * @Version: v1.0
 * @Modified By: 
 */
public class RomanNumberTurnInteger {

    /**
     * 时间复杂度分析：O(1) 但是哈希表的大学是有限制的，相当于遍历一个定长的数据结构
     * 空间复杂度分析：O(1) 虽然引入的一个新的数据结构，但是大小在一个有限的范围
     * <p>
     * 执行用时：15 ms, 在所有 Java 提交中击败了6.89%的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了11.23%的用户
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        // 定义一个表对应的值
        HashMap<String, Integer> map = new HashMap<>(16);
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        // 用来求最后的值
        int num = 0;
        /**
         * 循环字符串
         * 当前这个字符的值比后一个值大的情况，直接加上当前值
         * 当前这个值比后一个值小的情况，加这个值的相反数
         */
        for (int i = 0; i < s.length() - 1; i++) {
            num += map.get(s.charAt(i) + "") >= map.get(s.charAt(i + 1) + "") ?
                    map.get(s.charAt(i) + "") : -map.get(s.charAt(i) + "");
        }
        // 加上最后一个
        num += map.get(s.charAt(s.length() - 1) + "");
        return num;
    }

    /**
     * 时间复杂度分析：O(1) 但是哈希表的大学是有限制的，相当于遍历一个定长的数据结构
     * 空间复杂度分析：O(1) 没有引入新的数据结构
     * <p>
     * 执行用时：5 ms, 在所有 Java 提交中击败了99.97%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了84.58%的用户
     *
     * @param s
     * @return
     */
    public int romanToInt2(String s) {
        // 判空
        if (s == null) {
            return 0;
        }
        //定义求前一个的值
        int current = getValue(s.charAt(0));
        // 求当前这个的值
        int after = 0;
        // 求累加和
        int num = 0;
        // 循环数组
        for (int i = 1; i < s.length(); i++) {
            // 求出当前这个的值
            after = getValue(s.charAt(i));
            /**
             * 如果当前这个比前一个小，这加上前一个
             * 如果当前这个比前一个大，这减去前一个
             */
            if (current >= after) {
                num += current;
            } else {
                num -= current;
            }
            current = after;
        }
        return num + current;
    }

    /**
     * 罗马数字与阿拉伯数字的对应表
     *
     * @param ch
     * @return
     */
    private int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(new RomanNumberTurnInteger().romanToInt("MCMXCIV"));
    }
}
