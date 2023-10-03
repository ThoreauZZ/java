//按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
//
// n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
// 
// 
//
// 
//
// 示例 1： 
// 
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 
//
// Related Topics 数组 回溯 👍 1922 👎 0


package com.thoreau.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class L51_NQueens {
    public static void main(String[] args) {
        Solution solution = new L51_NQueens().new Solution();
        System.out.println(solution.solveNQueens(4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 回溯
         * 1. 一行一行放Q
         * 2. 如果某一个位置可以放，因为这一行都不会再放，递归下一行。
         *    递归出栈，把此位置设置为.,继续递归这一行不放的情况。
         * 3. 如果不能放，看这一行的其他列能不能放。
         * 4. 如果行遍历完成，row=n，递归完成，加入结果。
         */
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> solutions = new ArrayList<>();
            char[][] board = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = '.';
                }
            }
            backtrack(solutions, board, 0, n);
            return solutions;
        }

        private void backtrack(List<List<String>> solutions, char[][] board, int row, int n) {
            if (row == n) {
                solutions.add(generateSolution(board));
                return;
            }
            for (int col = 0; col < n; col++) {
                // 检查释放可以放Q，不可以就看其他列
                if (isSafe(board, row, col, n)) {
                    board[row][col] = 'Q';
                    // 这一行设置了Q，之间看下一行，在下一行中找可以放值的位置
                    backtrack(solutions, board, row + 1, n);
                    board[row][col] = '.';
                }
            }
        }

        /**
         * 检查：
         * 1. 检查此列是否可以放Q：因为是按行遍历，只需要检查<col的位置；
         * 2. 检查此行是否可以放Q：无需检查，因为一但放了Q，就递归检查下一行了。
         * 3. 检查做左上的斜线有没有；
         * 4. 检查做右上的斜线有没有；
         *
         */
        private boolean isSafe(char[][] board, int row, int col, int n) {
            for (int i = 0; i < row; i++) {
                if (board[i][col] == 'Q') {
                    return false;
                }
            }
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (board[i][j] == 'Q') {
                    return false;
                }
            }
            for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
                if (board[i][j] == 'Q') {
                    return false;
                }
            }
            return true;
        }

        private List<String> generateSolution(char[][] board) {
            List<String> solution = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                solution.add(new String(board[i]));
            }
            return solution;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}