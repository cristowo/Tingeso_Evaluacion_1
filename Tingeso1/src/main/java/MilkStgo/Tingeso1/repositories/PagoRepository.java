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

@Repository
public interface PagoRepository extends JpaRepository<PagoEntity, Integer> {

    //sin el value =, y el nativeQuery daba error.
    @Query(value = "SELECT COUNT(DISTINCT l.fecha) FROM LlegadaEntity l where l.proveedor = :codigo")
    int countByProveedor(@Param("codigo") String codigo);

    // buscamos la fecha anterior a la fecha actual en pago y que sea del proveedor
    @Query("select p from PagoEntity p where p.quincena = :fecha and p.codigoProveedor = :codigo")
    PagoEntity findPagoAnterior(@Param("fecha") String fecha, @Param("codigo") String codigo);

    // obtenemos informacion de los proveedores
    @Query("select l from LlegadaEntity l where l.proveedor = :Codigo")
    ArrayList<LlegadaEntity> findAllByCodigoProveedor(@Param("Codigo") String Codigo);

    @Query("select p from ProveedorEntity p where p.codigo = :Codigo")
    ProveedorEntity findByCodigoProveedor(@Param("Codigo") String Codigo);

    @Query("select p from ResultadoEntity p where p.proveedor = :Codigo")
    ResultadoEntity findResultadosByCodigoProveedor(@Param("Codigo") String Codigo);

}
