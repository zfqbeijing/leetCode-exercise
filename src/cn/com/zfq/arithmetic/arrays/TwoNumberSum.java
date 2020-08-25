package cn.com.zfq.arithmetic.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/***
 *
 * @Author : 张枫琴
 * @Description
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Version : v1.0.0
 * @Date : Created in 2020/8/25 16:21
 * @Modified By :
 **/
public class TwoNumberSum {

    public static void main(String[] args) {

        StringBuffer sb = new StringBuffer("ahhh");
        System.out.println(sb);
        int[] arr = {0, 4, 3, 0};
        int target = 0;
        System.out.println(Arrays.toString(twoSumAndTwoFor(arr, target)));
        System.out.println(Arrays.toString(towSumAndCurtail(arr, target)));
        System.out.println(Arrays.toString(twoSumAndHashMap(arr, target)));
    }

    /**
     * 该方法减少代码量同时也减少了循环，但是增加了一种新的数据结构，哈希表
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.60%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了85.43%的用户
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumAndHashMap(int[] nums, int target) {
        int[] indexs = new int[2];
        if (nums == null || nums.length == 1) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                indexs[0] = map.get(nums[i]);
                indexs[1] = i;
                break;
            }
            map.put(target - nums[i], i);
        }
        return indexs;
    }

    /**
     * 才有的方法是数组进排序，然后从数组的两边进行向中间进行遍历，这样减少了一成循环，同时多增加了一个数组
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.60%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了65.73%的用户
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] towSumAndCurtail(int[] nums, int target) {
        int[] indexs = new int[2];
        if (nums == null || nums.length == 1) {
            return null;
        }
        // 多数组进行拷贝
        int[] arr = Arrays.copyOf(nums, nums.length);
        //排序
        Arrays.sort(arr);
        int l = 0;
        int r = arr.length - 1;
        while (true) {
            if (arr[l] + arr[r] == target) {
                break;
            }
            // 相加比target大说明 右边的数大 否则说明左边的数小
            else if (arr[l] + arr[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        int a = -1;
        int b = -1;
        for (int i = 0; i < nums.length; i++) {
            if (arr[l] == nums[i] && a == -1) {
                indexs[0] = i;
                a = 1;
            } else if (arr[r] == nums[i] && b == -1) {
                indexs[1] = i;
                b = 1;
            } else if (b == 1 && a == 1) {
                break;
            }
        }

        return indexs;
    }

    /**
     * 常用的方法两层循环
     * <p>
     * 执行用时：79 ms, 在所有 Java 提交中击败了21.01%的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了41.78%的用户
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumAndTwoFor(int[] nums, int target) {
        int[] arr = new int[2];
        if (nums == null || nums.length == 1) {
            return null;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    arr[0] = i;
                    arr[1] = j;
                }
            }
        }

        return arr;
    }
}
