package cn.com.zfq.arithmetic.prove_safety;

/***
 * @ClassName: FindTwoArray
 * @Description:
 * 剑指 Offer 04. 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/10/12 14:56
 * @Version: v1.0
 * @Modified By: 
 */
public class FindTwoArray {

    /**
     * 暴力
     * 时间复杂度分析：O(NM) N和M分别为二维数组的行和列
     * 空复杂度分析：O(1)
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：44.7 MB, 在所有 Java 提交中击败了28.56%的用户
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        // 判断空
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        // 循环
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // 相等即返回
                if (target == matrix[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 优化
     * 时间复杂度分析：O(N + M) N和M分别为二维数组的行和列
     * 空复杂度分析：O(1)
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：44.5 MB, 在所有 Java 提交中击败了63.92%的用户
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        // 判断空
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        // 记录行的长度
        int row = matrix.length;
        // 临时记录行的变量
        int r = 0;
        // 临时记录列的变量
        int c = matrix[0].length - 1;
        // 循环
        while (r <= row && c >= 0) {
            // 临时记录当前num
            int num = matrix[r][c];
            /**
             * 当 target 与当前相等的时候返回
             * 小于的时候 列减一
             * 大于的时候 行加一
             */
            if (target == num) {
                return true;
            } else if (num > target) {
                c--;
            } else {
                r++;
            }
        }
        return false;
    }
}
