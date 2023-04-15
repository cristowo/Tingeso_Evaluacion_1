package MilkStgo.Tingeso1.repositories;

import MilkStgo.Tingeso1.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Integer> {
    @Query("select p from ProveedorEntity p where p.codigo = :Codigo")
    ProveedorEntity findProveedorByCodigoProveedor(@Param("Codigo") String Codigo);

    @Query("select DISTINCT l.proveedor from LlegadaEntity l, ResultadoEntity r where l.proveedor = r.proveedor")
    ArrayList<String> findAllProveedores();
}
