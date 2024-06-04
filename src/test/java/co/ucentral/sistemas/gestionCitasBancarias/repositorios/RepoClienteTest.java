package co.ucentral.sistemas.gestionCitasBancarias.repositorios;

import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Cliente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ucentral.edu.daniel.proyecto.Banco.Diner.repositorios.RepoCliente;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class RepoClienteTest {

    @Autowired
    RepoCliente repoCliente;

    @Autowired
    TestEntityManager testEntityManager;

    @DisplayName("Test guardar Cliente")
    @Test
    void testGuardarCliente(){

        //given
        Cliente cliente = Cliente.builder()
                .identificacion(555)
                .nombre("pepito")
                .correo("pepito@gmail.com")
                .clave("clave2").build();
        //when
        Cliente newcliente = repoCliente.save(cliente);

        //then
        //assertThat(newcliente).isNotNull();

        assertEquals("pepito",newcliente.getNombre());

    }

    @DisplayName("Test listar Cliente")
    @Test
    void listarClientes(){

        Cliente cliente = Cliente.builder()
                .identificacion(222)
                .nombre("pepito2")
                .correo("pepito2@gmail.com")
                .clave("clave23").build();

        repoCliente.save(cliente);


        List<Cliente> clientes = (List<Cliente>) repoCliente.findAll();

        assertThat(clientes).hasSameClassAs(1);
    }

    @DisplayName("Test Eliminar Cliente")
    @Test
    void eliminarCliente(){

        Cliente cliente = Cliente.builder()
                .identificacion(555)
                .nombre("pepito3")
                .correo("pepito3@gmail.com")
                .clave("clave4").build();


        repoCliente.save(cliente);


        assertThat(cliente).isNull();
    }

    @DisplayName("obtener Cliente por id")
    @Test
    void obtenerCliente(){

        Cliente cliente = Cliente.builder()
                .identificacion(555)
                .nombre("pepito3")
                .correo("pepito3@gmail.com")
                .clave("clave4").build();


        repoCliente.save(cliente);

        Cliente cliente2 = repoCliente.getReferenceById(555L);

        assertThat(cliente2.getIdentificacion()).isEqualTo(555);


    }
}