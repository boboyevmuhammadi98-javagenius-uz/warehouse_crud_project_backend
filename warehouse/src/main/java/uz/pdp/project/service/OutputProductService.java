package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.project.entity.*;
import uz.pdp.project.payload.OutputProductDto;
import uz.pdp.project.payload.Result;
import uz.pdp.project.repositary.*;

import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OutputRepository outputRepository;

    public Page<OutputProduct> getOutputProduct(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return outputProductRepository.findAll(pageable);
    }

    public OutputProduct getOutputProductById(int id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return optionalOutputProduct.orElse(null);
    }

    public Page<OutputProduct> getOutputProductByOutputId(int id, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return outputProductRepository.findAllByOutput_Id(id, pageable);
    }

    public Result addOutputProduct(OutputProductDto outputProductDto) {
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("product not found", false);
        if (!optionalProduct.get().isActive())
            return new Result("prodyct is not active", false);
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("output not found", false);
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("added", true);
    }

    public Result editOutputProduct(int id, OutputProductDto outputProductDto) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent())
            return new Result("output product not found", false);
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("product not found", false);
        if (!optionalProduct.get().isActive())
            return new Result("prodyct is not active", false);
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("output not found", false);
        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("edited", true);
    }

    public Result deleteOutputProduct(int id) {
        if (outputProductRepository.existsById(id)) {
            outputProductRepository.deleteById(id);
            return new Result("deleted", true);
        }
        return new Result("output product not found", false);
    }
}
