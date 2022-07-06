package uz.pdp.project.repositary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project.entity.OutputProduct;

public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {
    Page<OutputProduct> findAllByOutput_Id(int output_id, Pageable pageable);
}
