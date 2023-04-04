package MilkStgo.Tingeso1.repositories;

import MilkStgo.Tingeso1.entities.ResultadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoRepository extends JpaRepository<ResultadoEntity, Integer> {
}
