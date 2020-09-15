package cn.com.zfq.arithmetic.strings;

/***
 * @ClassName: ManacherLongest
 * @Description:
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/9/12 13:40
 * @Version: v1.0
 * @Modified By: 
 */
public class LongestManachers {


    /**
     * 暴力的解法
     * 时间复杂度：O(N^3)，这里 N 是字符串的长度，枚举字符串的左边界、右边界，
     * 然后继续验证子串是否是回文子串，这三种操作都与 NN 相关；
     * 空间复杂度：O(1)，只使用到常数个临时变量，与字符串长度无关。
     * <p>
     * 执行用时：565 ms, 在所有 Java 提交中击败了6.13%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了90.34%的用户
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null) {
            return null;
        }
        // 定义变量 存储字符串的长度
        int len = s.length();
        // 判断 字符串的长度是否小于2 如果小于直接返回该字符串
        if (len < 2) {
            return s;
        }
        // 最长回文子串的长度,当没有达到两个也上，就默认是第一个
        int maxLen = 1;
        // 记录最长回文子串的开始下
        int begin = 0;
        // 对字符串进行枚举
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                // 首先判断这个距离比当前这个回文子串的长度大，如果大,就进入方法查找是不是回文子串
                if (j - i + 1 > maxLen && validPalindrome(s, i, j)) {
                    // 如果当前是回文字符串 记录当前回文子串的长度
                    maxLen = j - i + 1;
                    // 并把下标记录当前下标
                    begin = i;
                }
            }
        }
        // 最后返回最长的
        return s.substring(begin, maxLen + begin);
    }

    /**
     * 判断字符串是不是回文子串
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    private boolean validPalindrome(String s, int left, int right) {
        // 一直循环判断
        while (left < right) {
            // 但出现不相等的是返回false
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 才有动态规划的方法进行就最长回文子串
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(N^2)，二维 dp 问题，一个状态得用二维有序数对表示，因此空间复杂度是 O(N^2)
     * <p>
     * 执行用时：170 ms, 在所有 Java 提交中击败了16.13%的用户
     * 内存消耗：43.4 MB, 在所有 Java 提交中击败了7.52%
     * 的用户
     *
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if (s == null) {
            return null;
        }
        // 定义变量 存储字符串的长度
        int len = s.length();
        // 用来记录当前是不是回文字符串
        boolean[][] dp = new boolean[len][len];
        // 用来记录目前最长的那个回文字符串
        String temp = "";
        // 对字符串进行枚举
        for (int n = 0; n < len; n++) {
            for (int i = 0; i + n < len; i++) {
                // 标识固定距离之间
                int j = i + n;
                // 单独一个字符串是一个回文字符串
                if (n == 0) {
                    dp[i][j] = true;
                } else {
                    // 记录当前和下一个字符串直接是不是回文字符串
                    boolean b = s.charAt(i) == s.charAt(j);
                    // 当外层循环的时候等于1的时候，就判断这两个字符串之间是否回文
                    if (n == 1) {
                        dp[i][j] = b;
                    } else {
                        // 其他情况的时候 相判断相邻两个之间是不是没如果是，在判断后两个直接是不是
                        dp[i][j] = (b && dp[i + 1][j - 1]);
                    }
                }
                if (dp[i][j] && n + 1 > temp.length()) {
                    temp = s.substring(i, i + n + 1);
                }
            }
        }
        return temp;
    }

    public String longestPalindrome3(String s) {
        if (s == null) {
            return null;
        }
        // 定义变量 存储字符串的长度
        int len = s.length();
        // 判断 字符串的长度是否小于2 如果小于直接返回该字符串
        if (len < 2) {
            return s;
        }
        // 最长回文子串的长度,当没有达到两个也上，就默认是第一个
        int maxLen = 1;
        // 默认记录是第一个
        String temp = s.substring(0, 1);
        for (int i = 0; i < len - 1; i++) {
            // 当中心位置是落在数上的时候，也就是说有奇数
            String oddStr = centerSpread(s, i, i);
            // 当中心不是不是数，也就是说表示是偶数
            String evneStr = centerSpread(s, i, i + 1);
            // 找出较长的那个
            String maxLenStr = oddStr.length() > evneStr.length() ? oddStr : evneStr;
            // 当前比上一个长就进行重新记录
            if (maxLenStr.length() > maxLen) {
                temp = maxLenStr;
                maxLen = maxLenStr.length();
            }
        }

        return temp;
    }

    /**
     * 向外部扩散 判断也当前数为中心的时候，最大的回文子串
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)，只使用到常数个临时变量，与字符串长度无关。
     * <p>
     * 执行用时：34 ms, 在所有 Java 提交中击败了76.45%的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了50.43%的用户
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    private String centerSpread(String s, int left, int right) {
        int len = s.length();
        while (left >= 0 && right < len) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return s.substring(left + 1, right);
    }

    /**
     * Manacher算法
     * 时间复杂度：O(n)，其中 n 是字符串的长度。由于对于每个位置，扩展要么从当前的最右侧臂长 right 开始，
     * 要么只会进行一步，而 right 最多向前走 O(n) 步，因此算法的复杂度为 O(n)。
     * 空间复杂度：O(n)，我们需要 O(n) 的空间记录每个位置的臂长。
     * <p>
     * 执行用时：11 ms, 在所有 Java 提交中击败了93.99%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了65.50%的用户
     *
     * @param s
     * @return
     */
    public String longestPalindrome4(String s) {
        // 特判
        if (s == null) {
            return null;
        }
        int len = s.length();
        // 判断 字符串的长度是否小于2 如果小于直接返回该字符串
        if (len < 2) {
            return s;
        }

        // 得到预处理字符串
        String str = addBoundaries(s, '#');
        // 新字符串的长度
        int sLen = 2 * len + 1;

        // 数组 p 记录了扫描过的回文子串的信息
        int[] p = new int[sLen];

        // 双指针，它们是一一对应的，须同时更新
        int maxRight = 0;
        int center = 0;

        // 当前遍历的中心最大扩散步数，其值等于原始字符串的最长回文子串的长度
        int maxLen = 1;
        // 原始字符串的最长回文子串的起始位置，与 maxLen 必须同时更新
        int start = 0;

        for (int i = 0; i < sLen; i++) {
            if (i < maxRight) {
                int mirror = 2 * center - i;
                // 这一行代码是 Manacher 算法的关键所在，要结合图形来理解
                p[i] = Math.min(maxRight - i, p[mirror]);
            }

            // 下一次尝试扩散的左右起点，能扩散的步数直接加到 p[i] 中
            int left = i - (1 + p[i]);
            int right = i + (1 + p[i]);

            // left >= 0 && right < sLen 保证不越界
            // str.charAt(left) == str.charAt(right) 表示可以扩散 1 次
            while (left >= 0 && right < sLen && str.charAt(left) == str.charAt(right)) {
                p[i]++;
                left--;
                right++;

            }
            // 根据 maxRight 的定义，它是遍历过的 i 的 i + p[i] 的最大者
            // 如果 maxRight 的值越大，进入上面 i < maxRight 的判断的可能性就越大，这样就可以重复利用之前判断过的回文信息了
            if (i + p[i] > maxRight) {
                // maxRight 和 center 需要同时更新
                maxRight = i + p[i];
                center = i;
            }
            if (p[i] > maxLen) {
                // 记录最长回文子串的长度和相应它在原始字符串中的起点
                maxLen = p[i];
                start = (i - maxLen) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }


    /**
     * 创建预处理字符串
     *
     * @param s      原始字符串
     * @param divide 分隔字符
     * @return 使用分隔字符处理以后得到的字符串
     */
    private String addBoundaries(String s, char divide) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        // 判断你传递的分割字符在字符串中是否存在
        if (s.indexOf(divide) != -1) {
            throw new IllegalArgumentException("参数错误，您传递的分割字符，在输入字符串中存在！");
        }
        // 定义一个SpringBuilder来创建一个带分割符的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历一个先添加一个分隔符遍历一个数的方式连接
        for (int i = 0; i < len; i++) {
            stringBuilder.append(divide);
            stringBuilder.append(s.charAt(i));
        }
        // 添加最后一个分割符
        stringBuilder.append(divide);
        // 转化为字符串返回
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LongestManachers max = new LongestManachers();
        String ac = max.longestPalindrome4("babad");
        System.out.println(ac);
    }
}
