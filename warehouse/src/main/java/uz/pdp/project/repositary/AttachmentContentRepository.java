package uz.pdp.project.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project.entity.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {
    Optional<AttachmentContent> findByAttachment_Id(Integer attachment_id);
}
