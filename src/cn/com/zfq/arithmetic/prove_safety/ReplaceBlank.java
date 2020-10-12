package cn.com.zfq.arithmetic.prove_safety;

/***
 * @ClassName: ReplaceBlank
 * @Description:
 * 剑指 Offer 05. 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * 示例 1：
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/10/12 15:23
 * @Version: v1.0
 * @Modified By: 
 */
public class ReplaceBlank {

    /**
     * 时间复杂度分析：O(N) 遍历了一遍字符串的长度
     * 空间复杂度分析：O(N) 重新引入一个字符串，同时对该字符串进行了操作，长度为N
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了43.03%的用户
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        // 判空
        if (s == null || s.length() <= 0) {
            return s;
        }
        // 定义一个新的字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            // 当有空时候追加%20，否则追加当前的
            if (" ".equals(s.substring(i, i + 1))) {
                sb.append("%20");
            } else {
                sb.append(s.substring(i, i + 1));
            }
        }
        return sb.toString();
    }

    /**
     * API
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了66.11%的用户
     *
     * @param s
     * @return
     */
    public String replaceSpace2(String s) {
        return s.replace(" ", "%20");
    }

    public static void main(String[] args) {
        System.out.println(new ReplaceBlank().replaceSpace("We are happy."));
    }
}
