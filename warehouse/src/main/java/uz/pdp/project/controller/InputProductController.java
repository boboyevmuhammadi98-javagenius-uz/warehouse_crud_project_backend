package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.entity.InputProduct;
import uz.pdp.project.payload.InputProductDto;
import uz.pdp.project.payload.Result;
import uz.pdp.project.service.InputProductService;

@RestController
@RequestMapping("/inputproduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    @GetMapping
    public Page<InputProduct> getInputProducts(@RequestParam int page) {
        return inputProductService.getInputProduct(page);
    }

    @GetMapping("/{id}")
    public InputProduct getInputProductById(@PathVariable int id) {
        return inputProductService.getInputProductById(id);
    }

    @GetMapping("/input/{id}")
    public Page<InputProduct> getInputProductByInputId(@PathVariable int id, @RequestParam int page) {
        return inputProductService.getInputProductByInputId(id, page);
    }

    @PostMapping
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto) {
        return inputProductService.addInputProduct(inputProductDto);
    }

    @PutMapping("/{id}")
    public Result editInputProduct(@PathVariable int id, InputProductDto inputProductDto) {
        return inputProductService.editInputProduct(id, inputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInputProduct(@PathVariable int id) {
        return inputProductService.deleteInputProduct(id);
    }
}
