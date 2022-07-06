package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.entity.Product;
import uz.pdp.project.payload.ProductDto;
import uz.pdp.project.payload.Result;
import uz.pdp.project.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public Page<Product> getProduct(@RequestParam int page) {
        return productService.getProduct(page);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @PutMapping("/{id}")
    public Result editProduct(@PathVariable int id, ProductDto productDto) {
        return productService.editProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}
