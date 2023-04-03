package MilkStgo.Tingeso1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "acopios")
@Data

public class AcopioEntity {
    @Id
    private Integer id_Acopio;
}
