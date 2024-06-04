package ucentral.edu.daniel.proyecto.Banco.Diner.operaciones;

import ucentral.edu.daniel.proyecto.Banco.Diner.dto.CitaDto;
import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Cita;

import java.util.List;
import java.util.Optional;

public interface OperacionesCita {

    CitaDto registrar(CitaDto citaDto);

    List<CitaDto> obtenerCitas();

    CitaDto obtenerCita(long serial);

    Optional<Cita> obtenerCitaById(long serial);

    Cita actualizar(CitaDto citaDto);

    void eliminar(long serial);

    void CerrarCita(Cita cita);

    List<CitaDto> obtenerCitasPorEmpleado(long identificacion);

    Cita guardarCita(Cita cita);
}
