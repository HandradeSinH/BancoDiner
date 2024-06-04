/*package co.ucentral.sistemas.gestionCitasBancarias.controladores;

import co.ucentral.sistemas.gestionCitasBancarias.dto.CitaDto;
import co.ucentral.sistemas.gestionCitasBancarias.dto.EmpleadoDto;
import co.ucentral.sistemas.gestionCitasBancarias.entidades.Cita;
import co.ucentral.sistemas.gestionCitasBancarias.repositorios.RepoCita;
import co.ucentral.sistemas.gestionCitasBancarias.servicios.ServicioCita;
import co.ucentral.sistemas.gestionCitasBancarias.servicios.ServicioCliente;
import co.ucentral.sistemas.gestionCitasBancarias.servicios.ServicioEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class ControladorEmpleado {

    @Autowired
    ServicioCita servicioCita;
    @Autowired
    ServicioCliente servicioCliente;
    @Autowired
    ServicioEmpleado servicioEmpleado;
    @Autowired
    RepoCita repoCita;

    @GetMapping("/empleado/inicio-sesion")
    public String formularioIngreso(Model model) {
        EmpleadoDto empleado = new EmpleadoDto();
        model.addAttribute("empleado", empleado);
        return "formulario-ingreso-empleado";
    }

    @PostMapping("/empleado/ingresar")
    public String iniciarSesion(@ModelAttribute("empleado") EmpleadoDto empleado) {
        // Depuración: Imprimir datos recibidos del formulario
        System.out.println("Datos recibidos del formulario - ID: " + empleado.getEmpId() + ", Clave: " + empleado.getClave());

        boolean loginExitoso = servicioEmpleado.inicioSesion(empleado);
        System.out.println("Login exitoso: " + loginExitoso);

        if (loginExitoso) {
            return "redirect:/empleado/portal-empleado/" + empleado.getEmpId();
        }
        System.out.println("Login fallido, redirigiendo a index.");
        return "index";
    }

    @PostMapping("/empleado/cerrar-cita")
    public String cerrarCita (@ModelAttribute("cita") CitaDto citaDto){
        Cita cita = repoCita.getReferenceById(citaDto.getId());
        servicioCita.CerrarCita(cita);
        return "index";
    }

    @GetMapping("/empleado/manejo-cita")
    public String manejoCita(@ModelAttribute("cita") CitaDto citaDto, Model model) {
        Cita cita = repoCita.getReferenceById(citaDto.getId());
        model.addAttribute("cita", cita);
        if (!(cita.getHora() == cita.getHora().plusMinutes(5) && cita.getEstado().equals("En Curso"))){
            return "redirect:/empleado/cerrar-cita";}
        return null;
    }

    @GetMapping("/empleado/transcurso-cita")
    public String transcursoCita(@ModelAttribute("cita") CitaDto citaDto, Model model) {
        Cita cita = repoCita.getReferenceById(citaDto.getId());
        model.addAttribute("cita", cita);
        return "redirect:/empleado/cerrar-cita";
    }

    @GetMapping("/empleado/portal-empleado/{id}")
    public String portalEmpleado(@PathVariable("id") Long id, Model model) {
        model.addAttribute("empleadoId", id);
        return "portal-empleado";
    }
    @GetMapping("/empleado/{id}")
    public ResponseEntity<List<Cita>> getCitasPorEmpleado(@PathVariable Long id) {
        List<Cita> citas = repoCita.findByEmpleado_EmpId(id);
        return ResponseEntity.ok(citas);
    }
}*/

package ucentral.edu.daniel.proyecto.Banco.Diner.controladores;

