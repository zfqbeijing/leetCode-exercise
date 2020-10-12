package cn.com.zfq.arithmetic.prove_safety;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/***
 * @ClassName: ArrayRepeatNumber
 * @Description:
 * 剑指 Offer 03. 数组中重复的数字
 * 找出数组中重复的数字。
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 * 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 * 限制：
 *
 * 2 <= n <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/10/12 14:19
 * @Version: v1.0
 * @Modified By: 
 */
public class ArrayRepeatNumber {

    /**
     * 使用Set
     * 时间复杂度分析：O(N),数组的长度为N 遍历了一遍数组
     * 空间复杂度分析：O(N),引入了新的数据结构且他的使用也是N
     * <p>
     * 执行用时：8 ms, 在所有 Java 提交中击败了29.82%的用户
     * 内存消耗：47.7 MB, 在所有 Java 提交中击败了30.47%的用户
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        // 数组没有初始化或者数组的长度小于2直接返回
        if (nums == null || nums.length < 2) {
            return 0;
        }
        // 定义一个set集合来记录 数组中的元素，当存在重复的时候返回
        Set<Integer> set = new HashSet<>();
        // 定义一个接收重复元素的变量
        int repeat = 0;
        // 遍历数组
        for (int i : nums) {
            // 判断是否是重复元素
            if (set.contains(i)) {
                return i;
            }
            // 加入集合中
            set.add(i);
        }
        return repeat;
    }

    /**
     * 先对数组进行排序，如何对数组进行遍历（以时间换空间）
     * 时间复杂度分析：O(NlogN)，我们只对数组遍历了一遍即为N，但是考虑到排序的时间复杂度快排的O(NlogN)
     * 空间复杂度分析：O(logN)，虽然在这儿我们只使用了几个常量，但是还有考虑在实际开发中，我们使用排序的空间复杂度
     * <p>
     * 执行用时：3 ms, 在所有 Java 提交中击败了59.79%的用户
     * 内存消耗：46.3 MB, 在所有 Java 提交中击败了87.52%的用户
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber2(int[] nums) {
        // 数组没有初始化或者数组的长度小于2直接返回
        if (nums == null || nums.length < 2) {
            return 0;
        }
        // 对数组进行排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            // 判断两两之间是否是相等的
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return 0;
    }

    /**
     * 该方法只适用于数组长度为N 数字：0 ~ n
     * 时间复杂度分析：O(NlogN) 这儿值的是平均时间复杂度，当最坏的情况是O(N^2)
     * 空间复杂度分析：O(1)
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：46.6 MB, 在所有 Java 提交中击败了65.65%的用户
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber3(int[] nums) {
        // 定义一个临时变量
        int temp = 0;
        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 50, 2, 50, 3};
        System.out.println(new ArrayRepeatNumber().findRepeatNumber3(nums));
    }

}
