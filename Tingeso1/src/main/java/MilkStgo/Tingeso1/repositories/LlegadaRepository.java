package MilkStgo.Tingeso1.repositories;

import MilkStgo.Tingeso1.entities.LlegadaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LlegadaRepository extends JpaRepository<LlegadaEntity, Integer> {
    @Query(value = "SELECT COUNT(DISTINCT l.fecha) FROM LlegadaEntity l where l.proveedor = :codigo")
    int countByProveedor(@Param("codigo") String codigo);

    @Query("select l from LlegadaEntity l where l.proveedor = :Codigo")
    ArrayList<LlegadaEntity> findAllLlegadasByCodigoProveedor(@Param("Codigo") String Codigo);
}
