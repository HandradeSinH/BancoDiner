package ucentral.edu.daniel.proyecto.Banco.Diner.repositorios;

import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Cita;
import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepoSede extends JpaRepository<Sede, Long>, JpaSpecificationExecutor<Cita> {
}
