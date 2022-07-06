package uz.pdp.project.repositary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project.entity.Input;

public interface InputRepository extends JpaRepository<Input, Integer> {
    Page<Input> findAllByWarehouse_Id(Integer warehouse_id, Pageable pageable);

    Page<Input> findAllBySupplier_Id(Integer supplier_id, Pageable pageable);
}
