package cn.com.zfq.arithmetic.strings;

import java.util.ArrayList;
import java.util.List;

/***
 * @ClassName: PhoneLetterCombination
 * @Description:
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: 张枫琴
 * @Date: 2020/10/10 22:37
 * @Version: v1.0
 * @Modified By: 
 */
public class PhoneLetterCombination {

    /**
     * 时间复杂度分析：O(3^M * 4^N)
     * 空间复杂度分析：O(M + N)
     * <p>
     * 执行用时：4 ms, 在所有 Java 提交中击败了36.70%的用户
     * 内存消耗：37.4 MB, 在所有 Java 提交中击败了92.25%的用户
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        // 定义一个字符串的字典
        List<String> dicList = new ArrayList<>();
        // 接收生成的字符串的集合
        List<String> list = new ArrayList<>();
        // 判空
        if (digits == null || digits.length() < 1) {
            return null;
        }
        dicList.add("abc");
        dicList.add("def");
        dicList.add("ghi");
        dicList.add("jkl");
        dicList.add("mno");
        dicList.add("pqrs");
        dicList.add("tuv");
        dicList.add("wxyz");
        backtrack(list, dicList, digits, 0, new StringBuffer());
        return list;
    }

    /**
     * 使用该方法进行递归
     *
     * @param list
     * @param dicList
     * @param digits
     * @param index
     * @param sb
     */
    public void backtrack(List<String> list, List<String> dicList, String digits, int index,
                          StringBuffer sb) {
        /**
         * 当前的坐标等于字符串长度的时候接收递归
         */
        if (index == digits.length()) {
            list.add(sb.toString());
        } else {
            // 取出当前的字符
            char digit = digits.charAt(index);
            // 找出字符串对应的字符串
            String let = dicList.get(Integer.parseInt(digit + "") - 2);
            // 求出字符串对应的长度
            int lettersCount = let.length();
            // 遍历字符串
            for (int i = 0; i < lettersCount; i++) {
                // 加入当前字符
                sb.append(let.charAt(i));
                // 递归
                backtrack(list, dicList, digits, index + 1, sb);
                // 移除当前的字符串
                sb.deleteCharAt(index);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(new PhoneLetterCombination().letterCombinations("23"));
    }
}
