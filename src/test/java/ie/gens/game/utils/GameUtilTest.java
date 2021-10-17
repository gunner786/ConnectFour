package ie.gens.game.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ie.gens.game.constant.GameConstants;
import ie.gens.game.model.Player;

import java.util.*;

import nl.altindag.log.LogCaptor;

@RunWith(SpringJUnit4ClassRunner.class)
public class GameUtilTest {
	
	@InjectMocks
	private GameUtil gameUtil;
	
	private LogCaptor logCaptor;
	
	private List<String> players= new ArrayList<>();
	
	private Player player;
	
	@Before
	public void setUp() {
		player = new Player();
		GameUtil.makeGame();
		logCaptor = LogCaptor.forClass(GameUtil.class);
	}
	
	@Test
	public void testMakeGame() {
		GameUtil.makeGame();
		assertThat(logCaptor.getInfoLogs()).containsExactly("Game board is created");
	}
	
	@Test
	public void testBusinessRules2Players() {
		players.add("John");
		players.add("Pat");
		GameUtil.businessRules(players, "John");
		assertThat(logCaptor.getInfoLogs()).containsExactly("Both players have joined game");
		
	}
	
	@Test
	public void testBusinessRules1Player() {
		players.add("John");
		GameUtil.businessRules(players, "John");
	}
	
	@Test
	public void testDropX() {
		GameUtil.DropX(player, 10);
		assertThat(logCaptor.getErrorLogs()).containsExactly("That's not a valid column");
	}
	
	@Test
	public void testDropY() {
		GameUtil.DropO(player, 10);
		assertThat(logCaptor.getErrorLogs()).containsExactly("That's not a valid column");
	}
	
	@Test
	public void testCheckXDiagonalForwardFalse() {
		GameConstants.board[1][0] = "X";
		GameConstants.board[2][0] = "X";
		GameConstants.board[3][0] = "X";
		GameConstants.board[4][0] = "X";
		GameConstants.board[5][0] = "X";
		GameUtil.checkXDiagonalForward();
		assertFalse(GameUtil.checkXDiagonalForward());
	}
	
	@Test
	public void testCheckODiagonalForward() {
		GameConstants.board[1][0] = "X";
		GameConstants.board[2][0] = "O";
		GameConstants.board[3][0] = "X";
		GameConstants.board[4][0] = "X";
		GameConstants.board[5][0] = "O";
		assertFalse(GameUtil.CheckODiagonalForward());
	}
	
	@Test
	public void testCheckXDiagonalBack() {
		GameConstants.board[1][0] = "X";
		GameConstants.board[0][0] = "O";
		GameConstants.board[0][2] = "X";
		GameConstants.board[4][0] = "X";
		GameConstants.board[5][0] = "O";
		assertFalse(GameUtil.checkXDiagonalBack());
	}
	

	@Test
	public void testCheckODiagonalBack() {
		GameConstants.board[1][0] = "X";
		GameConstants.board[0][0] = "O";
		GameConstants.board[0][2] = "X";
		GameConstants.board[4][0] = "X";
		GameConstants.board[5][0] = "O";
		assertFalse(GameUtil.CheckODiagonalBack());
	}
	
	
	
	
	@Test
	public void testCheckXHorizontalTrue() {
		GameConstants.board[0][0] = "X";
		GameConstants.board[0][1] = "X";
		GameConstants.board[0][2] = "X";
		GameConstants.board[0][3] = "X";
		GameConstants.board[0][4] = "X";
		assertTrue(GameUtil.checkXHorizontal());
		assertThat(logCaptor.getInfoLogs()).containsExactly("Player 2 has won!");
	}
	
	@Test
	public void testCheckXHorizontalFalse() {
		GameConstants.board[0][0] = "X";
		GameConstants.board[0][1] = "X";
		GameConstants.board[1][2] = "X";
		GameConstants.board[3][3] = "X";
		GameConstants.board[0][4] = "X";
		assertFalse(GameUtil.checkXHorizontal());
	}
	
	@Test
	public void testCheckXVerticalTrue() {
		GameConstants.board[0][0] = "X";
		GameConstants.board[1][0] = "X";
		GameConstants.board[2][0] = "X";
		GameConstants.board[3][0] = "X";
		GameConstants.board[4][0] = "X";
		assertTrue(GameUtil.checkXVertical());
		assertThat(logCaptor.getInfoLogs()).containsExactly("Player 2 has won!");
	}
	
	@Test
	public void testCheckXVerticalFalse() {
		GameConstants.board[0][0] = "X";
		GameConstants.board[0][1] = "X";
		GameConstants.board[1][2] = "X";
		GameConstants.board[3][3] = "X";
		GameConstants.board[0][4] = "X";
		assertFalse(GameUtil.checkXVertical());		
	}
	
	@Test
	public void testCheckOVerticalTrue() {
		GameConstants.board[0][0] = "O";
		GameConstants.board[1][0] = "O";
		GameConstants.board[2][0] = "O";
		GameConstants.board[3][0] = "O";
		GameConstants.board[4][0] = "O";
		assertTrue(GameUtil.checkOVertical());
		assertThat(logCaptor.getInfoLogs()).containsExactly("Player 1 has won!");
	}
	
	@Test
	public void testCheckOVerticalFalse() {
		GameConstants.board[0][0] = "X";
		GameConstants.board[0][1] = "X";
		GameConstants.board[1][2] = "X";
		GameConstants.board[3][3] = "X";
		GameConstants.board[0][4] = "X";
		assertFalse(GameUtil.checkOVertical());		
	}
	
	@Test
	public void testCheckOHorizontalTrue() {
		GameConstants.board[0][0] = "O";
		GameConstants.board[0][1] = "O";
		GameConstants.board[0][2] = "O";
		GameConstants.board[0][3] = "O";
		GameConstants.board[0][4] = "O";
		assertTrue(GameUtil.checkOHorizontal());
		assertThat(logCaptor.getInfoLogs()).containsExactly("Player 1 has won!");
	}
	
	@Test
	public void testCheckOHorizontalFalse() {
		GameConstants.board[0][0] = "X";
		GameConstants.board[0][1] = "O";
		GameConstants.board[1][2] = "O";
		GameConstants.board[3][3] = "X";
		GameConstants.board[0][4] = "X";
		assertFalse(GameUtil.checkOHorizontal());
	}
	
	@Test
	public void testCheckX() {
		assertFalse(GameUtil.CheckX());
	}
	
	@Test
	public void testCheckO() {
		assertFalse(GameUtil.CheckO());
	}
	
	@Test
	public void testEndGame() {
		assertTrue(GameUtil.endGame());
	}

}
