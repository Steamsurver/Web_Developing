package model.classes;

public class GameKey {
	
	private Integer id;
	private Integer gameId;
	private String game_Key;

	public GameKey() {
		
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public String getGameKey() {
		return this.game_Key;
	}

	public void setGameKey(String gameKey) {
		this.game_Key = gameKey;
	}

}
