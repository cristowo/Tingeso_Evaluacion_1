package MilkStgo.Tingeso1.repositories;

import MilkStgo.Tingeso1.entities.LlegadaEntity;
import MilkStgo.Tingeso1.entities.PagoEntity;
import MilkStgo.Tingeso1.entities.ProveedorEntity;
import MilkStgo.Tingeso1.entities.ResultadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<PagoEntity, Integer> {

    // buscamos la fecha anterior a la fecha actual en pago y que sea del proveedor
    @Query("select p from PagoEntity p where p.quincena = :fecha and p.codigoProveedor = :codigo")
    List<PagoEntity> findPagolist(@Param("fecha") String fecha, @Param("codigo") String codigo);

    @Query("select p from PagoEntity p where p.codigoProveedor = :codigo")
    List<PagoEntity> findPagoByCodigo(@Param("codigo") String codigo);




}
