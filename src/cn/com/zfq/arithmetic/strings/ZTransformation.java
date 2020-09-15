package cn.com.zfq.arithmetic.strings;

/***
 * @ClassName: ZTransformation
 * @Description:
 * 6. Z 字形变换
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 *
 * 示例 2:
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/9/13 14:21
 * @Version: v1.0
 * @Modified By: 
 */
public class ZTransformation {


    /**
     * 使用一个一维数组，来记录每一个字符应该放在什么地方
     * 时间复杂度：O(N),因为是只是遍历了长度为n的s字符串长度
     * 空间复杂度：O(N),因为只是引入了一个数组且该数组的长度最多是n
     *
     * 执行用时：15 ms, 在所有 Java 提交中击败了27.17%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了89.58%的用户
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (s == null) {
            return null;
        }
        // 当时numRows=1的时候直接返回即可
        if (numRows == 1) {
            return s;
        }
        // 定义一个StringBuffer类型的数组数组长度是这个行数
        StringBuffer[] strs = new StringBuffer[numRows];
        // 给数组中的值都默认初始值是""
        for (int i = 0; i < strs.length; i++) {
            strs[i] = new StringBuffer("");
        }
        // 定义一个值记录数组的下标1
        int num = 0;
        // 定义一个flag来判断是加一还是减一,默认是加一
        boolean falg = false;

        // 遍历字符串
        for (int i = 0; i < s.length(); i++) {
            // 遍历的字符串进行判断，追加在数组中对应下标的字符串的后面
            if (num < strs.length - 1 && (num == 0 || falg)) {
                // 进行字符串的追加
                strs[num] = strs[num].append(s.substring(i, i + 1));
                falg = true;
                // 数组下标进行加一
                num++;
            } else if (num == strs.length - 1 || !falg) {
                strs[num] = strs[num].append(s.substring(i, i + 1));
                falg = false;
                // 数组下标进行减一
                num--;
            }
        }

        // 定义一个str接收新的字符串
        String str = "";
        // 遍历数组
        for (int i = 0; i < strs.length; i++) {
            str += strs[i];
        }
        return str;
    }

    public static void main(String[] args) {
        ZTransformation z = new ZTransformation();

        String s = z.convert("AB", 2);
//           "LCIRETOESIIGEDHN"
        System.out.println(s);
    }
}
