package MilkStgo.Tingeso1.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity
@Table(name = "llegadas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LlegadaEntity {
    @Id
    @SequenceGenerator(
            name = "llegada_sequence",
            sequenceName = "llegada_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "llegada_sequence"
    )
    private Integer id_Llegada;
    private Date fecha;
    private String turno;
    private Integer kg_leche;
    private String proveedor;
}

