package uz.pdp.project.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
