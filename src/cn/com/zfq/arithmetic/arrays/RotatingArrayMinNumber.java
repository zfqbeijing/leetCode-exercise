package cn.com.zfq.arithmetic.arrays;

import java.util.Arrays;

/***
 * @ClassName: RotatingArrayMinNumber
 * @Description:
 * 剑指 Offer 11. 旋转数组的最小数字
 * 154. 寻找旋转排序数组中的最小值 II
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 *
 * 示例 1：
 * 输入：[3,4,5,1,2]
 * 输出：1
 *
 * 示例 2：
 * 输入：[2,2,2,0,1]
 * 输出：0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/10/15 19:05
 * @Version: v1.0
 * @Modified By: 
 */
public class RotatingArrayMinNumber {

    /**
     * 使用API排序后取第一个值
     * 时间复杂度分析：O(NlogN),为排序的时间复杂度
     * 空间复杂度分析：O(N) 为快排的空间复杂度
     * <p>
     * 执l行用时：1 ms, 在所有 Java 提交中击败了45.37%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了99.49%的用户
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        Arrays.sort(numbers);
        return numbers[0];
    }

    /**
     * 二分查找，该数组是分为两部分有序的找到从哪儿开始不是有序的即可
     * 时间复杂度分析：O(logN)
     * 空间复杂度分析：O(1)
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了99.80%的用户
     *
     * @param numbers
     * @return
     */
    public int minArray2(int[] numbers) {
        // 判空
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        // 记录最左边的节点
        int left = 0;
        // 记录最右边的节点
        int right = numbers.length - 1;
        // 记录中间的下标
        int centre = 0;
        // 遍历数组
        while (left < right) {
            centre = left + (right - left) / 2;
            if (numbers[centre] < numbers[right]) {
                // 把右边缩小到中间
                right = centre;
            } else if (numbers[centre] > numbers[right]) {
                // 把左边缩小的中间
                left = centre + 1;
            } else {
                // 当相等的情况左边加一或者右边减一
                right--;
            }
        }
        return numbers[left];
    }

    public int minArray3(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        int min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i - 1] > numbers[i]) {
                return numbers[i];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        RotatingArrayMinNumber min = new RotatingArrayMinNumber();
        int[] nums = {2, 2, 2, 0, 1};
        System.out.println(min.minArray3(nums));
    }
}
