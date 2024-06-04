package ucentral.edu.daniel.proyecto.Banco.Diner.entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Cliente")
@Table(name = "CLIENTES")
@Builder
public class Cliente {

    @Id
    @Column
    private long identificacion;

    @Column
    private String nombre;

    @Column
    private String correo;

    @Column
    private String clave;


}
