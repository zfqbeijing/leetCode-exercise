package cn.com.zfq.arithmetic.arrays;

import java.util.Arrays;

/***
 * @ClassName: SearchTwoArrayMedian
 * @Description:
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 *
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: 张枫琴
 * @Date: 2020/9/7 15:17
 * @Version: v1.0
 * @Modified By: 
 */
public class SearchTwoArrayMedian {

    public static void main(String[] args) {
        int[] num1 = {1, 2};
        int[] num2 = {3, 4};
        System.out.println("3------" + new SearchTwoArrayMedian().findMedianSortedArrays2(num1, num2));
    }

    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了68.57%的用户
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了83.04%的用户
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, nums, 0, nums1.length);
        System.arraycopy(nums2, 0, nums, nums1.length, nums2.length);
        Arrays.sort(nums);
        if (nums.length % 2 == 0) {
            int num = nums[nums.length / 2 - 1] + nums[nums.length / 2];
            return num / 2.0;
        } else {
            return nums[nums.length / 2];
        }
    }

    /**
     * 理解有误 我理解成时间复杂性是O(n+m)了，代码是正确的但是时间复杂度过高
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 用来记录当前到第几个数了，也就是循环几次了
        int count = 0;
        // 记录nums1 的下标
        int l1 = 0;
        // 记录nums2 的小标
        int l2 = 0;
        // 记录num1 的长度
        int sum1 = 0;
        // 记录num2 的长度
        int sum2 = 0;
        // 一个标识 如果是0 表示当前是nums1，如果是1就是nums2
        int flag = -1;
        // 当时偶数的时候，记录第一个数
        int num = 0;
        // 用来判断是第几次
        int bool = 1;
        // 进行循环
        while (true) {
            // 用来判断nums1数组是否为空 或者是否已经超出当前数组的长度
            boolean a = (nums1 != null && l1 < (sum1 = nums1.length)) ? true : false;
            // 用来记录nums2数组是否为空 或者是否已经超出当前数组的长度
            boolean b = (nums2 != null && l2 < (sum2 = nums2.length)) ? true : false;
            // 只有当都满足的时候才进行
            if (a && b) {
                // 判断对两个数组进行判断，那个数组较小的下标加一,然后并标识当前是哪个数组
                if (nums1[l1] < nums2[l2]) {
                    l1++;
                    flag = 0;
                } else {
                    l2++;
                    flag = 1;
                }
                // 计数加一
                count++;
            } else if (a) {
                l1++;
                flag = 0;
            } else if (b) {
                l2++;
                flag = 1;
            }

            // 记录当前平均数
            int average = (sum1 + sum2) / 2;
            boolean lean = (average == count || average + 1 == count);
            // 判断是一个中位数还是两个中位数，如果是一个直接取出返回，如果是两个取出分别相加除2
            if (((sum1 + sum2) % 2) == 0 && lean) {
                // 第二次的时候计算后就返回
                if ((flag == 0) && (bool == 2)) {
                    return (num + nums1[l1 - 1]) / 2.0;
                } else if (bool == 2) {
                    return (num + nums2[l2 - 1]) / 2.0;
                }

                // 当第一次记录第一个数
                if (flag == 0 && bool == 1) {
                    num = nums1[l1 - 1];
                    bool = 2;
                } else if (bool == 1) {
                    num = nums2[l2 - 1];
                    bool = 2;
                }

            } else if ((sum1 + sum2) / 2 == (count - 1)) {
                if (flag == 0) {
                    return nums1[l1 - 1];
                } else {
                    return nums2[l2 - 1];
                }
            }
        }
    }
}
