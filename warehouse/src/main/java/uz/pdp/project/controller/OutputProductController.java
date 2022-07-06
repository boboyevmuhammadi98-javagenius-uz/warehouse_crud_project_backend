package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.entity.OutputProduct;
import uz.pdp.project.payload.OutputProductDto;
import uz.pdp.project.payload.Result;
import uz.pdp.project.service.OutputProductService;

@RestController
@RequestMapping("/outputproduct")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    @GetMapping
    public Page<OutputProduct> getOutputProducts(@RequestParam int page) {
        return outputProductService.getOutputProduct(page);
    }

    @GetMapping("/{id}")
    public OutputProduct getOutputProductById(@PathVariable int id) {
        return outputProductService.getOutputProductById(id);
    }

    @GetMapping("/Output/{id}")
    public Page<OutputProduct> getOutputProductByOutputId(@PathVariable int id, @RequestParam int page) {
        return outputProductService.getOutputProductByOutputId(id, page);
    }

    @PostMapping
    public Result addOutputProduct(@RequestBody OutputProductDto outputProductDto) {
        return outputProductService.addOutputProduct(outputProductDto);
    }

    @PutMapping("/{id}")
    public Result editOutputProduct(@PathVariable int id, OutputProductDto outputProductDto) {
        return outputProductService.editOutputProduct(id, outputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteOutputProduct(@PathVariable int id) {
        return outputProductService.deleteOutputProduct(id);
    }
}
