package caio.caminha.orglist.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import caio.caminha.orglist.controllers.Dto.UsuarioDto;
import caio.caminha.orglist.controllers.form.UsuarioForm;
import caio.caminha.orglist.models.Usuario;
import caio.caminha.orglist.repositories.UsuarioRepository;

@RestController
@RequestMapping("/users")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public ResponseEntity<UsuarioDto> createUser(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder builder){
		Usuario user = usuarioForm.convert();
		this.usuarioRepository.save(user);
		
		URI uri = builder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDto(user));
	
	}
}
