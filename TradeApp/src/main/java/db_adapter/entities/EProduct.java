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
@Table(name="product")
@NamedQuery(name="EProduct.findAll", query="SELECT p FROM EProduct p")
public class EProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"id\"", unique=true, nullable=false)
	private Integer id;

	@Column(name="\"cost\"", nullable=false)
	private Integer cost;

	@Column(name="\"description\"", length=2147483647)
	private String description;

	@Column(name="\"game_name\"", nullable=false, length=40)
	private String gameName;

	@Column(name="\"img_source\"", length=2147483647)
	private String imgSource;

	public EProduct() {
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