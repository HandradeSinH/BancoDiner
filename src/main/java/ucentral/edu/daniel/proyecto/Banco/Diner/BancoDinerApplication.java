package ucentral.edu.daniel.proyecto.Banco.Diner;

import ucentral.edu.daniel.proyecto.Banco.Diner.repositorios.RepoCliente;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class BancoDinerApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();
		System.setProperty("spring.datasource.url", Objects.requireNonNull(dotenv.get("URL")));
		System.setProperty("spring.datasource.username", Objects.requireNonNull(dotenv.get("USERNAME")));
		System.setProperty("spring.datasource.password", Objects.requireNonNull(dotenv.get("PASSWORD")));
		SpringApplication.run(BancoDinerApplication.class, args);
	}

	@Autowired
	RepoCliente respositorioCliente;

}
