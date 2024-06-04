package ucentral.edu.daniel.proyecto.Banco.Diner.servicios;

import ucentral.edu.daniel.proyecto.Banco.Diner.dto.ClienteDto;
import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Cita;
import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Cliente;
import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Empleado;
import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Sede;
import ucentral.edu.daniel.proyecto.Banco.Diner.modeloCorreo.StructuraCorreo;
import ucentral.edu.daniel.proyecto.Banco.Diner.operaciones.Operaciones;
import ucentral.edu.daniel.proyecto.Banco.Diner.repositorios.RepoCita;
import ucentral.edu.daniel.proyecto.Banco.Diner.repositorios.RepoCliente;
import ucentral.edu.daniel.proyecto.Banco.Diner.repositorios.RepoEmpleado;
import ucentral.edu.daniel.proyecto.Banco.Diner.repositorios.RepoSede;
import org.modelmapper.ModelMapper;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ServicioCliente implements Operaciones {
    @Autowired
    RepoCliente repoCliente;

    @Autowired
    RepoEmpleado repoEmpleado;

    @Autowired
    RepoCita repoCita;

    @Autowired
    RepoSede repoSede;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public boolean inicioSesion(ClienteDto cliente) {
        Cliente newCliente = modelMapper.map(cliente, Cliente.class);
        long identidad = newCliente.getIdentificacion();
        String clave = newCliente.getClave();
        Optional<Cliente> clienteOptional = repoCliente.findById(identidad);
        if (clienteOptional.isPresent()) {
            Cliente clienteEncontrado = clienteOptional.get();
            if (clienteEncontrado.getClave().equals(clave)) {
                return true;
            } else {

                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int ingresarCliente(ClienteDto cliente) {
        Cliente newCliente = modelMapper.map(cliente,Cliente.class);
        long identidad = newCliente.getIdentificacion();
        if(!repoCliente.findById(identidad).isPresent()){
            repoCliente.save(newCliente);
            return 0;
        }else{
            return 1;
        }

    }

    @Override
    public List<LocalTime> disponibilidadHoras(Sede sede, LocalDate fecha, String servicio){
        //generar lista de horas posibles para asignar cita
        LocalTime inicio = LocalTime.of(8, 0);  // 8:00 AM
        LocalTime fin = LocalTime.of(16, 0);    // 4:00 PM

        int minutosIntervalo = 30;

        List<LocalTime> horas = new ArrayList<>();

        LocalTime horaActual = inicio;
        while (horaActual.isBefore(fin)) {
            horas.add(horaActual);
            horaActual = horaActual.plusMinutes(minutosIntervalo);
        }

        List<Time> resultTimes = repoCita.listarDisponibilidad(fecha, servicio, sede.getId_sede());

        if (resultTimes == null) {
            return horas; // Devolver todas las horas disponibles si no se encontraron tiempos reservados
        }

        List<LocalTime> localTimes = new ArrayList<>();
        for (Time time : resultTimes) {
            if (time != null) {
                localTimes.add(time.toLocalTime());
            }
        }


        horas.removeAll(localTimes);


        return horas;
    }

    @Override
    public void guardarCita(Cita cita) {
            repoCita.save(cita);
    }

    public Cita obtenerCita(long id){
        Cita ncita = repoCita.getReferenceById(id);
        return ncita;
    }


    @Value("$(spring.email.username)")
    private String fromEmail;

    public void enviarCorreo(String correo, StructuraCorreo structuraCorreo){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setSubject(structuraCorreo.getAsunto());
        simpleMailMessage.setText(structuraCorreo.getMensaje());
        simpleMailMessage.setTo(correo);

        javaMailSender.send(simpleMailMessage);

    }

    public Empleado seleccionarEmpleado(Long idSede, String cargo) {
        return repoEmpleado.findBySedeAndCargo(idSede, cargo);
    }


}
