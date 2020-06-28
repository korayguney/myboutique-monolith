package com.korayshop.myboutique.web;

import com.korayshop.myboutique.service.CategoryService;
import com.korayshop.myboutique.web.dto.CategoryDto;
import com.korayshop.myboutique.web.utility.Web;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Web.API + "/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> findAll() {
        return this.categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        return this.categoryService.findById(id);
    }

    @PostMapping
    public CategoryDto create(CategoryDto categoryDto) {
        return this.categoryService.create(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.categoryService.delete(id);
    }
}