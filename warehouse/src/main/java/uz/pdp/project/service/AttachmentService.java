package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.project.entity.Attachment;
import uz.pdp.project.entity.AttachmentContent;
import uz.pdp.project.payload.Result;
import uz.pdp.project.repositary.AttachmentContentRepository;
import uz.pdp.project.repositary.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public Page<Attachment> getAttachment(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return attachmentRepository.findAll(pageable);
    }

    public Attachment getAttachmentById(int id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        return optionalAttachment.orElse(null);
    }

    public void getAttachmentContentByAttachmentId(int id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachment_Id(id);
            if (optionalAttachmentContent.isPresent()) {
                response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getOriginalFileName() + "\"");
                response.setContentType(attachment.getContentType());
                FileCopyUtils.copy(optionalAttachmentContent.get().getBytes(), response.getOutputStream());
            }
        }
    }

    public Result addAttachment(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment = new Attachment();
        assert file != null;
        attachment.setOriginalFileName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Result("added", true, savedAttachment.getId());
    }

    public Result editAttachment(int id, MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent())
            return new Result("attachment not found", false);
        Attachment attachment1 = optionalAttachment.get();
        assert file != null;
        attachment1.setOriginalFileName(file.getOriginalFilename());
        attachment1.setSize(file.getSize());
        attachment1.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment1);
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachment_Id(savedAttachment.getId());
        if (optionalAttachmentContent.isPresent()) {
            optionalAttachmentContent.get().setAttachment(savedAttachment);
            optionalAttachmentContent.get().setBytes(file.getBytes());
            attachmentContentRepository.save(optionalAttachmentContent.get());
        }
        return new Result("edited", true);
    }

    public Result deleteAttachment(int id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachment_Id(id);
            optionalAttachmentContent.ifPresent(attachmentContent -> attachmentContentRepository.deleteById(attachmentContent.getId()));
            attachmentRepository.deleteById(id);
            return new Result("deleted", true);
        }
        return new Result("attachment not found", false);
    }
}
