package MilkStgo.Tingeso1.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resultados")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResultadoEntity {
    @Id
    private Integer id_resultado;
    private Integer porcentaje_grasa;
    private Integer porcentaje_sodio;
}
