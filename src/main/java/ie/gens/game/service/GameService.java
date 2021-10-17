package ie.gens.game.service;

public interface GameService {
	
	public String startGame(String player);
	public String endGame(String player);
	public String playGame (String player, int move);
}
