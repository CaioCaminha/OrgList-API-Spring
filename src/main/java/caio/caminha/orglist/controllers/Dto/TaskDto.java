package caio.caminha.orglist.controllers.Dto;

import org.springframework.data.domain.Page;

import caio.caminha.orglist.models.Task;
import caio.caminha.orglist.models.Usuario;

public class TaskDto {
	private Long id;
	private String title;
	private String description;
	private Usuario usuario;
	
	public TaskDto() {}
	
	public TaskDto(Task task) {
		this.id = task.getId();
		this.title = task.getTitle();
		this.description = task.getDescription();
		this.usuario = task.getUsuario();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static Page<TaskDto> convert(Page<Task> task){
		return task.map(TaskDto::new);
	}
}
