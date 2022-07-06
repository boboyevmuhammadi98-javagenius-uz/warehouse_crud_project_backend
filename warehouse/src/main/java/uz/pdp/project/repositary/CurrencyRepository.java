package uz.pdp.project.repositary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    boolean existsByName(String name);

    Page<Currency> findAllByActiveIsTrue(Pageable pageable);
}
