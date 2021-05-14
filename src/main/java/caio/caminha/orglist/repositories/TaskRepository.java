package caio.caminha.orglist.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import caio.caminha.orglist.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	Page<Task> findByUsuarioName(String name, Pageable page);
}
