package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.project.entity.OutputProduct;
import uz.pdp.project.repositary.InputProductRepository;

@Service
public class DashboardService {
    @Autowired
    InputProductRepository inputProductRepository;

    public double allInputSum() {
        return inputProductRepository.allInputSum();
    }

    public Page<OutputProduct> allOutputProduct(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return inputProductRepository.getProducts(pageable);
    }

    public int sumProductExpireDate() {
        return inputProductRepository.sumProductExpireDate();
    }
}
