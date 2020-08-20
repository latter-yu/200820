import java.util.HashMap;
import java.util.Map;

public class Main {
    public int cutRope(int target) {
        // 给你一根长度为 n 的绳子，请把绳子剪成整数长的 m 段（m、n 都是整数，n > 1 并且 m > 1，m <= n）
        // 每段绳子的长度记为 k[1], ... ,k[m]。
        // 请问 k[1] x ... x k[m] 可能的最大乘积是多少？
        // 例如，当绳子的长度是 8 时，我们把它剪成长度分别为 2、3、3 的三段，此时得到的最大乘积是 18。

        // 输入描述:
        // 输入一个数 n，意义见题面。（2 <= n <= 60）

        // 示例:
        // 输入
        // 8
        // 输出
        // 18

        /**
         * 题目分析：
         * 先举几个例子，可以看出规律来。
         * 4 ： 2*2
         * 5 ： 2*3
         * 6 ： 3*3
         * 7 ： 2*2*3 或者4*3
         * 8 ： 2*3*3
         * 9 ： 3*3*3
         * 10：2*2*3*3 或者4*3*3
         * 11：2*3*3*3
         * 12：3*3*3*3
         * 13：2*2*3*3*3 或者4*3*3*3
         *
         * 下面是分析：
         * 首先判断 k[0] 到 k[m] 可能有哪些数字，实际上只可能是 2 或者 3。
         * 当然也可能有 4，但是 4 = 2 * 2，我们就简单些不考虑了。
         * 5 < 2 * 3, 6 < 3 * 3,比 6 更大的数字我们就更不用考虑了，肯定要继续分。
         * 其次看 2 和 3 的数量，2 的数量肯定小于 3 个，因为 2 * 2 * 2 < 3 * 3。
         * 由于题目规定 m > 1，所以 2 只能是 1 * 1，3 只能是 2 * 1，这两个特殊情况直接返回就行了。
         */
        if (target == 2 || target == 3) {
            return target - 1;
        }
        int ret = 1;
        while (target > 4) {
            // 当 target = 4 时, 2 * 2 > 3 * 1
            // 因此不用再剪了
            target -= 3;
            ret *= 3;
        }
        return ret * target;
    }

    public int movingCount(int threshold, int rows, int cols) {
        // 地上有一个 m 行和 n 列的方格。
        // 一个机器人从坐标 0, 0 的格子开始移动，每一次只能向左，右，上，下四个方向移动一格
        // 但是不能进入行坐标和列坐标的数位之和大于 k 的格子。
        // 例如，当 k 为 18 时，机器人能够进入方格（35, 37），因为 3 + 5 + 3 + 7 = 18。
        // 但是，它不能进入方格（35, 38），因为 3 + 5 +  + 8 = 19。
        // 请问该机器人能够达到多少个格子？

        boolean[][] visit = new boolean[rows][cols]; // 记录被访问过的坐标
        return count(threshold, rows, cols, 0, 0, visit);
    }
    public int count(int k, int m, int n, int row, int col, boolean[][] visit) {
        if (row < 0 || row >= m || col < 0 || col >= n || Sum(row) + Sum(col) > k || visit[row][col]) {
            return 0;
        }
        // 标记表示已被访问过
        visit[row][col] = true;
        return count(k, m, n, row - 1, col, visit) +
               count(k, m, n, row, col - 1, visit) +
               count(k, m, n, row + 1, col, visit) +
               count(k, m, n, row, col + 1, visit) + 1;
               // 1 表示当前节点
    }
    public int Sum(int n) {
        // 计算 row 或 col 的和
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}

class Solution {
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        // 在一个长度为n的数组里的所有数字都在 0 到 n - 1 的范围内。
        // 数组中某些数字是重复的，但不知道有几个数字是重复的。
        // 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
        // 例如，如果输入长度为 7 的数组 {2, 3, 1, 0, 2, 5, 3}，那么对应的输出是第一个重复的数字 2

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (!map.containsKey(numbers[i])) {
                map.put(numbers[i], 1);
            } else {
                duplication[0] = numbers[i];
                return true;
            }
        }
        return false;
    }
}
