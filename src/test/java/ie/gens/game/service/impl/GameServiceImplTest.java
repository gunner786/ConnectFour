package ie.gens.game.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ie.gens.game.constant.GameConstants;
import ie.gens.game.model.Player;
import ie.gens.game.utils.GameUtil;
import nl.altindag.log.LogCaptor;

@RunWith(SpringJUnit4ClassRunner.class)
public class GameServiceImplTest {
	
	@InjectMocks
	private GameServiceImpl gameServiceImpl;
	
	@Mock
	private GameUtil gameUtil;
	
	private static final String playerEmpty = "Please enter player name";
	private static final String playerExists = "Please enter different player name";
	private static final String playerJoins = "Shameel has joined the game";
	private static final String playerWaiting = "Waiting for Player 2 to join!";
	private static final String playerTurn2 = "your turn John";
	private static final String playerTurn1 = "your turn Shameel";
	private static final String gameEnded = "Shameel disconnected the game John wins !!!";
	private LogCaptor logCaptor;
	
	
	@Before
	public void setUp () {
		GameUtil.makeGame();
		logCaptor = LogCaptor.forClass(GameServiceImpl.class);
		
	}
	
	@Test
	public void testPlayerEmpty() {
		
		String res = gameServiceImpl.startGame("");
		assertThat(res).contains(playerEmpty);
		
	}
	
	@Test
	public void testPlayerExists() {
		gameServiceImpl.playerList.add("Shameel");
		String res = gameServiceImpl.startGame("Shameel");
		assertThat(res).contains(playerExists);
	}
	
	@Test
	public void testPlayerJoinsGame() {
		String res = gameServiceImpl.startGame("Shameel");
		assertThat(res).contains(playerJoins);
	}
	
	@Test
	public void playGameOnePlayer() {
		gameServiceImpl.playerList.add("John");
		String res = gameServiceImpl.playGame("John",2);
		assertThat(res).contains(playerWaiting);
		
	}
	
	@Test
	// Shameel joined game first and he makes the first move
	public void playGameBothPlayerJoin() {
		gameServiceImpl.playerList.add("Shameel");
		gameServiceImpl.playerList.add("John");
		String res = gameServiceImpl.playGame("Shameel",2);
		assertThat(res).contains(playerTurn2);
		
	}
	
	@Test
	public void playGameBothCheckPlayer2Turn() {
		gameServiceImpl.playerList.add("Shameel");
		gameServiceImpl.playerList.add("John");
		GameConstants.player1Turn = false;
		String res = gameServiceImpl.playGame("Shameel",2);
		assertThat(res).contains(playerTurn2);
		
	}
	
	@Test
	public void playGameBothCheckPlayer1Turn() {
		gameServiceImpl.playerList.add("Shameel");
		gameServiceImpl.playerList.add("John");
		GameConstants.player1Turn = false;
		String res = gameServiceImpl.playGame("John",2);
		assertThat(res).contains(playerTurn1);
		
	}
	
	@Test
	// Shameel joined game first and Join makes the first move
	public void playGameBothPlayerJoin2() {
		GameConstants.player1Turn = false;
		gameServiceImpl.playerList.add("Shameel");
		gameServiceImpl.playerList.add("John");
		String res = gameServiceImpl.playGame("John",2);
		assertThat(res).contains(playerTurn1);
		
	}
	
	@Test
	public void endGame() {
		gameServiceImpl.playerList.add("Shameel");
		gameServiceImpl.playerList.add("John");
		String res = gameServiceImpl.endGame("Shameel");
		assertThat(res).contains(gameEnded);
		assertThat(logCaptor.getInfoLogs()).containsExactly(gameEnded);
		
	}
}
