package ucentral.edu.daniel.proyecto.Banco.Diner.entidades;


import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Cita")
@Table(name = "CITAS")
@ToString
@Builder
public class Cita implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "turno")
    private int turno;

    @ManyToOne
    @JoinColumn(name = "id_sede")
    private Sede sede;


    @Column
    private LocalDate fecha;

    @Column
    private LocalTime hora;

    @Column
    private String servicio;

    @ManyToOne
    @JoinColumn(name = "identificacion")
    private Cliente id_cliente;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Empleado empleado;

    @Column
    private String estado = "pendiente";

    @Column
    private String comentarios;

}