import ucentral.edu.daniel.proyecto.Banco.Diner.dto.CitaDto;
import ucentral.edu.daniel.proyecto.Banco.Diner.dto.EmpleadoDto;
import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Cita;
import ucentral.edu.daniel.proyecto.Banco.Diner.exception.ResourceNotFoundException;
import ucentral.edu.daniel.proyecto.Banco.Diner.repositorios.RepoCita;
import ucentral.edu.daniel.proyecto.Banco.Diner.servicios.ServicioCita;
import ucentral.edu.daniel.proyecto.Banco.Diner.servicios.ServicioCliente;
import ucentral.edu.daniel.proyecto.Banco.Diner.servicios.ServicioEmpleado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ControladorEmpleado {

    @Autowired
    ServicioCita servicioCita;
    @Autowired
    ServicioCliente servicioCliente;
    @Autowired
    ServicioEmpleado servicioEmpleado;
    @Autowired
    RepoCita repoCita;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/empleado/inicio-sesion")
    public String formularioIngreso(Model model) {
        EmpleadoDto empleado = new EmpleadoDto();
        model.addAttribute("empleado", empleado);
        return "formulario-ingreso-empleado";
    }

    @PostMapping("/empleado/ingresar")
    public String iniciarSesion(@ModelAttribute("empleado") EmpleadoDto empleado) {
        boolean loginExitoso = servicioEmpleado.inicioSesion(empleado);
        if (loginExitoso) {
            return "redirect:/empleado/portal-empleado/" + empleado.getEmpId();
        }
        return "redirect:/";
    }


    @GetMapping("/empleado/cerrar-cita/{idCita}")
    public String cerrarCita (@PathVariable("idCita") Long id, Model model){
        try {
            // Obtiene la cita con el ID proporcionado
            CitaDto cita = servicioCita.obtenerCita(id);
            // Añade la cita al modelo para que pueda ser accesible en la vista
            model.addAttribute("cita", cita);
            // Cierra la cita
            servicioCita.CerrarCita(modelMapper.map(cita, Cita.class));
            // Obtiene el empleado asociado a la cita
            EmpleadoDto empleado = cita.getEmpleado();
            if (empleado != null)
                // Si el empleado no es null, añade su ID al modelo
                model.addAttribute("empleadoId", empleado.getEmpId());
            // Redirige al usuario a la página cita-terminada
            return "cita-terminada";
        } catch (ResourceNotFoundException e) {
            // Si no se encuentra la cita, redirige al usuario a la página index
            return "index";
        }
    }

    @PostMapping("/empleado/cerrar-citas/{idCita}")
    public String cerrarCitas (@PathVariable("idCita") Long id, @RequestParam("comentarios") String comentarios, Model model){
        try {
            CitaDto cita = servicioCita.obtenerCita(id);
            model.addAttribute("cita", cita);
            cita.setComentarios(comentarios); // Añade los comentarios a la cita
            servicioCita.CerrarCita(modelMapper.map(cita, Cita.class));
            return "redirect:/empleado/portal-empleado/" + cita.getEmpleado().getEmpId();
        } catch (ResourceNotFoundException e) {
            return "index";
        }
    }
    @GetMapping("/empleado/atender-cita/{idCita}")
    public String atenderCita(@PathVariable("idCita") Long id, Model model) {
        try {
            CitaDto cita = servicioCita.obtenerCita(id);
            model.addAttribute("cita", cita);
            return "transcurso-cita"; // Nombre de la vista de Thymeleaf
        } catch (ResourceNotFoundException e) {
            return "error";
        }
    }

    @PostMapping("/empleado/transcurso-cita/{idCita}")
    public String transcursoCita(@PathVariable("idCita") Long id, Model model,@RequestParam("observaciones") String observaciones) {
        Cita cita = repoCita.getReferenceById(id);
        cita.setComentarios(observaciones);
        servicioCita.CerrarCita(cita);
        model.addAttribute("cita", cita);
        return "redirect:/empleado/cerrar-cita/" + id;
    }

    @GetMapping("/empleado/portal-empleado/{id}")
    public String portalEmpleado(@PathVariable("id") Long id, Model model) {
        List<Cita> citas = repoCita.findByEmpleado_EmpIdAndEstado(id, "pendiente");
        model.addAttribute("citas", citas);
        model.addAttribute("empleadoId", id);
        return "portal-empleado";
    }
    @GetMapping("/empleado/{id}")
    public ResponseEntity<List<Cita>> citasPendientes(@PathVariable Long id) {
        List<Cita> citas = repoCita.findByEmpleado_EmpIdAndEstado(id, "pendiente");
        return ResponseEntity.ok(citas);
    }
    @PostMapping("/empleado/llamado-en-sala/{id}")
    public String llamadoEnSala(@PathVariable("id") Long id, Model model) {
        model.addAttribute("idCita", id);
        return "espera-en-sala";
    }
}