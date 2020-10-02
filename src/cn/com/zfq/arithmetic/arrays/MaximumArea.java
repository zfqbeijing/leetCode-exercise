package cn.com.zfq.arithmetic.arrays;

/***
 * @ClassName: MaximumArea
 * @Description:
 * 11. 盛最多水的容器(也可以说最大面积)
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点   (i,   ai) 。在坐标内画 n 条垂直线，
 * 垂直线 i   的两个端点分别为   (i,   ai) 和 (i, 0)。找出其中的两条线，使得它们与   x   轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且   n   的值至少为 2。
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为   49。
 *
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/10/2 22:55
 * @Version: v1.0
 * @Modified By: 
 */
public class MaximumArea {

    /**
     * 最简单的是两层for循环找出最大的一个
     * 时间复杂度分析：O(N^2), 首先外层循环一定走了一遍数组所以N，但是内层寻也走了数组虽然不是完整的数组，所以是N^2
     * 空间复杂度分析：O(1),只有几个临时变量没有引入新的数据结构
     * <p>
     * 执行用时：758 ms, 在所有 Java 提交中击败了5.02%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了41.21%的用户
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        // 记录最大面积
        int maxArea = 0;
        // 定义一个用来存储距离
        int num = 0;
        // 用来存储临时面积
        int temp = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = height.length - 1; j > i; j--) {
                // 记录宽 也就是记录两个值之间的距离
                num = j - i;
                // 进行计算 先从两个值中找出最小的那个值，如何更加距离计算面积
                temp = height[i] > height[j] ? height[j] * num : height[i] * num;
                // 判断当前面积是否比之前的最大面积大 如果大重新赋值
                if (temp > maxArea) {
                    maxArea = temp;
                }
            }
        }
        // 最后返回最大的
        return maxArea;
    }

    /**
     * 双指针法
     * 时间复杂度分析：O(N),一个从数据库的首开始，一个从数据库的尾开始，所以只遍历了一遍数组
     * 空间复杂度分析：O(1),没有引入新的数据结构
     * 执行用时：3 ms, 在所有 Java 提交中击败了92.87%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了87.76%的用户
     *
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        // 记录最大面积
        int maxArea = 0;
        // 用存储数组的首 已就是左边
        int left = 0;
        // 用来记录数组的尾，已就是数组的右边
        int right = height.length - 1;
        // 当右边小于等于左边的时候结束循环
        while (left < right) {
            // 计算面积 并找出当前的面积与最低面积进行比较，把大的赋值
            maxArea = height[left] > height[right] ?
                    Math.max(maxArea, (right - left) * height[right--]) :
                    Math.max(maxArea, (right - left) * height[left++]);
        }
        // 最后返回最大的
        return maxArea;
    }


    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new MaximumArea().maxArea(height));
        System.out.println(new MaximumArea().maxArea2(height));
    }
}
