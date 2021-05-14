package caio.caminha.orglist.controllers.form;

import caio.caminha.orglist.models.Task;
import caio.caminha.orglist.models.Usuario;
import caio.caminha.orglist.repositories.TaskRepository;
import caio.caminha.orglist.repositories.UsuarioRepository;

public class TaskForm {
	
	
	private String title;
	private String description;
	private String usuario;
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public Task convert(UsuarioRepository usuarioRepository) {
		Usuario user = usuarioRepository.findByName(this.usuario);
		return new Task(this.title, this.description, user);
	}
	
	public Task update(Long id, TaskRepository taskRepository) {
		Task task = taskRepository.getOne(id);
		task.setTitle(this.title);
		task.setDescription(this.description);
		taskRepository.save(task);
		return task;
	}
	
}
