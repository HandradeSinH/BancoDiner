package ucentral.edu.daniel.proyecto.Banco.Diner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDto {

    private long empId;

    private String nombre;

    private String carreo;

    private SedeDto sede;

    private String cargo;

    private String clave;


}
