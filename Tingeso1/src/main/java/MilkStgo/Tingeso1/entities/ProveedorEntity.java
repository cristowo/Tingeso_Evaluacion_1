package MilkStgo.Tingeso1.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer id_proveedor;
    private Integer codigo;
    private String nombre;
    private String categoria;
    private Boolean retencion;
}
