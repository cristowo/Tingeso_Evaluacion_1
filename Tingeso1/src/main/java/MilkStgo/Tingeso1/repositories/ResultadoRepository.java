package MilkStgo.Tingeso1.repositories;

import MilkStgo.Tingeso1.entities.ResultadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ResultadoRepository extends JpaRepository<ResultadoEntity, Integer> {
    //seleccionar todos los que tengan "Pasado"
    @Query("select r from ResultadoEntity r where r.tiempo = :Pasado")
    ArrayList<ResultadoEntity> findAllByTiempo(@Param("Pasado") String Pasado);

}
