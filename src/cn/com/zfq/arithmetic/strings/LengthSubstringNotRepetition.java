package cn.com.zfq.arithmetic.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/***
 * @ClassName: LengthSubstringNotRepetition
 * @Description:
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: 张枫琴
 * @Date: 2020/9/4 18:53
 * @Version: v1.0
 * @Modified By:
 *
 */
public class LengthSubstringNotRepetition {

    public static void main(String[] args) {
        LengthSubstringNotRepetition l = new LengthSubstringNotRepetition();
//        System.out.println("abcabcbb: " + l.lengthOfLongestSubstring("abcabcbb"));
//        System.out.println("bbbbb: " + l.lengthOfLongestSubstring("bbbbb"));
//        System.out.println("pwwkew: " + l.lengthOfLongestSubstring("pwwkew"));

        System.out.println("abcabcbb: " + l.lengthOfLongestSubstring2("abcabcbb"));
        System.out.println("bbbbb: " + l.lengthOfLongestSubstring2("bbbbb"));
        System.out.println("pwwkew: " + l.lengthOfLongestSubstring2("pwwkew"));

    }

    /**
     * 执行用时：8 ms, 在所有 Java 提交中击败了69.67%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了77.26%的用户
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 记录每一个字符是否重复过
        Set<Character> set = new HashSet<>();
        // 指针 表示向右移动的距离,表示距左边的距离
        int r = -1;
        // 字符串的长度
        int n = s.length();
        // 记录最长的
        int max = 0;
        for (int i = 0; i < n; i++) {
            // 当左指针不等于0的时候移除最左边的字符串
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            // 窗口进行滑动
            while (r + 1 < n && !set.contains(s.charAt(r + 1))) {
                // 指针不断的向右滑动，不断的添加
                set.add(s.charAt(r + 1));
                r++;
            }
            // 这是从i到r个字符串中最长的无重复的子串
            max = Math.max(max, r - i + 1);
        }

        return max;
    }

    /**
     * 执行用时：8 ms, 在所有 Java 提交中击败了69.67%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了71.15%的用户
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }

        // 记录每一个元素，且该元素的目前的位置
        Map<Character, Integer> map = new HashMap<>(16);
        // 指针 表示距左边的指针
        int l = 0;
        // 记录最长的
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            // 判断该元素在哈希表中是否存在，如果存在比较当前的和以前的谁大
            if (map.containsKey(s.charAt(i))){
               l = Math.max(l,map.get(s.charAt(i))+1);
            }
            // 添加到哈希表中
            map.put(s.charAt(i),i);
            // 计算当前窗口里面的 最大值和之前最大比较
            max = Math.max(max,i-l+1);
        }
        return max;
    }
}
