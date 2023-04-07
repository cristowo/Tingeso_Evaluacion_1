package MilkStgo.Tingeso1.repositories;

import MilkStgo.Tingeso1.entities.LlegadaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LlegadaRepository extends JpaRepository<LlegadaEntity, Integer> {
    //@Query("select l from LlegadaEntity l where l.id_proveedor = :id")
    //LlegadaEntity findById_proveedor(@Param("id")Integer id_proveedor);
}
