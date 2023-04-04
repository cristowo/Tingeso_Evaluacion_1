package MilkStgo.Tingeso1.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "llegadas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LlegadaEntity {
    @Id
    private Integer id_Llegada;
    private Integer fecha;
    private Integer turno;
    private Integer kg_leche;
    private Integer id_proveedor;
    private Integer id_resultado;
}

