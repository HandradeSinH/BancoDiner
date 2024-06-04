package ucentral.edu.daniel.proyecto.Banco.Diner.repositorios;

import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public interface RepoCita extends JpaRepository<Cita, Long>, JpaSpecificationExecutor<Cita> {


    @Query(value = "SELECT hora FROM citas WHERE fecha = :fecha AND servicio = :servicio AND id_sede = :idSede", nativeQuery = true)
    List<Time> listarDisponibilidad(LocalDate fecha, String servicio, Long idSede);


    List<Cita> findByEmpleado_EmpIdAndEstado(long emp_id, String estado);

}