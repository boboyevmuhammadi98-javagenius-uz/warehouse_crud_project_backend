package uz.pdp.project.repositary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.project.entity.InputProduct;
import uz.pdp.project.entity.OutputProduct;
import uz.pdp.project.entity.Product;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {
    Page<InputProduct> findAllByInput_Id(int input_id, Pageable pageable);

    @Query(value = "select sum(price) from input_product join input using (id) where date=CURRENT_DATE", nativeQuery = true)
    double allInputSum();

    @Query(value = "select * from output_product join output using (id) where date=CURRENT_DATE order by amount desc", nativeQuery = true)
    Page<OutputProduct> getProducts(Pageable pageable);

    @Query(value = "select count(expire_date) from input_product where CURRENT_DATE + interval '3' >= expire_date", nativeQuery = true)
    int sumProductExpireDate();

    @Query(value = "select * from product join input_product ip on product.id = ip.product_id where CURRENT_DATE + interval :date>=expire_date", nativeQuery = true)
    Page<Product> getProductExpireDate(int date, Pageable pageable);
}
