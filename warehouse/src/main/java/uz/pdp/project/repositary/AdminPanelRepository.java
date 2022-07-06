package uz.pdp.project.repositary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project.entity.AdminPanel;
import uz.pdp.project.entity.Input;

public interface AdminPanelRepository extends JpaRepository<AdminPanel, Integer> {
}
