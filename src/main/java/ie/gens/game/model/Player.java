package ie.gens.game.model;

public class Player {
	
	private String name;
	private int move;
	private String gameMessage= "";
	
	
	public int getMove() {
		return move;
	}

	public String getGameMessage() {
		return gameMessage;
	}


	public void setGameMessage(String gameMessage) {
		this.gameMessage = gameMessage;
	}


	public void setMove(int move) {
		this.move = move;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getName() {
		return null;
	}
	

}
