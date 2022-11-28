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
@Table(name="user_table")
@NamedQuery(name="EUser.findAll", query="SELECT u FROM EUser u")
public class EUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "\"id\"", unique=true, nullable=false)
	private Integer id;

	@Column(name = "\"login\"", nullable=false, length=32)
	private String login;

	@Column(name = "\"password\"", nullable=false, length=128)
	private String password;

	@Column(name = "\"access_level\"", nullable=false, length=32)
	private String accessLevel;
	
	
	public EUser() {
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAccessLevel() {
		return this.accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
}