package cn.com.zfq.arithmetic.arrays;

/***
 * @ClassName: FibonacciSsequence
 * @Description:
 * 509. 斐波那契数 差别就是不需要取模
 * 剑指 Offer 10- I. 斐波那契数列
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 *
 * F(0) = 0, F(1)= 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：5
 *
 * 提示：
 *
 * 0 <= n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: 张枫琴
 * @Date: 2020/10/12 17:55
 * @Version: v1.0
 * @Modified By: 
 */
public class FibonacciSsequence {

    /**
     * 循环的方法
     * 时间复杂度分析：O(N),遍历了N次
     * 空间复杂度分析：O(1)
     * <p>
     * 执行用时：    0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了96.52%的用户
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        // 如果n==0直接返回
        if (n < 2) {
            return n;
        }
        // 定义求和值
        int sum = 0;
        // 定义第一个初始值
        int re = 0;
        // 定义第二个初始值
        int ter = 1;
        // 对数进行遍历
        for (int i = 0; i < n - 1; i++) {
            // 计算当前第n项
            sum = (ter + re);
            // 把上一次的第二项作为下一次的第一项
            re = ter;
            // 把当前的第n项作为下一次的第二项
            ter = sum;
        }

        return sum;
    }

    /**
     * 暴力递归
     * 时间复杂度分析：O(2^N)
     * 空间复杂度分析：O(N)
     * <p>
     * 执行用时：6 ms, 在所有 Java 提交中击败了29.42%的用户
     * 内存消耗：34.9 MB, 在所有 Java 提交中击败了99.95%的用户
     *
     * @param n
     * @return
     */
    public int fib2(int n) {
        // 如果小于2直接返回当前的n即可
        if (n < 2) {
            return n;
        }
        return fib(n - 2) + fib(n - 1);
    }

    /**
     * 使用矩阵求幂的方法
     * 时间复杂度分析：O(logN)
     * 空间复杂度分析：O(logN)，递归所需要的内存空间
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了94.10%的用户
     *
     * @param n
     * @return
     */
    public int fib3(int n) {
        if (n < 1) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
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
     * 时间复杂度分析：O(1) 常数的时间复杂度
     * 空间复杂度分析：O(1) 存储黄金分割率所使用的空间
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35 MB, 在所有 Java 提交中击败了99.83%的用户
     *
     * @param n
     * @return
     */
    public int fib4(int n) {
        /**
         * Math.round(x,y) ：对数字进行四舍五入，后面可以指定几位小数,就是对x进行四舍五入保留y位小数，y可以不写
         * Math.pow(x,y) ：对x进行y次幂，并返回值
         * Math.sqrt(x) ：返回正确舍入的一个值的平根
         * ((1 + √5) / 2) ^ n / √5 对该值四舍五入在取整即可
         */
        return (int) Math.round(Math.pow((1 + Math.sqrt(5)) / 2, n) / Math.sqrt(5));
    }


    public static void main(String[] args) {
        System.out.println(new FibonacciSsequence().fib(5));
        System.out.println(new FibonacciSsequence().fib4(5));
    }
}
