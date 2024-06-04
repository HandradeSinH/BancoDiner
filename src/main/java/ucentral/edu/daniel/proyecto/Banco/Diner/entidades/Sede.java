package ucentral.edu.daniel.proyecto.Banco.Diner.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Sede")
@Table(name = "SEDES")
@Builder
public class Sede {

    @Id
    @Column
    private long id_sede;

    @Column(name = "SED_NOMBRE")
    private String nombre;

    @Column
    private String direccion;


}
