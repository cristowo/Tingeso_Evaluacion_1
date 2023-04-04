package MilkStgo.Tingeso1.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pagos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PagoEntity {
    @Id
    private Integer id_pago;
    private Integer id_resultado;
}
