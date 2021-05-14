package caio.caminha.orglist.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
public class Usuario {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull @NotEmpty
	private String name;
	@NotNull @NotEmpty
	private String email;
	@NotNull @NotEmpty
	private String password;
	@OneToMany(mappedBy = "usuario")
	private List<Task> task;
	
	public Usuario() {}
	
	public Usuario(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}
