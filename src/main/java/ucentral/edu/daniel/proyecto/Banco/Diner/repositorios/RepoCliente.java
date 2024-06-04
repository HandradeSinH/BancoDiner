package ucentral.edu.daniel.proyecto.Banco.Diner.repositorios;

import ucentral.edu.daniel.proyecto.Banco.Diner.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepoCliente extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {

}
