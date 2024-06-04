package ucentral.edu.daniel.proyecto.Banco.Diner.servicios;

import ucentral.edu.daniel.proyecto.Banco.Diner.dto.EmpleadoDto;
import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Empleado;
import ucentral.edu.daniel.proyecto.Banco.Diner.operaciones.OperacionesEmpleado;
import ucentral.edu.daniel.proyecto.Banco.Diner.repositorios.RepoCita;
import ucentral.edu.daniel.proyecto.Banco.Diner.repositorios.RepoCliente;
import ucentral.edu.daniel.proyecto.Banco.Diner.repositorios.RepoEmpleado;
import ucentral.edu.daniel.proyecto.Banco.Diner.repositorios.RepoSede;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioEmpleado implements OperacionesEmpleado {
    @Autowired
    RepoCliente repoCliente;

    @Autowired
    RepoCita repoCita;

    @Autowired
    RepoSede repoSede;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    RepoEmpleado repoEmpleado;

    public boolean inicioSesion(EmpleadoDto empleado) {
        Empleado newEmpleado = modelMapper.map(empleado, Empleado.class);
        long identidad = newEmpleado.getEmpId();
        String clave = newEmpleado.getClave();
        boolean seguir = false;
        Optional<Empleado> empleadoOptional = repoEmpleado.findById(identidad);
        if (empleadoOptional.isPresent()) {
            Empleado empleadoEncontrado = empleadoOptional.get();
            if (empleadoEncontrado.getClave().equals(clave)){
                seguir = true;
            }
        }
        return seguir;
    }
}