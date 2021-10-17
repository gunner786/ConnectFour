package ie.gens.game.constant;

public class GameConstants {
	
	public static final int COLUMNS = 9;
    public static final int ROWS = 6;
    public static final String playerEmpty = "Please enter player name";
    public static final String playerExists = "Player already exists, please enter a different player name";
    public static final String playerWaiting = "Waiting for Player 2 to join!";
    public static final int BOTTOM_ROW = ROWS - 1;
    public static String[][] board = new String[ROWS][COLUMNS];
    public static boolean player1Turn = true;

}
