package cn.com.zfq.arithmetic.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/***
 * @ClassName: ThreeNumberSum
 * @Description:
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 * 请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/10/3 21:35
 * @Version: v1.0
 * @Modified By: 
 */
public class ThreeNumberSum {

    /**
     * 先对数组进行排序，然后对数组使用双指针方法进行判断
     * 时间复杂度分析：O(N^2) 其中 N 是数组 nums 的长度。
     * 空间复杂度分析：O(logN) 我们忽略存储答案的空间，额外的排序的空间复杂度为 O(logN)
     * <p>
     * 执行用时：19 ms, 在所有 Java 提交中击败了99.57%的用户
     * 内存消耗：42.7 MB, 在所有 Java 提交中击败了66.99%的用户
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        /**
         * 对数组进行判null
         * 当数组里面元素的个数已经小于3的时候，没有必要执行
         */
        if (nums == null || nums.length <= 2) {
            return Collections.emptyList();
        }
        // 定义一个集合用来接收最后求出的数据
        List<List<Integer>> result = new LinkedList<>();
        // 对数组进行排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            /**
             * 对数组进行排序 进行整理，当第一个就是大于0的数就 必要了肯定是大于0的
             * c为非负数，就不能满足a+b+c=0了
             */
            if (nums[i] > 0) {
                return result;
            }
            /**
             * 此时是 跳过计算过的数据，同时防止结果重复
             */
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 开始遍历时候的头指针
            int head = i + 1;
            // 开始遍历时候的尾指针
            int tail = nums.length - 1;
            // 把a固定 找出b和c的情况，当头指针大于等于尾指针的情况结束循环
            while (head < tail) {
                // 这儿 对头和尾进行了一个计算 并取反，这样相当于 计算一下 b+c 取反后直接判断和a是否相等就好，不用再去计算是否等于0
                int sum = -(nums[head] + nums[tail]);
                // 判断取反后的数是否和第一个相等
                if (sum == nums[i]) {
                    // 如何相等向里面追加
                    result.add(Arrays.asList(nums[i], nums[head], nums[tail]));
                    // 跳过计算过的数据，同时防止结果重复,同时头指针向右移动
                    while (head < tail && nums[head] == nums[head + 1]) {
                        head++;
                    }
                    // 跳过计算过的数据，同时防止结果重复,同时尾指针向左移动
                    while (head < tail && nums[tail] == nums[tail - 1]) {
                        tail--;
                    }
                }
                /**
                 * 当不相等的情况 判断b+c的值 判断是移动那个指针
                 */
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
