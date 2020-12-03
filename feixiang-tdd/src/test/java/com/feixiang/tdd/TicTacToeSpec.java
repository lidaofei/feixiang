package com.feixiang.tdd;

import com.feixiang.tdd.game.service.TicTacToe;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * 井字游戏TDD
 * @author lidaofei
 * @date 2019/12/23 23:48
 */
public class TicTacToeSpec {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    private TicTacToe ticTacToe;

    @Before
    public void getTicTacToe(){
        ticTacToe = new TicTacToe();
    }

    /**
     *  当x超出边界，返回运行时异常
     * 测试方法：play()
     */
    @Test(expected = RuntimeException.class)
    public void whenXOutsideBoardThenRuntimeException(){
        ticTacToe.play(5,2);
    }

    /**
     *  当y超出边界，返回运行时异常
     * 测试方法：play()
     */
    @Test
    public void whenYOutsideBoardThenRuntimeException(){
        expected.expect(RuntimeException.class);
        ticTacToe.play(2,5);
    }

    /**
     * 当该格已经被占据了，返回运行时异常
     */
    @Test(expected = RuntimeException.class)
    public void whenOccupiedThenRuntimeException(){
        ticTacToe.play(1,1);
        ticTacToe.play(1,1);
    }

    /**
     * 第一轮，当获取第一个玩家，返回k
     */
    @Test
    public void givenFirstTurnWhenNextPlayerThenK(){
        char player = ticTacToe.nextPlayer();
        assertEquals('k',player);
    }

    @Test
    public void givenLastTurnWasKWhenNextPlayerThenJ(){
        ticTacToe.play(1,1);
        char player = ticTacToe.nextPlayer();
        assertEquals('J',player);
    }

}
