package uz.pdp.project.repositary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project.entity.WareHouse;

public interface WareHouseRepository extends JpaRepository<WareHouse, Integer> {
    boolean existsByName(String name);

    Page<WareHouse> findAllByActiveIsTrue(Pageable pageable);
}
