package MilkStgo.Tingeso1.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "resultados")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResultadoEntity {
    @Id
    @SequenceGenerator(
            name = "resultado_sequence",
            sequenceName = "resultado_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "resultado_sequence"
    )
    private Integer id_resultado;
    private String proveedor;
    private Integer porcentaje_grasa;
    private Integer porcentaje_sodio;
}
