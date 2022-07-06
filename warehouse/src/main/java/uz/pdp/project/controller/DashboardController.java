package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.entity.OutputProduct;
import uz.pdp.project.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @GetMapping("/suminput")
    public double allInputSum() {
        return dashboardService.allInputSum();
    }

    @GetMapping("/allOutput")
    public Page<OutputProduct> allOutputProduct(@RequestParam int page) {
        return dashboardService.allOutputProduct(page);
    }

    @GetMapping("/allproductExpireDate")
    public int sumProductExpireDate() {
        return dashboardService.sumProductExpireDate();
    }
}
