package ucentral.edu.daniel.proyecto.Banco.Diner.repositorios;

import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
public interface RepoEmpleado extends JpaRepository<Empleado, Long>, JpaSpecificationExecutor<Empleado> {

    @Query(value = "SELECT * FROM empleados WHERE id_sede = :idSede AND cargo = :cargo", nativeQuery = true)
    Empleado findBySedeAndCargo(Long idSede, String cargo);
    
}
