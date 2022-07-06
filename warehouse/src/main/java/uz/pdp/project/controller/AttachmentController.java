package uz.pdp.project.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.project.entity.Attachment;
import uz.pdp.project.payload.Result;
import uz.pdp.project.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @GetMapping
    public Page<Attachment> getAttachment(@RequestParam int page) {
        return attachmentService.getAttachment(page);
    }

    @GetMapping("/{id}")
    public Attachment getAttachmentById(@PathVariable int id) {
        return attachmentService.getAttachmentById(id);
    }

    @SneakyThrows
    @GetMapping("/ByAttachment/{id}")
    public void getAttachmentContentByAttachmentId(@PathVariable int id, HttpServletResponse response) {
        attachmentService.getAttachmentContentByAttachmentId(id, response);
    }

    @PostMapping
    public Result addAttachment(MultipartHttpServletRequest request) throws IOException {
        return attachmentService.addAttachment(request);
    }

    @PutMapping("/{id}")
    public Result editAttachment(@PathVariable int id, MultipartHttpServletRequest request) throws IOException {
        return attachmentService.editAttachment(id, request);
    }

    @DeleteMapping("/{id}")
    public Result deleteAttachment(@PathVariable int id) {
        return attachmentService.deleteAttachment(id);
    }
}
