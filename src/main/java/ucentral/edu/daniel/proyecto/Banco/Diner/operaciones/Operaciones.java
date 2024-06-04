package ucentral.edu.daniel.proyecto.Banco.Diner.operaciones;

import ucentral.edu.daniel.proyecto.Banco.Diner.dto.ClienteDto;
import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Cita;
import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Sede;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface Operaciones {
    boolean inicioSesion(ClienteDto cliente);

    public int ingresarCliente(ClienteDto cliente);

    public List<LocalTime> disponibilidadHoras(Sede sede, LocalDate fecha, String servicio);

    void guardarCita(Cita cita);
}
