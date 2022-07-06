package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.project.entity.Category;
import uz.pdp.project.payload.CategoryDto;
import uz.pdp.project.payload.Result;
import uz.pdp.project.repositary.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Page<Category> getCategory(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return categoryRepository.findAllByActiveIsTrue(pageable);
    }

    public Category getCategoryById(int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            if (optionalCategory.get().isActive())
                return optionalCategory.get();
        }
        return optionalCategory.orElse(null);
    }

    public Result addCategory(CategoryDto categoryDto) {
        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName)
            return new Result("exists bu name", false);
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalParentCategory.isPresent()) {
                return new Result("parent category not found", false);
            }
            if (optionalParentCategory.get().isActive())
                category.setParentCategory(optionalParentCategory.get());
            return new Result("this parent category is not active", false);
        }
        categoryRepository.save(category);
        return new Result("added", true);
    }

    public Result editCategory(int id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new Result("category not found", false);
        if (categoryRepository.existsByName(categoryDto.getName()))
            return new Result("exists bu name", false);
        if (!optionalCategory.get().isActive())
            return new Result("category is not active", false);
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalParentCategory.isPresent())
                return new Result("parent category not found", false);
            category.setParentCategory(optionalParentCategory.get());
        }
        categoryRepository.save(category);
        return new Result("edited", true);
    }

    public Result deleteCategory(int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            if (!optionalCategory.get().isActive())
                return new Result("category is not active", false);
            categoryRepository.delete(optionalCategory.get());
            return new Result("deleted", true);
        }
        return new Result("ctegory not found", true);
    }
}
