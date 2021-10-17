package ie.gens.game.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import ie.gens.game.constant.GameConstants;
import ie.gens.game.model.Player;
import ie.gens.game.service.GameService;
import ie.gens.game.utils.GameUtil;
import java.util.stream.Collectors;


@Component
public class GameServiceImpl implements GameService {

	public List<String> playerList = new ArrayList<>();
	
	private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);
	
	public String startGame(String playerName) {
		logger.info("Begin startGame .. ");
		
		Gson gson = new Gson();
		Player player = new Player();
		
		if (playerName.trim().isEmpty()) {
			logger.error(GameConstants.playerEmpty);
			player.setGameMessage(GameConstants.playerEmpty);
			return gson.toJson(player);
		} else {
			
			GameUtil.makeGame();
			if(playerList.contains(playerName)) {
			logger.error(GameConstants.playerEmpty);
			player.setGameMessage("Player " + playerName + " already exists" + " Please enter different player name");
			return gson.toJson(player);
			}
			playerList.add(playerName);
			logger.info(playerName + " has joined the game");
			player.setGameMessage(playerName + " has joined the game" + GameUtil.printGame());
		}
		return gson.toJson(player);
		
	}
	
	public String playGame(String playerName, int move) {
		logger.info("Begin playGame .. " +playerName + " made move " + move);
		Gson gson = new Gson();
		Player player = new Player();
        String response = GameUtil.businessRules(playerList,playerName);

        if(!response.equals("")) {
        	logger.info(response);
        	player.setGameMessage(response);
        	return gson.toJson(player);
        } 
        // Player 1 will start the game first
        else if (playerList.get(0).equalsIgnoreCase(playerName)) {
        	checkPlayer1Turn(player,move);
            
        } else {
        	checkPlayer2Turn(player,move);
            
        }
         checkGameWinner(player);
		 return gson.toJson(player);
    }
	
	@Override
	public String endGame(String playerName) {
		 Gson gson = new Gson();
		 Player player = new Player();
		 List<String> playerWon = playerList.stream().filter((value) -> !value.equals(playerName)).collect(Collectors.toList());
		 player.setGameMessage(playerName + " disconnected the game " +playerWon.get(0)+ " wins !!!");
		 logger.info(player.getGameMessage());
	     return gson.toJson(player);
	}

	private void checkGameWinner(Player player) {
		if (GameUtil.CheckX()) {
			logger.info("Player 2: " + playerList.get(1) + " has Won!");
            player.setGameMessage("Player 2: " + playerList.get(1) + " has Won!");
        } else if(GameUtil.CheckO()) {
        	logger.info("Player 1: " + playerList.get(0) + " has Won!");
            player.setGameMessage("Player 1: " + playerList.get(0) + " has Won!");
        } 
		
	}

	private void checkPlayer2Turn(Player player, int move) {
		
		if (!GameConstants.player1Turn) {
        	GameUtil.DropX(player,move);
            if(!player.getGameMessage().equals("That column is full")) {
            	GameConstants.player1Turn = true;
                player.setGameMessage("It's your turn " + playerList.get(0) + GameUtil.printGame());
            }

        } else {
            player.setGameMessage("It's your turn " + playerList.get(0) + GameUtil.printGame());
        }
		
	}

	private void checkPlayer1Turn(Player player, int move) {
		
		if (GameConstants.player1Turn) {
            GameUtil.DropO(player,move);
            if(!player.getGameMessage().equals("That column is full")) {
            	GameConstants.player1Turn = false;
                player.setGameMessage("It's your turn " + playerList.get(1) + GameUtil.printGame());
            }

        } else {
            player.setGameMessage("It's your turn " + playerList.get(1) + GameUtil.printGame());
        }
	}
	
	

}
