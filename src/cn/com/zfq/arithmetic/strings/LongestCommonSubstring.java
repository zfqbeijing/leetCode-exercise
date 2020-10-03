package cn.com.zfq.arithmetic.strings;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/***
 * @ClassName: LongestCommonSubstring
 * @Description:
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/10/3 20:32
 * @Version: v1.0
 * @Modified By: 
 */
public class LongestCommonSubstring {

    /**
     * 才有暴力的解法,先遍历数组(数组外层循环)
     * 时间复杂度分析：O(NM) 假设某一个字符串的长度是M，数组的长度是N，所以的平均时间复杂度是O(NM)
     * 空间复杂度分析：O(1) 在改方法中只使用了几个常量，没有使用到其他数据结构
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了88.40%的用户
     * 内存消耗：36.8 MB, 在所有 Java 提交中击败了77.57%的用户
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        // 判null
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 用来存储最长的子串，默认是取出第一个用来
        String s = strs[0];
        for (int i = 1; i < strs.length; i++) {
            // 找出两个字符串中较小的那个字符串长度
            int len = Math.min(s.length(), strs[i].length());
            // 定义下标来用来比较的时候遍历字符串
            int index = 0;
            // 计算两个字符串中最长的那个子串
            while (index < len && s.charAt(index) == strs[i].charAt(index)) {
                index++;
            }
            // 最后求出最长的那个子串
            s = s.substring(0, index);
        }

        return s;
    }

    /**
     * 才有暴力的解法,先遍历字符串(字符串外层循环)
     * 时间复杂度分析：O(NM) 假设某一个字符串的长度是M，数组的长度是N，所以的平均时间复杂度是O(NM)
     * 空间复杂度分析：O(1) 在改方法中只使用了几个常量，没有使用到其他数据结构
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了88.40%的用户
     * 内存消耗：36.8 MB, 在所有 Java 提交中击败了79.69%的用户
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs) {
        // 判null
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 记录 最长子串的长度，默认时候数组的第一个字符串的长度
        int sLen = strs[0].length();
        // 按照第一个字符串去遍历
        for (int i = 0; i < sLen; i++) {
            // 一个一个的查找数组中的每一个字符串是否都存在
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                /**
                 * 首先判断 i 是否等于当前数组中的字符串长度是否相等，如果相等则返回，因为当前就是最长的
                 * 其次判断当前 一个字符是否当前的字符串都是相等的，如果不相等这返回，说明已经找到最大的了
                 */
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }

        }
        return strs[0];
    }

    public static void main(String[] args) {
        String[] str = {"flower", "flow", "flight"};
        String[] s = {"dog", "racecar", "car"};
        System.out.println(new LongestCommonSubstring().longestCommonPrefix2(str));
        System.out.println(new LongestCommonSubstring().longestCommonPrefix2(s));
    }

    public List<List<Integer>> squeezeFastSolution(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // 加速1：c为非负数，就不能满足a+b+c=0了
            if (nums[i] > 0) {
                return result;
            }
            // 加速2：跳过计算过的数据，同时防止结果重复
            if (i != 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int head = i + 1;
            int tail = nums.length - 1;
            while (head < tail) {
                int sum = -(nums[head] + nums[tail]);
                if (sum == nums[i]) {
                    result.add(Arrays.asList(nums[i], nums[head], nums[tail]));
                    // 加速3：跳过计算过的数据，同时防止结果重复
                    while (head < tail && nums[head] == nums[head+1]) {
                        head++;
                    }
                    while (head < tail && nums[tail] == nums[tail-1]) {
                        tail--;
                    }
                }
                if (sum <= nums[i]) {
                    tail--;
                } else {
                    head++;
                }
            }
        }

        return result;
    }
}
