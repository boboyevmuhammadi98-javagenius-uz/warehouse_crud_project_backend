package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.entity.Category;
import uz.pdp.project.payload.CategoryDto;
import uz.pdp.project.payload.Result;
import uz.pdp.project.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public Page<Category> getCategory(@RequestParam int page) {
        return categoryService.getCategory(page);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @PutMapping("/{id}")
    public Result editCategory(@PathVariable int id, CategoryDto categoryDto) {
        return categoryService.editCategory(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }


}
