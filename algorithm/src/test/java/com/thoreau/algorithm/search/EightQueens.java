package com.thoreau.algorithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 2023/4/23 22:21.
 *
 * @author zhaozhou
 */
public class EightQueens{
    private static final int BOARD_SIZE = 8; // 棋盘大小
    private static int[][] board = new int[BOARD_SIZE][BOARD_SIZE]; // 棋盘，0表示空，1表示皇后

    /**
     * 判断当前位置是否可以放置皇后
     */
    private static boolean canPlaceQueen(int row, int col) {
        // 检查列
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        // 检查左上角
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // 检查右上角
        for (int i = row - 1, j = col + 1; i >= 0 && j < BOARD_SIZE; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    /**
     * 放置皇后
     */
    private static boolean placeQueen(int row) {
        if (row == BOARD_SIZE) {
            // 找到一个解
            return true;
        }

        // 在当前行尝试放置皇后
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (canPlaceQueen(row, col)) {
                board[row][col] = 1;
                if (placeQueen(row + 1)) {
                    // 找到一个解
                    return true;
                }
                board[row][col] = 0; // 回溯
            }
        }

        return false;
    }

    /**
     * 打印棋盘
     */
    private static void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        if (placeQueen(0)) {
            printBoard();
        } else {
            System.out.println("无解");
        }
    }

}

