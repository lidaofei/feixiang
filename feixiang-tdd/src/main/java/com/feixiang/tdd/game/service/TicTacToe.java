package com.feixiang.tdd.game.service;

/**
 * @author lidaofei
 * @date 2019/12/24 22:20
 */
public class TicTacToe {

    public static final char INIT_VALUE = '0';
    private char[][] board;

    private char player;

    public TicTacToe() {
        this.board =
                {
                        {'0', '0', '0'},
                        {'0', '0', '0'},
                        {'0', '0', '0'}
                };
        player = 'K';
    }


    public void play(Integer x, Integer y) {
        test(x, "x越界");
        test(y, "y越界");

        if (board[x][y] != INIT_VALUE) {
            throw new RuntimeException("该格已经被占据");
        } else {
            board[x][y] = '1';
        }
    }

    private void test(Integer x, String message) {
        if (x <= 0 || x >= 5) {
            throw new RuntimeException(message);
        }
    }

    public char nextPlayer() {
        return 'k';
    }
}
