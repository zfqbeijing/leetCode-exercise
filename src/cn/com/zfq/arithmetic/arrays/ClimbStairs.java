package cn.com.zfq.arithmetic.arrays;

/***
 * @ClassName: ClimbStairs
 * @Description:
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 *   1 阶 + 1 阶
 *   2 阶
 *
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 *   1 阶 + 1 阶 + 1 阶
 *   1 阶 + 2 阶
 *   2 阶 + 1 阶
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/10/16 11:47
 * @Version: v1.0
 * @Modified By: 
 */
public class ClimbStairs {

    /**
     * 暴力递归
     * 时间复杂度分析：O(2^N)
     * 空间复杂度分析：O(N)
     * 超出时间限制
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n < 2) {
            return n;
        } else if (n == 2) {
            return 2;
        }

        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 循环的方法
     * 时间复杂度分析：O(N),遍历了N次
     * 空间复杂度分析：O(1)
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了98.32%的用户
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        // 如果n==0直接返回
        if (n == 0) {
            return 0;
        } else if (n <= 2) {
            return n;
        }
        // 定义求和值
        int res = 2;
        // 定义第二个初始值
        int pre = 1;
        // 定义第一个初始值
        int temp = 0;
        // 对数进行遍历
        for (int i = 3; i <= n; i++) {
            // 把当前的第n项作为下一次的第二项
            temp = res;
            // 计算当前第n项
            res = (temp + pre);
            // 把上一次的第二项作为下一次的第一项
            pre = temp;

        }
        return res;
    }

    /**
     * 使用矩阵求幂的方法
     * 时间复杂度分析：O(logN)
     * 空间复杂度分析：O(logN)，递归所需要的内存空间
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35 MB, 在所有 Java 提交中击败了99.67%的用户
     *
     * @param n
     * @return
     */
    public int climbStairs3(int n) {
        if (n < 1) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    private int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] temp = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = mullMatrix(res, temp);
            }
            temp = mullMatrix(temp, temp);
        }
        return res;
    }

    private int[][] mullMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j] % 1000000007;
                }
            }
        }
        return res;
    }

    /**
     * 黄金分割公式法
     * 黄金分割与斐波拉契数列的关系，也可以自己去论证
     * https://www.jianshu.com/p/51830aade104
     * https://www.jianshu.com/p/fa60955293bc
     * <p>
     * 时间复杂度分析：O(1) 常数的时间复杂度,也可以说是O(logN),因为pow()会用去这么多时间
     * 空间复杂度分析：O(1) 存储黄金分割率所使用的空间
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35 MB, 在所有 Java 提交中击败了99.83%的用户
     *
     * @param n
     * @return
     */
    public int climbStairs4(int n) {
        /**
         * Math.round(x,y) ：对数字进行四舍五入，后面可以指定几位小数,就是对x进行四舍五入保留y位小数，y可以不写
         * Math.pow(x,y) ：对x进行y次幂，并返回值
         * Math.sqrt(x) ：返回正确舍入的一个值的平根
         * (((1 + √5) / 2) ^ (n+1) - ((1 + √5) / 2) ^ (n+1)) / √5 对该值取整即可
         */
        return (int) ((Math.pow((1 + Math.sqrt(5)) / 2, n + 1)
                - Math.pow((1 - Math.sqrt(5)) / 2, n + 1))
                / Math.sqrt(5));
    }

    public static void main(String[] args) {
        System.out.println(new ClimbStairs().climbStairs4(3));
    }
}
