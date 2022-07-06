package uz.pdp.project.repositary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project.entity.Output;

public interface OutputRepository extends JpaRepository<Output, Integer> {
    Page<Output> findAllByWarehouse_Id(Integer warehouse_id, Pageable pageable);

    Page<Output> findAllByClient_Id(Integer client_id, Pageable pageable);
}
