package caio.caminha.orglist.controllers;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import caio.caminha.orglist.controllers.Dto.TaskDto;
import caio.caminha.orglist.controllers.form.TaskForm;
import caio.caminha.orglist.controllers.form.UsuarioForm;
import caio.caminha.orglist.models.Task;
import caio.caminha.orglist.models.Usuario;
import caio.caminha.orglist.repositories.TaskRepository;
import caio.caminha.orglist.repositories.UsuarioRepository;
import caio.caminha.orglist.security.TokenService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	//injeção de dependencias
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@GetMapping
	public ResponseEntity<Page<TaskDto>> listTask(@RequestParam(required = false) String usuario,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable page, @RequestHeader("Authorization") String token) {
		if(usuario == null && this.tokenService.isTokenValid(token.substring(7, token.length()))) {
			Long id = tokenService.getUserId(token.substring(7, token.length()));
			
			String userName = this.usuarioRepository.getOne(id).getName();
			
			Page<Task> tasks = this.taskRepository.findByUsuarioName(userName, page);
			
			return ResponseEntity.ok(TaskDto.convert(tasks));
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TaskDto> criaTask(@RequestBody TaskForm taskForm, UriComponentsBuilder builder,  @RequestHeader("Authorization") String token) {
			
			if(this.tokenService.isTokenValid(token.substring(7, token.length()))) {
				Long id = this.tokenService.getUserId(token.substring(7, token.length()));
				String userName = this.usuarioRepository.getOne(id).getName();
				taskForm.setUsuario(userName);
				Task task = taskForm.convert(this.usuarioRepository);
				this.taskRepository.save(task);
				
				URI uri = builder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();
				return ResponseEntity.created(uri).body(new TaskDto(task));
			}
			return ResponseEntity.badRequest().build();
		
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskForm taskForm) {
		Optional<Task> optional = this.taskRepository.findById(id);
		if(optional.isPresent()) {
			Task task = taskForm.update(id, taskRepository);
			return ResponseEntity.ok(new TaskDto(task));
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Long id) {
		Optional<Task> optional = this.taskRepository.findById(id);
		if(optional.isPresent()) {
			this.taskRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	
	
}
