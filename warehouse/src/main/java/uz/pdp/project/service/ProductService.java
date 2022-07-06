package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.project.entity.*;
import uz.pdp.project.payload.ProductDto;
import uz.pdp.project.payload.Result;
import uz.pdp.project.repositary.*;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    public Page<Product> getProduct(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return productRepository.findAllByActiveIsTrue(pageable);
    }

    public Product getProductById(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            if (optionalProduct.get().isActive()) {
                return optionalProduct.orElse(null);
            }
        }
        return optionalProduct.orElse(null);
    }

    public Result addProduct(ProductDto productDto) {
        if (productRepository.existsByNameAndCategory_Id(productDto.getName(), productDto.getCategoryId()))
            return new Result("exists name and category", false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("category not found", false);
        if (!optionalCategory.get().isActive())
            return new Result("Category is not active", false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (!optionalAttachment.isPresent())
            return new Result("attachment not found", false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("measurement not found", false);
        if (!optionalMeasurement.get().isActive())
            return new Result("measurement is not active", false);
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        productRepository.save(product);
        return new Result("added", true);
    }

    public Result editProduct(int id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("product not found", false);
        if (productRepository.existsByNameAndCategory_Id(productDto.getName(), productDto.getCategoryId()))
            return new Result("exists name and category", false);
        if (!optionalProduct.get().isActive())
            return new Result("product is not active", false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("category not found", false);
        if (!optionalCategory.get().isActive())
            return new Result("Category is not active", false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (!optionalAttachment.isPresent())
            return new Result("attachment not found", false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("measurement not found", false);
        if (!optionalMeasurement.get().isActive())
            return new Result("measurement is not active", false);
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        productRepository.save(product);
        return new Result("edited", true);
    }

    public Result deleteProduct(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            if (optionalProduct.get().isActive()) {
                productRepository.deleteById(id);
                return new Result("deleted", true);
            }
            return new Result("product is not active", false);
        }
        return new Result("product not found", false);
    }
}
