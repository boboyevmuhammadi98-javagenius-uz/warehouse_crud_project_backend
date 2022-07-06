package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.project.entity.AdminPanel;
import uz.pdp.project.entity.Product;
import uz.pdp.project.repositary.AdminPanelRepository;
import uz.pdp.project.repositary.InputProductRepository;

import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    AdminPanelRepository adminPanelRepository;

    public Page<Product> getProductExpireDate(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Optional<AdminPanel> optionalAdminPanel = adminPanelRepository.findById(1);
        return inputProductRepository.getProductExpireDate(optionalAdminPanel.get().getNotificationDate(), pageable);
    }

}
