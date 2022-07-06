package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.project.entity.AdminPanel;
import uz.pdp.project.entity.Users;
import uz.pdp.project.entity.WareHouse;
import uz.pdp.project.payload.Result;
import uz.pdp.project.payload.UserDto;
import uz.pdp.project.repositary.AdminPanelRepository;
import uz.pdp.project.repositary.UserRepository;
import uz.pdp.project.repositary.WareHouseRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminPanelService {

    @Autowired
    AdminPanelRepository adminPanelRepository;

    public Result addNotificationDate(AdminPanel adminPanel) {
        Optional<AdminPanel> optionalAdminPanel = adminPanelRepository.findById(1);
        if (!optionalAdminPanel.isPresent()) {
            AdminPanel adminPanel1 = new AdminPanel();
            adminPanel1.setName("admin");
            adminPanel1.setNotificationDate(adminPanel.getNotificationDate());
            adminPanelRepository.save(adminPanel1);
            return new Result("addedd", true);
        }
        optionalAdminPanel.get().setNotificationDate(adminPanel.getNotificationDate());
        adminPanelRepository.save(optionalAdminPanel.get());
        return new Result("added", true);
    }
}
