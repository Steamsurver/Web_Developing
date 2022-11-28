package model.classes;

import jakarta.persistence.Column;

public class GameProduct {
	private Integer id;
	private Integer cost;
	private String description;
	private String gameName;
	private String imgSource;

	
	public GameProduct() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCost() {
		return this.cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGameName() {
		return this.gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getImgSource() {
		return this.imgSource;
	}

	public void setImgSource(String imgSource) {
		this.imgSource = imgSource;
	}
}
