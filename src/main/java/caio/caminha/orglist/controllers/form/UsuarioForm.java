package caio.caminha.orglist.controllers.form;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import caio.caminha.orglist.models.Usuario;

public class UsuarioForm {
	private String name;
	private String email;
	private String password;
	
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
	
	public Usuario convert() {
		return new Usuario(this.name, this.email, new BCryptPasswordEncoder().encode(password));
	}
}
