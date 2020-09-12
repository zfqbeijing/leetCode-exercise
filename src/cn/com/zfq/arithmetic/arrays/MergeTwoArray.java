package cn.com.zfq.arithmetic.arrays;

import java.util.Arrays;

/***
 * @ClassName: MergeTwoArray
 * @Description:
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: 张枫琴
 * @Date: 2020/9/7 17:27
 * @Version: v1.0
 * @Modified By: 
 */
public class MergeTwoArray {

    /**
     * 使用API实现
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了23.24%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了77.36%的用户
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    /**
     * 才有双指针 从后向前插的方法
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了99.08%的用户
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        // 定义nums1的指针
        int p1 = m - 1;
        // 定义nums2的指针
        int p2 = n - 1;
        // 用来存入的时候的新指针nums1的新指针
        int p = m + n - 1;
        // 对数组进行遍历
        while (p1 >= 0 && p2 >= 0) {
            // 比较两个数组中的元素 并在nums1中添加大的那个
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }
        // 从nums2中添加丢失的元素
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {2, 5, 6};
        new MergeTwoArray().merge2(nums1, nums1.length, nums2, nums2.length);
    }
}
