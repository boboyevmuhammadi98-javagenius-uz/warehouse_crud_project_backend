package uz.pdp.project.repositary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByCode(String code);

    Page<Product> findAllByActiveIsTrue(Pageable pageable);

    boolean existsByNameAndCategory_Id(String name, Integer category_id);
}
