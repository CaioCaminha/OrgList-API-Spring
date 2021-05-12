package caio.caminha.orglist.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "task")
public class Task {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull @NotEmpty
	private String title;
	@NotNull @NotEmpty
	private String description;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
}
