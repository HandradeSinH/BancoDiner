package co.ucentral.sistemas.gestionCitasBancarias.repositorios;

import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Cita;
import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Sede;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ucentral.edu.daniel.proyecto.Banco.Diner.repositorios.RepoCita;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@DataJpaTest
class RepoCitaTest {

    @Autowired
    RepoCita repoCita;

    @DisplayName("guardar citas")
    @Test
    void guardarCitas(){

        Sede sede = new Sede();
        sede.setId_sede(1L); // Asigna un ID válido para la sede
        sede.setNombre("Norte");
        sede.setDireccion("Calle 170");


        Cita cita = new Cita();
        cita.setTurno(1);
        cita.setSede(sede);
        cita.setFecha(LocalDate.now());
        cita.setHora(LocalTime.now());
        cita.setServicio("Servicio de prueba");

        Cita citaGuardada = repoCita.save(cita);


        assertNotNull(citaGuardada);

    }

    @DisplayName("Listar citas")
    @Test
    void listarCitas(){
        List<Cita> citas = repoCita.findAll();

        assertNotNull(citas);

        assertEquals(0, citas.size());

    }

    @DisplayName("obtener cita")
    @Test
    void obtenerCitas(){

        Cita cita = repoCita.findById(1L).orElse(null);

        assertNotNull(cita);
    }

    @DisplayName("eliminar cita")
    @Test
    void eliminarCitas(){
        repoCita.deleteById(1L);

        assertEquals(0, repoCita.count());

    }


}