package ie.gens.game.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ie.gens.game.constant.GameConstants;
import ie.gens.game.model.Player;

public class GameUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(GameUtil.class);
	
    //creates the board
    public static void makeGame() {
        for (int i = 0; i < GameConstants.ROWS ; i++) {
            for (int j = 0; j < GameConstants.COLUMNS; j++) {
                GameConstants.board[i][j] = " ";
            }
        }
        logger.info("Game board is created");
    }
    
    public static String businessRules(List<String>players, String player) {
        if (players.size() < 2) {
        	logger.info(GameConstants.playerWaiting);
            return GameConstants.playerWaiting + printGame();
        }
        else {
        	logger.info("Both players have joined game");
        	return "";
        }
    }

    //prints the board
    public static String printGame() {
        //prints blank board after setup
        String result = "<br/>";
        // prints the board
        for (int i = 0; i < GameConstants.ROWS ; i++) {
            for (int j = 0; j < GameConstants.COLUMNS; j++) {
                result += "[" + GameConstants.board[i][j] + "]";
            }
            result += "<br/>";
        }
        return result;
    }
    
  //Checks if a player 1 has won horizontally
    public static boolean checkXHorizontal(){
        // creates boolean to act as flag
        boolean win = false;

        // creates counter
        int counter = 0;
        while (!win) {

            for (int r = 0; r < GameConstants.ROWS ; r += 1) {
                for (int c = 0; c < GameConstants.COLUMNS ; c += 1) {
                    if (GameConstants.board[r][c].equals("X")) { 
                        counter += 1;
                    } else {
                        counter = 0; 
                    }
                    if (counter >= 5) {
                    	logger.info("Player 2 has won!"); 
                        win = true;
                    }
                }
            }
            break;
        }
        return win;
    }

    
    public static boolean checkXVertical(){
        
        boolean win = false;
        int counter = 0;
        while (!win) {

            for (int c = 0; c < GameConstants.COLUMNS ; c += 1) {
                for (int r = 0; r < GameConstants.ROWS ; r += 1) {
                    if (GameConstants.board[r][c].equals("X")) { 
                        counter += 1;
                    } else {
                        counter = 0; 
                    }
                    if (counter >= 5) {
                    	logger.info("Player 2 has won!"); 
                        win = true;
                    }
                }
            }
            break;
        }
        return win;
    }

    public static boolean checkOVertical(){
       
        boolean win = false;
        int counter = 0;
        while (!win) {
            for (int c = 0; c < GameConstants.COLUMNS ; c += 1) {
                for (int r = 0; r < GameConstants.ROWS ; r += 1) {
                    if (GameConstants.board[r][c].equals("O")) { 
                        counter += 1;
                    } else {
                        counter = 0; 
                    }
                    if (counter >= 5) {
                    	logger.info("Player 1 has won!");
                        win = true;
                    }
                }
            }
            break;
        }
        return win;
    }

   
    public static boolean checkOHorizontal(){
        boolean win = false;
        int counter = 0;
        while (!win) {
            for (int r = 0; GameConstants.ROWS > r; r += 1) {
                for (int c = 0; GameConstants.COLUMNS > c; c += 1) {
                    if (GameConstants.board[r][c].equals("O")) {
                        counter += 1;
                    } else {
                        counter = 0; 
                    }
                    if (counter >= 5) {
                    	logger.info("Player 1 has won!"); 
                        win = true;
                    }
                }
            }
            break;
        }
        return win;
    }

    public static boolean checkXDiagonalForward() {
        
        for (int c = 5; c > 3; c--) {
            for (int r = 0; r < 5; r++) {
                if (GameConstants.board[r][c].equals("X") && GameConstants.board[r][c] == GameConstants.board[r + 1][c - 1] && GameConstants.board[r][c] == GameConstants.board[r + 2][c - 2]
                        && GameConstants.board[r][c] == GameConstants.board[r + 3][c - 3] && GameConstants.board[r][c] == GameConstants.board[r + 4][c - 4]) {
                	logger.info("Player 2 has won!");
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean CheckODiagonalForward() {
        
        for (int c = 5; c > 3; c--) {
            for (int r = 0; r < 5; r++) {
            	if (GameConstants.board[r][c].equals("X") && GameConstants.board[r][c] == GameConstants.board[r + 1][c - 1] && GameConstants.board[r][c] == GameConstants.board[r + 2][c - 2]
                        && GameConstants.board[r][c] == GameConstants.board[r + 3][c - 3] && GameConstants.board[r][c] == GameConstants.board[r + 4][c - 4]) {
            		logger.info("Player 1 has won!");
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkXDiagonalBack() {
        
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 2; i++) {
                if (GameConstants.board[j][i].equals("X") && GameConstants.board[j][i] == GameConstants.board[j + 1][i + 1] && GameConstants.board[j][i] == GameConstants.board[j + 2][i + 2]
                        && GameConstants.board[j][i] == GameConstants.board[j + 3][i + 3] && GameConstants.board[j][i] == GameConstants.board[j + 4][i + 4]) {
                    System.out.println("Player 2 has won!");
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean CheckODiagonalBack() {
        
    	for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 2; i++) {
                if (GameConstants.board[j][i].equals("X") && GameConstants.board[j][i] == GameConstants.board[j + 1][i + 1] && GameConstants.board[j][i] == GameConstants.board[j + 2][i + 2]
                        && GameConstants.board[j][i] == GameConstants.board[j + 3][i + 3] && GameConstants.board[j][i] == GameConstants.board[j + 4][i + 4]) {
                    System.out.println("Player 1 has won!");
                    return true;
                }
            }
        }
        return false;
    }

    public static void DropX(Player player, int column) {
       
        int counter = 1;

        while (true) {
            if (column >= GameConstants.COLUMNS) {
            	logger.error("That's not a valid column");
            	player.setGameMessage("That's not a valid column");
                break;
            }

            if (GameConstants.board[GameConstants.BOTTOM_ROW][column].equals(" ")) { // checks to see if space is blank, puts X there if it is
            	GameConstants.board[GameConstants.BOTTOM_ROW][column] = "X";
                break; 
            } else if (GameConstants.board[GameConstants.BOTTOM_ROW][column].equals("X") || GameConstants.board[GameConstants.BOTTOM_ROW][column].equals("O")) { // if space isn't blank,
                // checks to see if one
                // above is
                if (GameConstants.board[GameConstants.BOTTOM_ROW - counter][column].equals(" ")) { // puts X if blank
                	GameConstants.board[GameConstants.BOTTOM_ROW - counter][column] = "X";
                    break; // breaks loop after placing
                }
            }
            counter += 1; // adds one to counter if the space wasn't blank, then loops again
            if (counter == GameConstants.ROWS) { // checks to see if at end of column
            	logger.error("That column is full");
            	player.setGameMessage("That column is full");
                break;
            }
        }
    }

    public static void DropO(Player player, int column){
        //creates a counter
        int counter = 1;

        while(true){
            if(column >= GameConstants.COLUMNS){
            	logger.error("That's not a valid column");
            	player.setGameMessage("That's not a valid column");
                break;
            }

            if (GameConstants.board[GameConstants.BOTTOM_ROW][column].equals(" ")) { //checks to see if space is blank, puts O there if it is
            	GameConstants.board[GameConstants.BOTTOM_ROW][column] = "O";
                break; //breaks loop after placing
            }else if(GameConstants.board[GameConstants.BOTTOM_ROW][column].equals("X") || GameConstants.board[GameConstants.BOTTOM_ROW][column].equals("O")){ //if space isn't blank, checks to see if one above is
                if(GameConstants.board[GameConstants.BOTTOM_ROW - counter][column].equals(" ")){ //puts O if blank
                	GameConstants.board[GameConstants.BOTTOM_ROW - counter][column] = "O";
                    break; //breaks loop after placing
                }
            }
            counter += 1; //adds one to counter if the space wasn't blank, then loops again
            if(counter == GameConstants.ROWS){ //checks to see if at end of column
            	player.setGameMessage("That column is full");
                break;
            }
        }
    }

    public static boolean CheckX() {
        // creates flag
        boolean flag = false;

        // checks all Xs at once, for cleaner main loop
        if (checkXVertical() || checkXHorizontal() || checkXDiagonalBack() || checkXDiagonalForward()) {
            flag = true;
        }
        return flag;
    }

    public static boolean CheckO() {
        boolean flag = false;
        if (checkOVertical() || checkOHorizontal() || CheckODiagonalBack() || CheckODiagonalForward()) {
            flag = true;
        }
        return flag;
    }

    public static boolean endGame() {
    	GameConstants.board = new String[GameConstants.ROWS][GameConstants.COLUMNS];
        makeGame();
        printGame();
        GameConstants.player1Turn = true;
        return true;
    }

}
