package caio.caminha.orglist.controllers.Dto;

import org.springframework.data.domain.Page;

import caio.caminha.orglist.models.Usuario;

public class UsuarioDto {
	private Long id;
	private String name;
	private String email;
	
	public UsuarioDto() {}
	
	public UsuarioDto(Usuario user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
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
	
	public static Page<UsuarioDto> convert(Page<Usuario> user){
		return user.map(UsuarioDto::new);
	}
}
