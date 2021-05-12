package caio.caminha.orglist.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import caio.caminha.orglist.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	Optional<Task> findByName(String name);
}
