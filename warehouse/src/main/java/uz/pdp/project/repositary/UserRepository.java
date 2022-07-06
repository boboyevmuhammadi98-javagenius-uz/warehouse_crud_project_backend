package uz.pdp.project.repositary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByCode(String code);

    Page<Users> findAllByActiveIsTrue(Pageable pageable);
}
