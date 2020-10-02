package cn.com.zfq.arithmetic.strings;

/***
 * @ClassName: RegularExpression
 * @Description:
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 *  示例 5:
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/10/2 21:23
 * @Version: v1.0
 * @Modified By: 
 */
public class RegularExpression {

    public static void main(String[] args) {
        RegularExpression re = new RegularExpression();
        System.out.println(re.isMatch("aa", "a"));
        System.out.println(re.isMatch("aa", "a*"));
        System.out.println(re.isMatch("ab", ".*"));
        System.out.println(re.isMatch("aab", "c*a*b"));
        System.out.println(re.isMatch("mississippi", "mis*is*p*."));
    }

    /**
     * 常数。
     */
    private static final char DOT = '.';

    /**
     * 常数*
     */
    private static final char STAR_NO = '*';

    public boolean isMatch(String s, String p) {
        // 就行特殊判null
        if (s == null || p == null) {
            return false;
        }
        // 记录s字符串的长度
        int sLen = s.length();
        // 记录p字符串的长度
        int pLen = p.length();
        // 定义一个二维数组用来接收动态规划的时候的匹配情况
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        // 默认第一个是true
        dp[0][0] = true;
        for (int i = 0; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                /**
                 * 判断p的前一个是不是等于* 如果等于*就判断在前一个
                 * 如果不等于就对当前的进行判断
                 */
                if (p.charAt(j - 1) == STAR_NO) {
                    // 判前面的结果给他
                    dp[i][j] = dp[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[sLen][pLen];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == DOT) {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

}
