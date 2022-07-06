package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.project.entity.*;
import uz.pdp.project.payload.InputProductDto;
import uz.pdp.project.payload.Result;
import uz.pdp.project.repositary.*;

import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    InputRepository inputRepository;

    public Page<InputProduct> getInputProduct(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return inputProductRepository.findAll(pageable);
    }

    public InputProduct getInputProductById(int id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.orElse(null);
    }

    public Page<InputProduct> getInputProductByInputId(int id, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return inputProductRepository.findAllByInput_Id(id, pageable);
    }

    public Result addInputProduct(InputProductDto inputProductDto) {
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("product not found", false);
        if (!optionalProduct.get().isActive())
            return new Result("product is not active", false);
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new Result("input not found", false);
        InputProduct inputProduct = new InputProduct();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setExpireDate(inputProduct.getExpireDate());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new Result("added", true);
    }

    public Result editInputProduct(int id, InputProductDto inputProductDto) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent())
            return new Result("input product not found", false);
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("product not found", false);
        if (!optionalProduct.get().isActive())
            return new Result("product is not active", false);
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new Result("input not found", false);
        InputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new Result("edited", true);
    }

    public Result deleteInputProduct(int id) {
        if (inputProductRepository.existsById(id)) {
            inputProductRepository.deleteById(id);
            return new Result("deleted", true);
        }
        return new Result("input product not found", false);
    }
}
