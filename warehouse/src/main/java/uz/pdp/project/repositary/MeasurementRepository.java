package uz.pdp.project.repositary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    boolean existsByName(String name);

    Page<Measurement> findAllByActiveIsTrue(Pageable pageable);
}
