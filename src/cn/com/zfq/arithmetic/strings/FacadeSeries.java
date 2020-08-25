package cn.com.zfq.arithmetic.strings;

/***
 *
 * @Author : 张枫琴
 * @Description
 *  给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 *  注意：整数序列中的每一项将表示为一个字符串。
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 *      1.     1
 *      2.     11
 *      3.     21
 *      4.     1211
 *      5.     111221
 *  第一项是数字 1
 *  描述前一项，这个数是 1 即 “一个 1 ”，记作 11
 *  描述前一项，这个数是 11 即 “两个 1 ” ，记作 21
 *  描述前一项，这个数是 21 即 “一个 2 一个 1 ” ，记作 1211
 *  描述前一项，这个数是 1211 即 “一个 1 一个 2 两个 1 ” ，记作 111221
 *
 *  示例 1:
 *      输入: 1
 *      输出: "1"
 *      解释：这是一个基本样例。
 *
 *  示例 2:
 *      输入: 4
 *      输出: "1211"
 *      解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也就是出现频次 = 1 而 值 = 2；
 *          类似 "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-and-say
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Version : v1.0.0
 * @Date : Created in 2020/8/25 17:58
 * @Modified By : 
 **/
public class FacadeSeries {

    public static void main(String[] args) {
//        System.out.println(countAndSay(5));
        countAndSayRecursion(5);
    }

    /**
     * 采用原始双重for循环来实现了，但是他的效率非常慢
     * <p>
     * 执行用时：12 ms, 在所有 Java 提交中击败了24.88%的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了18.36%的用户
     *
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        if (n < 0 && n > 30) {
            return null;
        }
        String s = "1";
        for (int i = 0; i < n - 1; i++) {
            s = countSay(new StringBuffer(s)).toString();
        }
        return s;
    }

    public static StringBuffer countSay(StringBuffer s) {
        int count = 0;
        char c = s.charAt(0);
        // 创建一个StringBuffer 方便想后面追加
        StringBuffer newS = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            // 统计相同的出现过几次
            if (c == s.charAt(i)) {
                count++;
            }
            // 当出现不同的时候先追加进去，如何在统计新出现的
            else if (c != s.charAt(i)) {

                int j = c - '0';
                String str = count + "" + j;
                newS.append(str);
                count = 1;
                c = s.charAt(i);
            }
        }
        // 统计最后的那几次
        int j = c - '0';
        String str = count + "" + j;
        newS.append(str);
        return newS;
    }

    /**
     * 递归
     * <p>
     * 执行用时：6 ms, 在所有 Java 提交中击败了29.22%的用户
     * 内存消耗：37 MB, 在所有 Java 提交中击败了63.33%的用户
     *
     * @param n
     * @return
     */
    public static String countAndSayRecursion(int n) {

        // * n = 1时，自动返回 "1"
        if (n == 1) {
            return "1";
        }

        // * 创建返回字符串
        StringBuffer s = new StringBuffer();

        // * 递归 -> 获取上一个字符串
        String pStr = countAndSayRecursion(n - 1);

        // * 用于记录每个字符的开始下标
        int begin = 0;

        // * 遍历上一个字符串，得到该字符串
        for (int i = 1; i <= pStr.length(); i++) {
            // * 碰到前后不相同的字符，进行计算
            if (pStr.charAt(i - 1) != pStr.charAt(begin)) {
                // * 计算相同字符的个数
                s.append(i - begin - 1);
                // * 写入上一字符串字符
                s.append(pStr.charAt(begin));
                // * 将新下标赋给 begin
                begin = i - 1;
            }
            // * 到达最后一位，直接加入末尾
            if (i == pStr.length()) {
                // * 计算相同字符的个数
                s.append(i - begin);
                // * 写入上一字符串字符
                s.append(pStr.charAt(begin));
            }
        }
        System.out.println("s：" + s.toString());
        return s.toString();
    }


}
