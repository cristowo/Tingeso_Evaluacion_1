package MilkStgo.Tingeso1.repositories;

import MilkStgo.Tingeso1.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Integer> {
}
