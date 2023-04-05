package MilkStgo.Tingeso1.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedores")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProveedorEntity {
    @Id
    @SequenceGenerator(
            name = "proveedor_sequence",
            sequenceName = "proveedor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "proveedor_sequence"
    )
    private Integer id_proveedor;
    private Integer codigo;
    private String nombre;
    private String categoria;
    private String retencion;
}
