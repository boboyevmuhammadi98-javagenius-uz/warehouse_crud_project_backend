package uz.pdp.project.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
