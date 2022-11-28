package db_adapter.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


@Entity
@Table(name="game_keys")
@NamedQuery(name="EGameKey.findAll", query="SELECT g FROM EGameKey g")
public class EGameKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="game_id")
	private Integer gameId;

	@Column(name="game_key", length=2147483647)
	private String game_Key;

	public EGameKey() {
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