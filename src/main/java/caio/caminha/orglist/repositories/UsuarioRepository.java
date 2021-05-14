package caio.caminha.orglist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import caio.caminha.orglist.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findByName(String name);
}
