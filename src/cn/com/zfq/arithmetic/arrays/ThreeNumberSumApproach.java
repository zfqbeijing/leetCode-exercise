package cn.com.zfq.arithmetic.arrays;

import java.util.Arrays;
import java.util.PriorityQueue;

/***
 * @ClassName: ThreeNumberSumApproach
 * @Description:
 * 16. 最接近的三数之和
 * 给定一个包括n 个整数的数组nums和 一个目标值target。找出nums中的三个整数，使得它们的和与target最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 *
 *
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 *
 * 提示：
 *
 * 3 <= nums.length <= 10^3
 * -10^3<= nums[i]<= 10^3
 * -10^4<= target<= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: 张枫琴
 * @Date: 2020/10/10 19:55
 * @Version: v1.0
 * @Modified By: 
 */
public class ThreeNumberSumApproach {
    /**
     * 使用排序加+双指针
     * 时间复杂度分析：O(N^2) 其中N为nums的长度，采用双指针遍历对数组遍历了N，但是第一个需要枚举所以又是N 所以是N^2
     * 我们在使用快排的时候的时间复杂度是O(NlogN)，但是 在这O(NlogN)<O(N^2)
     * 空间复杂度分析：O(logN) 虽然在这儿我们没有用到其他的数据结构，用的是常量，但是我们在使用Arrays.sort()的时候
     * 需要考虑到，快排的空间复杂度是O(logN)
     * <p>
     * 执行用时：7 ms, 在所有 Java 提交中击败了46.56%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了64.79%的用户
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        // 判空和判断数是否有三
        if (nums == null || nums.length < 3) {
            return 0;
        }
        // 对数组进行排序
        PriorityQueue<Integer> queue = new PriorityQueue(11);
        for (int i : nums) {
            queue.add(i);
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = queue.poll();
        }
        // 记录当前的最接近的值,防止做计算的时候越界
        int approach = Integer.MAX_VALUE / 2;
        // 记录左指针
        int left = 0;
        // 记录右指针
        int right = 0;
        // 遍历数组
        for (int i = 0; i < nums.length - 2; i++) {
            // 增加效率 相等的跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 给左指针赋值
            left = i + 1;
            // 给有指针赋值
            right = nums.length - 1;
            // 临时记录的值
            int temp = 0;
            while (right > left) {
                temp = nums[left] + nums[right] + nums[i];
                // 如果相等直接返回，本身时最接近的
                if (temp == target) {
                    return temp;
                }
                // 通过绝对值的大小，来判断是否有更新当前的最接近的值
                if (Math.abs(target - temp) < Math.abs(target - approach)) {
                    approach = temp;
                }
                /**
                 * 来判断指针的移动的方向 是左指针移动还是右指针移动
                 * 如果 temp > target 右指针向左移
                 * 如果 temp < target 左指针向右移
                 */
                if (temp > target) {
                    // 向对右指针向左移
                    right--;
                    // 如果右指针没有超过左指针的情况下，当出现同的数是进行跳过，增加效率
                    if (right > left && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else {
                    // 向对左指针向右移
                    left++;
                    // 如果左指针没有超过右指针的情况下，当出现同的数是进行跳过，增加效率
                    if (right > left && nums[left] == nums[left - 1]) {
                        left++;
                    }
                }
            }
        }
        return approach;
    }

    /**
     * 非我写 来源于LeetCode 夜殇
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.99%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了64.27%的用户
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int min=Integer.MAX_VALUE;
        int t=0;
        int k=nums.length-1,kt=nums[k]+nums[k-1];
        int index=0;
        while(index<nums.length-2&&nums[index]+kt<target){
            index++;
        }
        if(index== nums.length-2){
            return nums[index-1]+kt;
        }else if(index>0){
            if(target - kt - nums[index - 1]>kt + nums[index] - target){
                min=kt + nums[index] - target;
                t=kt + nums[index];
            }else{
                min=target - kt - nums[index - 1];
                t=target-min;
            }
        }
        for(int i=index;i<nums.length-2;i++){
            int ts=target-nums[i];
            if(nums[i+1]+nums[i+2]>ts){
                return nums[i+1]+nums[i+2]-ts<min?nums[i]+nums[i+1]+nums[i+2]:t;
            }
            for(int j=i+1;j!=k;){
                if(nums[j]+nums[k]>ts){
                    if((nums[k]+nums[j])-ts<min){
                        t=nums[i]+nums[k]+nums[j];
                        min=(nums[k]+nums[j])-ts;
                    }
                    k--;
                }else if(nums[j]+nums[k]<ts){
                    if(ts-(nums[k]+nums[j])<min){
                        t=nums[i]+nums[k]+nums[j];
                        min=ts-(nums[k]+nums[j]);
                    }
                    j++;
                }else{
                    return target;
                }
            }
            k=nums.length-1;
            while(i!=nums.length-2&&nums[i+1]==nums[i]){
                i++;
            }
        }
        return t;
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 5, 7, 3};

        System.out.println(new ThreeNumberSumApproach().threeSumClosest(nums, 13));
    }
}
