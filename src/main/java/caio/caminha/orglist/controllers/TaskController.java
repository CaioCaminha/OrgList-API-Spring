package caio.caminha.orglist.controllers;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import caio.caminha.orglist.controllers.Dto.TaskDto;
import caio.caminha.orglist.controllers.form.TaskForm;
import caio.caminha.orglist.models.Task;
import caio.caminha.orglist.repositories.TaskRepository;
import caio.caminha.orglist.repositories.UsuarioRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@GetMapping
	public Page<TaskDto> listTask(@RequestParam(required = false) String usuario,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable page) {
		if(usuario == null) {
			Page<Task> tasks = this.taskRepository.findAll(page);
			return TaskDto.convert(tasks);
		}else {
			Page<Task> tasks = this.taskRepository.findByUsuarioName(usuario, page);
			return TaskDto.convert(tasks);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TaskDto> criaTask(@RequestBody @Valid TaskForm taskForm, UriComponentsBuilder builder) {
		Task task = taskForm.convert(this.usuarioRepository);
		this.taskRepository.save(task);
		
		URI uri = builder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();
		return ResponseEntity.created(uri).body(new TaskDto(task));
	}
	
	public void updateTask() {
		
	}
	
	public void deleteTask() {
		
	}
	
	
	
}
